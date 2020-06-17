package com.example.rfid.blImpl.deviceConnection.MQTTConnection;

import com.example.rfid.bl.Connection.MQTTPushService;
import com.example.rfid.po.MqttConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



public class MqttPushClient implements MQTTPushService
{


    private static final Logger log = LoggerFactory.getLogger(MqttPushClient.class);

    private MqttClient client;

    public MqttPushClient(MqttConnection mqttConnection){
        client= mqttConnection.getClient();
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }


    /**
     * 发布，默认qos为0，非持久化
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic,String pushMessage){
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     * @param qos
     * @param retained  是否持有
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos,boolean retained,String topic,String pushMessage){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if(null == mTopic){
            log.error("MQTT topic 不存在");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布消息的服务质量(推荐为：2-确保消息到达一次。0-至多一次到达；1-至少一次到达，可能重复)，
     * retained 默认：false-非持久化（是指一条消息消费完，就会被删除；持久化，消费完，还会保存在服务器中，当新的订阅者出现，继续给新订阅者消费）
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, String topic, String pushMessage){
        publish(qos, false, topic, pushMessage);
    }

}