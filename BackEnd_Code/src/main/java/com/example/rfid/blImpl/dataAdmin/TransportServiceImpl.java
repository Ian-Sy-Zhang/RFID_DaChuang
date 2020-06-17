package com.example.rfid.blImpl.dataAdmin;

import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Override
    public ResponseVO addTransport(TransportInfoVO transportInfoVO) {
        return null;
    }

    @Override
    public ResponseVO removeTransport(int TransportId) {
        return null;
    }

    @Override
    public ResponseVO updateTransport(TransportVO transportVO) {
        return null;
    }

    @Override
    public ResponseVO searchOneTransport(int TransportId) {

        return null;
    }

    @Override
    public ResponseVO searchTransportList() {
        return null;
    }
}
