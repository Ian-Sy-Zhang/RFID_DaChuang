package com.example.rfid.vo;

import com.example.rfid.data.dataAdmin.TransportMapper;

public class TransportInfoVO {
    // 前半部分包含了一些设备信息
    private String name;
    private String ip;

    // 后半部分包含了运输信息
    private int TransportId;
    private int CAS_id;
    private int batchId;
    private int Num;
    private int State;

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
        return TransportId;
    }

    public void setTransportId(int transportId) {
        TransportId = transportId;
    }

    public int getCAS_id() {
        return CAS_id;
    }

    public void setCAS_id(int CAS_id) {
        this.CAS_id = CAS_id;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
