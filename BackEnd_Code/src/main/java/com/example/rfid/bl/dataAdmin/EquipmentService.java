package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;

public interface EquipmentService {
    // equipment表的CRUD工作

    // 增加Equipment
    public ResponseVO addTransport(TransportInfoVO transportInfoVO);

    // 删除Equipment
    public ResponseVO removeTransport(int TransportId);

    // 修改Equipment
    public ResponseVO updateTransport(TransportVO transportVO);

    // 查询一条Equipment信息
    public ResponseVO searchOneTransport(int TransportId);

    // 查询所有Transport信息
    public ResponseVO searchTransportList();
}
