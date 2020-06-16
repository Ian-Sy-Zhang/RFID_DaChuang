package com.example.rfid.blImpl.dataAdmin;

import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Override
    public ResponseVO getEquipmentNameByIp(String ip) {
        List<String> list = new ArrayList<>();
        list.add("123");
        return ResponseVO.buildSuccess(list);
    }
}
