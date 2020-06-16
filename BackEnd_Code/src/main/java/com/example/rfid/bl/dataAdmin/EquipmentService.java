package com.example.rfid.bl.dataAdmin;

import com.example.rfid.vo.ResponseVO;

public interface EquipmentService {
    // equipment表的CRUD工作
    ResponseVO getEquipmentNameByIp(String ip);
}
