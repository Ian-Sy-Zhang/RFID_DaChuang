package com.example.rfid.data.dataAdmin;

import com.example.rfid.vo.TransportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransportMapper {

    void addTransInfo(@Param("transportVO") TransportVO transportVO);

    void removeTransport(@Param("transportId") int transportId);

    void updateTransport(@Param("transportVO") TransportVO transportVO);

    List<TransportVO> searchOneTransport(@Param("TransportId") int transportId);

    List<TransportVO> searchTransport();

}
