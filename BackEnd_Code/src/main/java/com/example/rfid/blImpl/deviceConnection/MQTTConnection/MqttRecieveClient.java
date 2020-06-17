package com.example.rfid.blImpl.deviceConnection.MQTTConnection;

import com.example.rfid.bl.Connection.MQTTRecieveService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MqttRecieveClient implements MQTTRecieveService {
    private static MqttClient client;
    public MqttRecieveClient(){
        client = MqttConnection.getClient();
    }

    /**
     * 取消订阅某个主题
     * @param topic
     */
    public void cleanTopic(String topic) {
        if(null != client&& !client.isConnected()) {
            try {
                client.unsubscribe(topic);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("mqttClient is error");
        }
    }


    /**
     * 订阅某个主题
     * @param topic
     * @param qos
     */
    public void subscribe(String[] topic,int[] qos) {
        try {
            MqttPushClient.getClient().unsubscribe(topic);
            MqttPushClient.getClient().subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

}

