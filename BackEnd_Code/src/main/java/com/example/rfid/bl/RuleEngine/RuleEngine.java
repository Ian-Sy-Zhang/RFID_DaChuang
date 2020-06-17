package com.example.rfid.bl.RuleEngine;

import com.example.rfid.vo.Rule;
import com.example.rfid.vo.TransportInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RuleEngine {
    List<Rule> getRules();

    void addRule(String ruleName, String bean, String condition, String action);

    void startRule(String ruleName);

    void modifyRule(String ruleName, String bean, String condition, String action);

    void stopRule(String ruleName);

    void removeRule(String ruleName);

    void fireRules(List<TransportInfoVO> transportInfos);


}
