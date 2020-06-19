package com.example.rfid.controller.connection;


import com.example.rfid.bl.Connection.HTTPConnectService;
import com.example.rfid.bl.Connection.MQTTConnectionService;
import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.blImpl.deviceConnection.HTTPConnection.HTTPConnect;
import com.example.rfid.blImpl.deviceConnection.MQTTConnection.MqttConnection;
import com.example.rfid.blImpl.deviceConnection.MQTTConnection.MqttRecieveClient;
import com.example.rfid.po.MqttConfiguration;
import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Connection")
public class ConnectionController {

    @Autowired
    EquipmentService equipmentService;

    Map<Integer,HTTPConnect> httpConnects;
    MqttConnection mqttConnection = new MqttConnection();



    @GetMapping("/connection/{id}")
    public ResponseVO connect(@PathVariable int id) throws IOException {
        ResponseVO temp = equipmentService.searchOneEquipment(id);
        EquipmentVO mes = (EquipmentVO) temp.getContent();
        if(mes.getConnectionType()=="http")
        {
            HTTPConnect httpConnect = new HTTPConnect(mes.getIp());
            if(httpConnect.connect().getResponseCode() == 200){
                httpConnects.put(id,httpConnect);
                return ResponseVO.buildSuccess(true);
            }
            else{
                return ResponseVO.buildSuccess(false);
            }

        }
        else{
            if(mqttConnection.getClient().isConnected()!=true){
                MqttConfiguration mqttConfiguration = new MqttConfiguration();
                mqttConfiguration.setHost("192.168.1.1");
                mqttConfiguration.setUsername("Admin");
                mqttConfiguration.setPassword("passwd");
                mqttConfiguration.setKeepAlive(200);
                mqttConfiguration.setClientId("rfid");
                mqttConfiguration.setQos(new int[]{1});
                mqttConfiguration.setTopic(new String[]{"equitment"+id});
                mqttConnection.connect(mqttConfiguration);
                if(mqttConnection.getClient().isConnected()==false){
                    return ResponseVO.buildSuccess(true);
                }
            }
            else{
                MqttRecieveClient mqttRecieveClient = new MqttRecieveClient(mqttConnection);
                mqttRecieveClient.subscribe(new String[]{"equitment"+id},new int[]{1});
            }
            return ResponseVO.buildSuccess(true);
        }
    }

    @GetMapping("/close/{id}")
    public ResponseVO close(@PathVariable int id){
        ResponseVO temp = equipmentService.searchOneEquipment(id);
        EquipmentVO mes = (EquipmentVO) temp.getContent();
        if(mes.getConnectionType()=="http"){
            HTTPConnect httpConnect = httpConnects.get(id);
            httpConnect.closeConnect();
            httpConnects.remove(id);
            return ResponseVO.buildSuccess(true);
        }
        else{
            MqttRecieveClient mqttRecieveClient = new MqttRecieveClient(mqttConnection);
            mqttRecieveClient.cleanTopic("equitment"+id);
            return ResponseVO.buildSuccess(true);
        }
    }

}
