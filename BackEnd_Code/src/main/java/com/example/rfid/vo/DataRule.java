package com.example.rfid.vo;

import lombok.Data;

@Data
public class DataRule {
    private String name;
    private String sql;
    private String condition;
    private String action;
    private String state;

    public DataRule() {
    }

    public DataRule(String name, String sql, String condition, String action) {
        this.name = name;
        this.sql = sql;
        this.condition = condition;
        this.action = action;
    }

    public DataRule(String name, String sql, String condition, String action, String state) {
        this.name = name;
        this.sql = sql;
        this.condition = condition;
        this.action = action;
        this.state = state;
    }

}
