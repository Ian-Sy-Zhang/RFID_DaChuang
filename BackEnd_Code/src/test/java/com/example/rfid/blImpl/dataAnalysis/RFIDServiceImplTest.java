package com.example.rfid.blImpl.dataAnalysis;

import com.example.rfid.RFIDApplication;
import com.example.rfid.bl.dataAnalysis.RFIDService;
import com.example.rfid.vo.ChemcasVO;
import com.example.rfid.vo.DangerousBatchVO;
import com.example.rfid.vo.ResponseVO;
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
public class RFIDServiceImplTest {
    @Autowired
    RFIDService rfidService;

    @Test
    public void mostDangerousBatch() {
        ResponseVO re = rfidService.mostDangerousBatch();
    }

    @Test
    public void mostDangerousChem() {
        ResponseVO re = rfidService.mostDangerousChem();
    }
}