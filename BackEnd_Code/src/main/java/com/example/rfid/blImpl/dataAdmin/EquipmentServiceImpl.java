package com.example.rfid.blImpl.dataAdmin;

import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.data.dataAdmin.EquipmentMapper;
import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.ResponseVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    EquipmentMapper equipmentMapper;

    @Override
    public ResponseVO addEquipment(EquipmentVO equipmentVO) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.addEquipment(equipmentVO));
        }catch (Exception e){
            return ResponseVO.buildFailure("addEquipment失败");
        }
    }

    @Override
    public ResponseVO removeEquipment(int id) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.removeEquipment(id));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateEquipment(EquipmentVO equipmentVO) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.updateEquipment(equipmentVO));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO setEquipmentOnline(int id) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.setEquipmentOnline(id));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO setEquipmentOffline(int id) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.setEquipmentOffline(id));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchOneEquipment(int id) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.searchEquipment(id));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchEquipmentList() {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.searchEquipmentList());
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getEquipmentNameByIp(String ip) {
        try {
            return ResponseVO.buildSuccess(equipmentMapper.getEquipmentNameByIp(ip));
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }
}
