package com.example.rfid.data.dataAnalysis;

import com.example.rfid.RFIDApplication;
import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.DangerousBatchVO;
import com.example.rfid.vo.TransportVO;
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
public class RFIDMapperTest {

    @Resource
    RFIDMapper rfidMapper;

    @Test
    public void mostDangerousBatch(){
        ArrayList<DangerousBatchVO> re = rfidMapper.mostDangerousBatch();
        System.out.println(re.size());
    }

    @Test
    public void mostDangerousChem(){
        ArrayList<ChemcasVO> re = rfidMapper.mostDangerousChem();
        System.out.println(re.size());
    }

}