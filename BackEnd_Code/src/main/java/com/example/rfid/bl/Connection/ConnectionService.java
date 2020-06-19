package com.example.rfid.bl.Connection;

import com.example.rfid.vo.ResponseVO;

import java.io.IOException;

public interface ConnectionService {
    public ResponseVO connect(int id);

    public ResponseVO close(int id);
}
