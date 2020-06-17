package com.example.rfid.bl.RuleEngine;


import com.example.rfid.RFIDApplication;
import com.example.rfid.vo.TransportInfoVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RFIDApplication.class)
public class RuleEngineTest2 {

    @Autowired
    private RuleEngine ruleEngine;

    @Autowired
    private KieSession session;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRules() {
//        ruleEngine.getRules();
    }

    @Test
    public void addRule() {
//        ruleEngine.addRule("Transport","TransportInfoVO","TransportInfoVO(ip == globalIP)","TransportMapper.addTransInfo()");
    }

    @Test
    public void startRule() {
    }

    @Test
    public void modifyRule() {
    }

    @Test
    public void stopRule() {
    }

    @Test
    public void removeRule() {
    }

    @Test
    public void fireRules() {
        List<TransportInfoVO> list = new ArrayList<>();
        list.add(new TransportInfoVO("123","132",0,0,0,0,0));
        ruleEngine.fireRules(list);

    }
}