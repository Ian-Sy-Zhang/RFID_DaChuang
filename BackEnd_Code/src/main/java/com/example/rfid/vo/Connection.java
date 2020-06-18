package com.example.rfid.vo;

import com.example.rfid.blImpl.deviceConnection.HTTPConnection.HTTPConnect;
import com.example.rfid.blImpl.deviceConnection.MQTTConnection.MqttConnection;

public class Connection {
    private HTTPConnect httpConnection = null;
    private MqttConnection mqttConnection =null;
    private boolean type = true; //true为http，false为mqtt

    public Connection(HTTPConnect httpConnect){
        type = true;
        httpConnection = httpConnect;
    }

    public Connection(MqttConnection mqttConnection){
        type = false;
        this.mqttConnection = mqttConnection;
    }
}
