package com.example.rfid.bl.RuleEngine;

import com.example.rfid.vo.DataRule;
import com.example.rfid.vo.EquipmentRule;
import com.example.rfid.vo.TransportInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RuleEngine {
    List<DataRule> getDataRules();

    List<EquipmentRule> getEquipmentRules();

    void addDataRule(String ruleName, String sql, String condition, String action);

    void addEquipmentRule(String ruleName, String ip, String condition, String action);

    void startDataRule(String ruleName);

    void startEquipmentRule(String ruleName);

    void modifyDataRule(String ruleName, String sql, String condition, String action);

    void modifyEquipmentRule(String ruleName, String ip, String condition, String action);

    void stopDataRule(String ruleName);

    void stopEquipmentRule(String ruleName);

    void removeDataRule(String ruleName);

    void removeEquipmentRule(String ruleName);

    void fireRules(List<TransportInfoVO> transportInfos);


}
