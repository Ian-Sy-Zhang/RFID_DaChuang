package com.example.rfid.controller.RuleEngine;

import com.example.rfid.bl.RuleEngine.RuleAction;
import com.example.rfid.bl.RuleEngine.RuleEngine;
import com.example.rfid.vo.DataRule;
import com.example.rfid.vo.EquipmentRule;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ruleEngine")
public class RuleEngineController {

    @Autowired
    RuleEngine ruleEngine;

    @RequestMapping("/getDataRules")
    public ResponseVO getDataRules(){
        try {
            return ResponseVO.buildSuccess(ruleEngine.getDataRules());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("数据转发规则获取失败");
        }
    }

    @RequestMapping("/getEquipmentRules")
    public ResponseVO getEquipmentRules(){
        try {
            return ResponseVO.buildSuccess(ruleEngine.getEquipmentRules());
        }
        catch (Exception e){
            return ResponseVO.buildFailure("设备联动规则获取失败");
        }
    }

    @RequestMapping("/addDataRule")
    public ResponseVO addDataRule(@RequestBody DataRule dataRule){
        try {
            ruleEngine.addDataRule(
                    dataRule.getName(),
                    dataRule.getSql(),
                    dataRule.getCondition(),
                    dataRule.getAction());
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("添加数据转发规则失败");
        }
    }

    @RequestMapping("/addEquipmentRule")
    public ResponseVO addEquipmentRule(@RequestBody EquipmentRule equipmentRule){
        try {
            ruleEngine.addEquipmentRule(
                    equipmentRule.getName(),
                    equipmentRule.getIp(),
                    equipmentRule.getCondition(),
                    equipmentRule.getAction());
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("添加设备联动规则失败");
        }
    }

    @RequestMapping("/startDataRule/{name}")
    public ResponseVO startDataRule(@PathVariable String name){
        try {
            ruleEngine.startDataRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("启动数据转发规则失败");
        }
    }

    @RequestMapping("/startEquipmentRule/{name}")
    public ResponseVO startEquipmentRule(@PathVariable String name){
        try {
            ruleEngine.startEquipmentRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("启动设备联动规则失败");
        }
    }

    @RequestMapping("/modifyDataRule")
    public ResponseVO modifyDataRule(@RequestBody DataRule dataRule){
        try {
            ruleEngine.modifyDataRule(
                    dataRule.getName(),
                    dataRule.getSql(),
                    dataRule.getCondition(),
                    dataRule.getAction());
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("修改数据转发规则失败");
        }
    }

    @RequestMapping("/modifyEquipmentRule")
    public ResponseVO modifyEquipmentRule(@RequestBody EquipmentRule equipmentRule){
        try {
            ruleEngine.modifyEquipmentRule(
                    equipmentRule.getName(),
                    equipmentRule.getIp(),
                    equipmentRule.getCondition(),
                    equipmentRule.getAction());
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("修改设备联动规则失败");
        }
    }

    @RequestMapping("/stopDataRule/{name}")
    public ResponseVO stopDataRule(@PathVariable String name){
        try {
            ruleEngine.stopDataRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("停止数据转发规则失败");
        }
    }

    @RequestMapping("/stopEquipmentRule/{name}")
    public ResponseVO stopEquipmentRule(@PathVariable String name){
        try {
            ruleEngine.stopEquipmentRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("停止设备联动规则失败");
        }
    }

    @RequestMapping("/removeDataRule/{name}")
    public ResponseVO removeDataRule(@PathVariable String name){
        try {
            ruleEngine.removeDataRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("停止数据转发规则失败");
        }
    }

    @RequestMapping("/removeEquipmentRule/{name}")
    public ResponseVO removeEquipmentRule(@PathVariable String name){
        try {
            ruleEngine.removeEquipmentRule(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            return ResponseVO.buildFailure("停止设备联动规则失败");
        }
    }

    @RequestMapping("/fireRules")
    public ResponseVO fireRules(@RequestBody List<TransportInfoVO> transportInfos){
        try {
            ruleEngine.fireRules(transportInfos);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("执行规则失败");
        }
    }

}
