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

    // 将指定id的设备的status设置为online
    public ResponseVO setEquipmentOnline(int id);

    // 将指定id的设备的status设置为offline
    public ResponseVO setEquipmentOffline(int id);

    // 查询一条equipment信息
    public ResponseVO searchOneEquipment(int id);

    // 查询所有equipment信息
    public ResponseVO searchEquipmentList();

    // 查询设备名
    public ResponseVO getEquipmentNameByIp(String ip);
}
