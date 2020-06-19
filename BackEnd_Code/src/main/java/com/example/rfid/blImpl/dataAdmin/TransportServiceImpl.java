package com.example.rfid.blImpl.dataAdmin;

import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.data.dataAdmin.TransportMapper;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    TransportMapper transportMapper;

    @Override
    public ResponseVO addTransport(TransportVO transportVO) {
        try {
            transportMapper.addTransInfo(transportVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("addTransport失败");
        }
    }

    @Override
    public ResponseVO removeTransport(int transportId) {
        try {
            transportMapper.removeTransport(transportId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("removeTransport失败");
        }
    }

    @Override
    public ResponseVO updateTransport(TransportVO transportVO) {
        try {
            transportMapper.updateTransport(transportVO);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("updateTransport失败");
        }
    }

    @Override
    public ResponseVO searchOneTransport(int transportId) {
        try {
            return ResponseVO.buildSuccess(transportMapper.searchOneTransport(transportId));
        }catch (Exception e){
            return ResponseVO.buildFailure("searchOneTransport失败");
        }
    }

    @Override
    public ResponseVO searchTransportList() {
        try {
            return ResponseVO.buildSuccess(transportMapper.searchTransport());
        }catch (Exception e){
            return ResponseVO.buildFailure("searchTransportList失败");
        }
    }
}
