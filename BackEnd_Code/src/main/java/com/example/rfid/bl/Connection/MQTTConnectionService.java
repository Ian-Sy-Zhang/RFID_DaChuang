package com.example.rfid.bl.Connection;

import com.example.rfid.po.MqttConfiguration;

public interface MQTTConnectionService {
    public void connect(MqttConfiguration mqttConfiguration);

    public Boolean reConnect() throws Exception;

    public void closeConnect();
}
