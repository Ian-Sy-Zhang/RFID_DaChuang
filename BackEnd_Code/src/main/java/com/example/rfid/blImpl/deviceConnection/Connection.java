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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Service
@Component
public class Connection implements ConnectionService {
    @Autowired
    EquipmentService equipmentService;

    Map<Integer, HTTPConnect> httpConnects;
    MqttConnection mqttConnection = new MqttConnection();
    @Override
    public ResponseVO connect(int id) {
        ResponseVO temp = equipmentService.searchOneEquipment(id);
        if(temp.getContent()==null){
            return ResponseVO.buildSuccess(false);
        }
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
            if(mqttConnection.getClient()==null){
                MqttConfiguration mqttConfiguration = new MqttConfiguration();
                mqttConfiguration.setHost("192.168.1.1");
                mqttConfiguration.setUsername("Admin");
                mqttConfiguration.setPassword("passwd");
                mqttConfiguration.setKeepAlive(200);
                mqttConfiguration.setClientId("rfid");
                mqttConfiguration.setQos(new int[]{1});
                mqttConfiguration.setTopic(new String[]{"equitment"+id});
                mqttConnection.connect(mqttConfiguration);
                if(mqttConnection.getClient()==null||mqttConnection.getClient().isConnected()==false){
                    return ResponseVO.buildSuccess(false);
                }
            }
            else{
                MqttRecieveClient mqttRecieveClient = new MqttRecieveClient(mqttConnection);
                mqttRecieveClient.subscribe(new String[]{"equitment"+id},new int[]{1});
            }
            return ResponseVO.buildSuccess(true);
        }
    }
    @Override
    public ResponseVO close(int id){
        ResponseVO temp = equipmentService.searchOneEquipment(id);
        if(temp.getContent()==null){
            return ResponseVO.buildSuccess(false);
        }
        EquipmentVO mes = (EquipmentVO) temp.getContent();
        if(mes.getConnectionType()=="http"){
            if(httpConnects.containsKey(id)) {
                HTTPConnect httpConnect = httpConnects.get(id);
                httpConnect.closeConnect();
                httpConnects.remove(id);
                return ResponseVO.buildSuccess(true);
            }
            else
                return ResponseVO.buildSuccess(false);
        }
        else{
            MqttRecieveClient mqttRecieveClient = new MqttRecieveClient(mqttConnection);
            mqttRecieveClient.cleanTopic("equitment"+id);
            return ResponseVO.buildSuccess(true);
        }
    }


    @Scheduled(fixedRate = 2000)
    public void testMqtt() throws Exception {
        if(mqttConnection!=null&&mqttConnection.getClient()!=null) {
            if (mqttConnection.getClient().isConnected() == false) {
                mqttConnection.reConnect();
            }

            if (mqttConnection.getClient().isConnected() == false) {
                System.out.println("something error with mqttServer");
            }
        }
    }


    @Scheduled(fixedRate = 2000)
    public void testHttp(){
        if(httpConnects!=null) {
            try {
                for (HTTPConnect temp : httpConnects.values()) {
                    if (temp.getConnect().getResponseCode() != 200) {
                        temp.connect();
                    }
                    if (temp.getConnect().getResponseCode() != 200) {
                        System.out.println("something error with http");
                        int id = 0;
                        Iterator it = httpConnects.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            Object obj = entry.getValue();
                            if (obj != null && obj.equals(temp)) {

                                id = (int) entry.getKey();

                            }
                        }

                        equipmentService.setEquipmentOffline(id);
                    }
                }
            } catch (IOException exception) {
                System.out.println("IO ERROR");
            }
        }
    }
}
