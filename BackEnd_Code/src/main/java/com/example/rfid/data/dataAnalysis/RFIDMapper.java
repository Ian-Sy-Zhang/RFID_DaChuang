package com.example.rfid.data.dataAnalysis;


import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.DangerousBatchVO;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface RFIDMapper {

    ArrayList<DangerousBatchVO> mostDangerousBatch();

    ArrayList<ChemcasVO> mostDangerousChem();

}
