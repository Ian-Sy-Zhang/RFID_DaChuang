package com.example.rfid.bl.dataAdmin;

import com.example.rfid.RFIDApplication;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RFIDApplication.class)
public class TransportServiceTest {

    @Autowired
    TransportService transportService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        transportService = null;
    }

    @Test
    public void addTransport() {
        TransportVO transportVO = new TransportVO(3,1,2,1,1);
        ResponseVO responseVO = transportService.addTransport(transportVO);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void removeTransport() {
        ResponseVO responseVO = transportService.removeTransport(3);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void updateTransport() {
        TransportVO transportVO = new TransportVO(1,2,3,4,5);
        ResponseVO responseVO = transportService.updateTransport(transportVO);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void searchOneTransport() {
        ResponseVO responseVO = transportService.searchOneTransport(1);
        List<TransportVO> list = (List<TransportVO>) responseVO.getContent();
        assertEquals(1,list.size());
    }

    @Test
    public void searchTransportList() {
        ResponseVO responseVO = transportService.searchTransportList();
        List<TransportVO> list = (List<TransportVO>) responseVO.getContent();
        assertEquals(3,list.size());
    }
}