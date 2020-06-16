package com.example.rfid.vo;

public class BatchVO {
    int batchId;
    int batchName;
    int Destination;
    int Departure;

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchName() {
        return batchName;
    }

    public void setBatchName(int batchName) {
        this.batchName = batchName;
    }

    public int getDestination() {
        return Destination;
    }

    public void setDestination(int destination) {
        Destination = destination;
    }

    public int getDeparture() {
        return Departure;
    }

    public void setDeparture(int departure) {
        Departure = departure;
    }
}
