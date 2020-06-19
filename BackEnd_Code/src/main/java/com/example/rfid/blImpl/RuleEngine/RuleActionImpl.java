package com.example.rfid.blImpl.RuleEngine;

import com.example.rfid.bl.RuleEngine.RuleAction;
import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/RFID/alert";
        String response = restTemplate.postForObject(url,transportVO,String.class);
        System.out.println(response);
    }

    @Override
    public void log(TransportVO transportVO) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/RFID/log";
        String response = restTemplate.postForObject(url,transportVO,String.class);
        System.out.println(response);
    }

    @Override
    public void doNothing() {
        System.out.println("do nothing");
    }

    @Override
    public void reScan(TransportInfoVO transportInfoVO) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/RFID/reScan";
        String response = restTemplate.postForObject(url,transportInfoVO,String.class);
        System.out.println(response);
    }

    @Override
    public void shutDown(TransportInfoVO transportInfoVO) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/RFID/shutDown";
        String response = restTemplate.postForObject(url,transportInfoVO,String.class);
        System.out.println(response);
    }
}
