package com.example.rfid.blImpl.RuleEngine;

import com.example.rfid.bl.RuleEngine.RuleAction;
import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleActionImpl implements RuleAction {

    @Autowired
    TransportService transportService;

    @Override
    public void addTransport(TransportVO transportVO) {
        transportService.addTransport(transportVO);
    }

    @Override
    public void alert(TransportVO transportVO) {
        System.out.println("alert");
    }

    @Override
    public void log(TransportVO transportVO) {
        System.out.println("log");
    }

    @Override
    public void doNothing() {
        System.out.println("do nothing");
    }

    @Override
    public void reScan(TransportInfoVO transportInfoVO) {
        System.out.println("machine "+transportInfoVO.getIp()+" is rescanning.");
    }

    @Override
    public void shutDown(TransportInfoVO transportInfoVO) {
        System.out.println("machine "+transportInfoVO.getIp()+" is shutting down.");
    }
}
