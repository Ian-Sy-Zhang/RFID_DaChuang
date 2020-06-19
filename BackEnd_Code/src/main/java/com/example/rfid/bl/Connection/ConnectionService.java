package com.example.rfid.bl.Connection;

import com.example.rfid.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ConnectionService {
    public ResponseVO connect(int id);

    public ResponseVO close(int id);
}
