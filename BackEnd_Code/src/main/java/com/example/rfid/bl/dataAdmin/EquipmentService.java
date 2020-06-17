package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;

public interface EquipmentService {
    // equipment表的CRUD工作

    // 增加equipment
    public ResponseVO addEquipment(EquipmentVO equipmentVO);

    // 删除equipment
    public ResponseVO removeEquipment(int id);

    // 修改equipment
    public ResponseVO updateEquipment(EquipmentVO equipmentVO);

    // 查询一条equipment信息
    public ResponseVO searchOneEquipment(int id);

    // 查询所有equipment信息
    public ResponseVO searchEquipmentList();
}
