package com.example.rfid.bl.Connection;

public interface MQTTRecieveService {
    public void cleanTopic(String topic);

    public void subscribe(String[] topic, int[] qos);
}
