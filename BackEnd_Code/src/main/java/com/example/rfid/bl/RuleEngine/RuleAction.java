package com.example.rfid.bl.RuleEngine;

import com.example.rfid.vo.TransportInfoVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.stereotype.Service;

@Service
public interface RuleAction {
    void addTransport(TransportVO transportVO);

    void alert(TransportVO transportVO);

    void log(TransportVO transportVO);

    void doNothing();

    void reScan(TransportInfoVO transportInfoVO);

    void shutDown(TransportInfoVO transportInfoVO);
}
