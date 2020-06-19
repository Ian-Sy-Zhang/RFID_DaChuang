package com.example.rfid.bl.RuleEngine;


import com.example.rfid.RFIDApplication;
import com.example.rfid.vo.DataRule;
import com.example.rfid.vo.EquipmentRule;
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
public class RuleEngineTest {

    @Autowired
    private RuleEngine ruleEngine;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        ruleEngine = null;
    }


    @Test
    public void getDataRules() {
        List<DataRule> rules = ruleEngine.getDataRules();
        System.out.println(rules.size());
    }

    @Test
    public void getEquipmentRules() {
        List<EquipmentRule> rules = ruleEngine.getEquipmentRules();
        System.out.println(rules.size());
    }

    @Test
    public void addDataRule() {
        String sql = "select casId from 127.0.0.1 where num > 20";
        String condition = "state = 1 && name = \"52\"";
        String action = "database && log";
        ruleEngine.addDataRule("test",sql,condition,action);
    }

    @Test
    public void addEquipmentRule() {
        String ip = "127.0.0.1";
        String condition = "state = 1";
        String action = "database && log";
        ruleEngine.addEquipmentRule("test",ip,condition,action);
    }

    @Test
    public void startRule() {
        ruleEngine.startDataRule("test");
        ruleEngine.startEquipmentRule("test");
    }

    @Test
    public void modifyDataRule() {
        String sql = "select * from default";
        String condition = "state = 1 && ip = \"192.168.1.1\"";
        String action = "alert";
        ruleEngine.modifyDataRule("Test",sql,condition,action);
    }

    @Test
    public void modifyEquipmentRule() {
    }

    @Test
    public void stopRule() {
        ruleEngine.stopDataRule("test");
    }

    @Test
    public void removeRule() {
        ruleEngine.removeEquipmentRule("test");
    }

    @Test
    public void fireRules() {
//        List<TransportInfoVO> list = new ArrayList<>();
//        list.add(new TransportInfoVO("123","192.168.1.1",0,1,0,50,1));
        List<TransportInfoVO> list  = new ArrayList<>();
        list.add(new TransportInfoVO("110","192.168.1.1",5,0,20,30,1));
        ruleEngine.fireRules(list);

    }

}