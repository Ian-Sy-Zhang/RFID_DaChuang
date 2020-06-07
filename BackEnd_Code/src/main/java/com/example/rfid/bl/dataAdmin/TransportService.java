package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.BatchVO;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;

public interface TransportService {
    //transport表的管理工作，增删改查方法

    // 增加Transport
    public ResponseVO addTransport(TransportVO transportVO);

    // 删除batch
    public ResponseVO removeTransport(int TransportId);

    // 修改batch
    public ResponseVO updateTransport(TransportVO transportVO);

    // 查询一条batch信息
    public ResponseVO searchOneTransport(int TransportId);

    // 查询所有batch信息
    public ResponseVO searchTransportList();
}
