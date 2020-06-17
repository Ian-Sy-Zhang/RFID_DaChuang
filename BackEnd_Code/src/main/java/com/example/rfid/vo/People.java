package com.example.rfid.vo;
import lombok.Data;

@Data
public class People {
    private int sex;

    private String name;

    private String drlType;

    public People(int sex, String name, String drlType) {
        this.sex = sex;
        this.name = name;
        this.drlType = drlType;
    }

    public People() {
    }
}
