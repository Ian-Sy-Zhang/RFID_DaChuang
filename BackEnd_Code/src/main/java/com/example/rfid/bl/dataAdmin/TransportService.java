package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.BatchVO;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;

public interface TransportService {
    //transport表的管理工作，增删改查方法

    // 增加Transport
    public ResponseVO addTransport(TransportVO transportVO);

    // 删除Transport
    public ResponseVO removeTransport(int TransportId);

    // 修改Transport
    public ResponseVO updateTransport(TransportVO transportVO);

    // 查询一条Transport信息
    public ResponseVO searchOneTransport(int TransportId);

    // 查询所有Transport信息
    public ResponseVO searchTransportList();
}
