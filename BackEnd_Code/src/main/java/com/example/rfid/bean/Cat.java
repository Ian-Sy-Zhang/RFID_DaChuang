package com.example.rfid.bean;

import lombok.Data;

@Data
public class Cat {
    private int age;

    public Cat() {
    }

    public Cat(int age) {
        this.age = age;
    }
}
