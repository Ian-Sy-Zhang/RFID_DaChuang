package com.example.rfid.vo;

import lombok.Data;

@Data
public class Rule {
    private String name;
    private String state;
    private String type;
    private String condition;
    private String action;

    public Rule() {
    }

    public Rule(String name, String state, String type, String condition, String action) {
        this.name = name;
        this.state = state;
        this.type = type;
        this.condition = condition;
        this.action = action;
    }
}
