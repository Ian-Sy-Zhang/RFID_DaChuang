package com.example.rfid.vo;

import lombok.Data;

@Data
public class EquipmentRule {
    private String name;
    private String ip;
    private String condition;
    private String action;
    private String state;

    public EquipmentRule() {
    }

    public EquipmentRule(String name, String ip, String condition, String action) {
        this.name = name;
        this.ip = ip;
        this.condition = condition;
        this.action = action;
    }

    public EquipmentRule(String name, String ip, String condition, String action, String state) {
        this.name = name;
        this.ip = ip;
        this.condition = condition;
        this.action = action;
        this.state = state;
    }
}
