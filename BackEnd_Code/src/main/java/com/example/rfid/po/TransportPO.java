package com.example.rfid.po;

public class TransportPO {
    private int TransportId;
    private int CAS_id;
    private int batchId;
    private int Num;
    private int State;

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
