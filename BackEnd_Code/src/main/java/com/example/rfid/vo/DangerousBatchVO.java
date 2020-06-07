package com.example.rfid.vo;

public class DangerousBatchVO {
    int batchId;

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getDangerousTimes() {
        return DangerousTimes;
    }

    public void setDangerousTimes(int dangerousTimes) {
        DangerousTimes = dangerousTimes;
    }

    int DangerousTimes;
}
