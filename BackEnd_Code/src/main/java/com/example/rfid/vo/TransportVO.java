package com.example.rfid.vo;

public class TransportVO {
    private int TransportId;
    private int CAS_id;
    private int batchId;
    private int num;
    private int State;

    public TransportVO() {
    }

    public TransportVO(TransportInfoVO transportInfoVO) {
        TransportId = transportInfoVO.getTransportId();
        this.CAS_id = transportInfoVO.getCasId();
        this.batchId = transportInfoVO.getBatchId();
        this.num = transportInfoVO.getNum();
        State = transportInfoVO.getState();
    }

    public TransportVO(int transportId, int CAS_id, int batchId, int num, int state) {
        TransportId = transportId;
        this.CAS_id = CAS_id;
        this.batchId = batchId;
        this.num = num;
        State = state;
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
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
