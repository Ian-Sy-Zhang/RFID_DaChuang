package com.example.rfid.vo;

import com.example.rfid.data.dataAdmin.TransportMapper;

public class TransportInfoVO {
    // 前半部分包含了一些设备信息
    private String name;
    private String ip;

    // 后半部分包含了运输信息
    private int transportId;
    private int casId;
    private int batchId;
    private int num;
    private int state;

    public TransportInfoVO() {
    }

    public TransportInfoVO(String name, String ip, int transportId, int casId, int batchId, int num, int state) {
        this.name = name;
        this.ip = ip;
        this.transportId = transportId;
        this.casId = casId;
        this.batchId = batchId;
        this.num = num;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public int getCasId() {
        return casId;
    }

    public void setCasId(int casId) {
        this.casId = casId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
