package com.example.rfid.data.dataAdmin;

import com.example.rfid.vo.TransportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TransportMapper {

    int addTransInfo(@Param("transportVO") TransportVO transportVO);

}
