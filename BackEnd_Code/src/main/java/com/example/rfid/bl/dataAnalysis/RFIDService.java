package com.example.rfid.bl.dataAnalysis;

import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;

public interface RFIDService {

    // 分析“最容易出事运输路径”
    public ResponseVO mostDangerousBatch();

    // 分析“最容易出事化学用品”
    public ResponseVO mostDangerousChem();

}
