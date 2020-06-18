package com.example.rfid.bl.Connection;

public interface MQTTPushService {
    public void publish(String topic, String pushMessage);

    public void publish(int qos, boolean retained, String topic, String pushMessage);

    public void publish(int qos, String topic, String pushMessage);
}
