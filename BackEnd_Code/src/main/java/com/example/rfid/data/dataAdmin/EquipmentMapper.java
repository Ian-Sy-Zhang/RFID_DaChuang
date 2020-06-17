package com.example.rfid.data.dataAdmin;

import com.example.rfid.vo.EquipmentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface EquipmentMapper {

    int addEquipment(@Param("equipmentVO") EquipmentVO equipmentVO);

    int removeEquipment(@Param("id") int id);

    int updateEquipment(@Param("equipmentVO") EquipmentVO equipmentVO);

    EquipmentVO searchEquipment(@Param("id") int id);

    ArrayList<EquipmentVO> searchEquipmentList();


}
