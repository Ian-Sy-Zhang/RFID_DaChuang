package com.example.rfid.blImpl.dataAnalysis;


import com.example.rfid.bl.dataAnalysis.RFIDService;
import com.example.rfid.data.dataAnalysis.RFIDMapper;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RFIDServiceImpl implements RFIDService {
    @Resource
    RFIDMapper rfidMapper;

    @Override
    public ResponseVO mostDangerousBatch() {
        try {
            return ResponseVO.buildSuccess(rfidMapper.mostDangerousBatch());
        }
        catch (Exception e){
            return ResponseVO.buildFailure("获取危险的batch失败");
        }
    }

    @Override
    public ResponseVO mostDangerousChem() {
        try {
            return ResponseVO.buildSuccess(rfidMapper.mostDangerousChem());
        }catch (Exception e){
            return ResponseVO.buildFailure("获取危险的chem失败");
        }
    }
}
