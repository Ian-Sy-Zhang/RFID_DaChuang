package com.example.rfid.data.dataAdmin;
import com.example.rfid.RFIDApplication;
import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.DangerousBatchVO;
import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.TransportVO;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RFIDApplication.class)
public class EquipmentMapperTest {

    @Resource
    EquipmentMapper equipmentMapper;

    @Test
    public void addEquipment(){
        EquipmentVO equipmentVO = new EquipmentVO();
        equipmentVO.setAbs("shdjkf");
        equipmentVO.setConnectionType("http");
        equipmentVO.setIp("172.168.1.1");
        equipmentVO.setName("sdhjfk");
        equipmentVO.setStatus("断开链接");

        int re = equipmentMapper.addEquipment(equipmentVO);

    }

    @Test
    public void onoffLine(){
        int re = equipmentMapper.setEquipmentOnline(1);
    }

    @Test
    public void removeEquipment(){
        int re = equipmentMapper.removeEquipment(1);
    }

    @Test
    public void updateEquipment(){
        EquipmentVO equipmentVO = new EquipmentVO();
        equipmentVO.setId(1);
        equipmentVO.setAbs("shdjkf");
        equipmentVO.setConnectionType("http");
        equipmentVO.setIp("172.168.1.1");
        equipmentVO.setName("sdhjfk");
        equipmentVO.setStatus("链接正常");

        int re = equipmentMapper.updateEquipment(equipmentVO);
    }

    @Test
    public void searchEquipment(){
        EquipmentVO re = equipmentMapper.searchEquipment(1);
        System.out.print(re.toString());
    }

    @Test
    public void searchEquipmentList(){
        ArrayList<EquipmentVO> re = equipmentMapper.searchEquipmentList();

        for (EquipmentVO equipmentVO : re){
            System.out.print(re.toString());
        }
    }


}