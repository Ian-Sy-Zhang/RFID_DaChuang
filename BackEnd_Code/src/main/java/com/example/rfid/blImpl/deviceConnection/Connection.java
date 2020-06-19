package com.example.rfid.blImpl.deviceConnection;

import com.example.rfid.bl.Connection.ConnectionService;
import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.blImpl.deviceConnection.HTTPConnection.HTTPConnect;
import com.example.rfid.blImpl.deviceConnection.MQTTConnection.MqttConnection;
import com.example.rfid.blImpl.deviceConnection.MQTTConnection.MqttRecieveClient;
import com.example.rfid.po.MqttConfiguration;
import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class Connection implements ConnectionService {
    @Autowired
    EquipmentService equipmentService;

    Map<Integer, HTTPConnect> httpConnects;
    MqttConnection mqttConnection = new MqttConnection();

    public ResponseVO connect(int id) {
        ResponseVO temp = equipmentService.searchOneEquipment(id);
        EquipmentVO mes = (EquipmentVO) temp.getContent();
        if(mes.getConnectionType()=="http")
        {
            HTTPConnect httpConnect = new HTTPConnect(mes.getIp());
            try {
                if (httpConnect.connect().getResponseCode() == 200) {
                    httpConnects.put(id, httpConnect);
                    return ResponseVO.buildSuccess(true);
                } else {
                    return ResponseVO.buildSuccess(false);
                }
            }catch(IOException exception){
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

    public ResponseVO close(int id){
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
