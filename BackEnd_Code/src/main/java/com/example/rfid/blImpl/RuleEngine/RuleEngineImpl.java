package com.example.rfid.blImpl.RuleEngine;

import com.example.rfid.bl.RuleEngine.RuleAction;
import com.example.rfid.bl.RuleEngine.RuleEngine;
import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.vo.DataRule;
import com.example.rfid.vo.EquipmentRule;
import com.example.rfid.vo.TransportInfoVO;
import org.kie.api.KieBase;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleEngineImpl implements RuleEngine {
    private final String rulePath_sleep = "src/main/resources/rules/sleep/";
    private final String rulePath_start = "src/main/resources/rules/start/";

    @Autowired
    private KieSession session;

    @Autowired
    private KieBase kieBase;

    @Autowired
    private RuleAction ruleAction;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    public List<DataRule> getDataRules() {
        List<DataRule> rules = new ArrayList<>();
        File file = new File(rulePath_sleep);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            String name = f.getName();
            if(name.endsWith("E.drl"))continue;
            String drl = readFile(f);
            rules.add(new DataRule(name.substring(0,name.length()-5),
                    getSqlFromDrl(drl),
                    getConditionFromDrl(drl),
                    getActionFromDrl(drl),
                    "sleep"));
        }

        file = new File(rulePath_start);
        fs = file.listFiles();
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            String name = f.getName();
            if(name.endsWith("E.drl"))continue;
            String drl = readFile(f);
            rules.add(new DataRule(name.substring(0,name.length()-5),
                    getSqlFromDrl(drl),
                    getConditionFromDrl(drl),
                    getActionFromDrl(drl),
                    "start"));
        }
        return rules;
    }

    @Override
    public List<EquipmentRule> getEquipmentRules() {
        List<EquipmentRule> rules = new ArrayList<>();
        File file = new File(rulePath_sleep);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            String name = f.getName();
            if(name.endsWith("D.drl"))continue;
            String drl = readFile(f);
            rules.add(new EquipmentRule(name.substring(0,name.length()-5),
                    getIpFromDrl(drl),
                    getConditionFromDrl(drl),
                    getActionFromDrl(drl),
                    "sleep"));
        }

        file = new File(rulePath_start);
        fs = file.listFiles();
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            String name = f.getName();
            if(name.endsWith("D.drl"))continue;
            String drl = readFile(f);
            rules.add(new EquipmentRule(name.substring(0,name.length()-5),
                    getIpFromDrl(drl),
                    getConditionFromDrl(drl),
                    getActionFromDrl(drl),
                    "start"));
        }
        return rules;
    }

    private String readFile(File file){
        StringBuilder drl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                drl.append(tempString).append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drl.toString();
    }

    private String getSqlFromDrl(String drl){
        String[] lines = drl.split(System.getProperty("line.separator"));
        StringBuilder sql = new StringBuilder("select ");
        StringBuilder selected = new StringBuilder();
        String ip = "default";
        StringBuilder where = new StringBuilder(" where ");
        boolean hasWhere = false;
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("transportVO.setTransportId")) selected.append("transportId,");
            else if(line.startsWith("transportVO.setBatchId")) selected.append("batchId,");
            else if(line.startsWith("transportVO.setCAS_id")) selected.append("casId,");
            else if(line.startsWith("transportVO.setNum")) selected.append("num,");
            else if(line.startsWith("transportVO.setState")) selected.append("state,");
            else if(line.startsWith("$p : TransportInfoVO")){
                if(line.contains("ip == \"")){
                    String subline = line.substring(line.indexOf("ip == \""));
                    ip = subline.split("\"")[1];
                }
                if(line.contains("transportId")){
                    if(hasWhere)where.append(" and ");
                    else hasWhere =true;
                    String[] parts = line.substring(line.indexOf("transportId")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    where.append(parts[0]);
                }
                if(line.contains("casId")){
                    if(hasWhere)where.append(" and ");
                    else hasWhere =true;
                    String[] parts = line.substring(line.indexOf("casId")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    where.append(parts[0]);
                }
                if(line.contains("batchId")){
                    if(hasWhere)where.append(" and ");
                    else hasWhere =true;
                    String[] parts = line.substring(line.indexOf("batchId")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    where.append(parts[0]);
                }
                if(line.contains("num")){
                    if(hasWhere)where.append(" and ");
                    else hasWhere =true;
                    String[] parts = line.substring(line.indexOf("num")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    where.append(parts[0]);
                }
            }
        }
        if(selected.length()!=0)
        selected.delete(selected.length()-1,selected.length());
        sql.append(selected);
        sql.append(" from ").append(ip);
        if(hasWhere)sql.append(where.toString().replaceAll("==","="));
        return sql.toString();
    }

    private String getIpFromDrl(String drl){
        String[] lines = drl.split(System.getProperty("line.separator"));
        String ip = "All";
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("$p : TransportInfoVO")){
                if(line.contains("ip == \"")){
                    line = line.substring(line.indexOf("ip == \""));
                    ip = line.split("\"")[1];
                }
            }
        }
        return ip;
    }

    private String getConditionFromDrl(String drl){
        String[] lines = drl.split(System.getProperty("line.separator"));
        StringBuilder condition = new StringBuilder();
        boolean hasCondition = false;
        for(String line:lines){
            line = line.trim();
            if(line.trim().startsWith("$p : TransportInfoVO")){
                if(line.contains("name == ")){
                    hasCondition = true;
                    String[] parts = line.substring(line.indexOf("name == ")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    condition.append(parts[0]);
                }
                if(line.contains("state == ")){
                    if(hasCondition)condition.append(" && ");
                    String[] parts = line.substring(line.indexOf("state == ")).split(" && ");
                    if(parts[0].endsWith(")"))parts[0] = parts[0].substring(0,parts[0].length()-1);
                    condition.append(parts[0]);
                }
                break;
            }
        }
        return condition.toString();
    }

    private String getActionFromDrl(String drl){
        String[] lines = drl.split(System.getProperty("line.separator"));
        StringBuilder action = new StringBuilder();
        boolean hasAction = false;
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("ruleAction")){
                if(line.startsWith("ruleAction.doNothing"))continue;
                if(line.startsWith("ruleAction.addTransport")){
                    if(hasAction)action.append(" && ");
                    else hasAction = true;
                    action.append("database");
                }
                if(line.startsWith("ruleAction.alert")){
                    if(hasAction)action.append(" && ");
                    else hasAction = true;
                    action.append("alert");
                }
                if(line.startsWith("ruleAction.log")){
                    if(hasAction)action.append(" && ");
                    else hasAction = true;
                    action.append("log");
                }
                if(line.startsWith("ruleAction.reScan")){
                    if(hasAction)action.append(" && ");
                    else hasAction = true;
                    action.append("reScan");
                }
                if(line.startsWith("ruleAction.shutDown")){
                    if(hasAction)action.append(" && ");
                    else hasAction = true;
                    action.append("shutDown");
                }
            }
        }
        return action.toString();
    }

    @Override
    public void addDataRule(String ruleName, String sql, String condition, String action) {
        String rule = generateRule(ruleName,sql,condition,action);
        String filePath = rulePath_sleep + ruleName+"D.drl";
        makeFile(filePath, rule);
    }

    @Override
    public void addEquipmentRule(String ruleName, String ip, String condition, String action) {
        String rule = generateRule(ruleName,ip,condition,action);
        String filePath = rulePath_sleep + ruleName+"E.drl";
        makeFile(filePath,rule);
    }

    private void makeFile(String path, String content){
        File myFilePath = new File(path);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(content);
            resultFile.close();
        }
        catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    private String generateRule(String ruleName, String sqlOrIp, String condition, String action){
        // package 随意即可
        StringBuilder rule = new StringBuilder("package rules.example.rfid.vo").append(System.getProperty("line.separator"));

        // import内容，包括TransportInfoVO，TransportVO，RuleAction
        rule.append("import com.example.rfid.vo.TransportInfoVO").append(System.getProperty("line.separator"));
        rule.append("import com.example.rfid.vo.TransportVO").append(System.getProperty("line.separator"));
        rule.append("import com.example.rfid.bl.RuleEngine.RuleAction").append(System.getProperty("line.separator"));

        // 方言为java
        rule.append("dialect \"java\"").append(System.getProperty("line.separator"));

        // 全局变量
        rule.append("global RuleAction ruleAction;").append(System.getProperty("line.separator"));

        // 具体rule
        rule.append("rule \"").append(ruleName).append("\"").append(System.getProperty("line.separator"));

        // 条件
        rule.append(generateWhen(sqlOrIp, condition));

        // 动作
        rule.append(generateAction(sqlOrIp, action));

        // end
        rule.append("end");

        return rule.toString();
    }

    private StringBuilder generateWhen(String sqlOrIp, String condition){
        StringBuilder when = new StringBuilder("    when" + System.getProperty("line.separator"));
        when.append("        $p : TransportInfoVO(");

        boolean hasCondition = false;
        if(sqlOrIp.startsWith("select")){
            //添加条件
            String ip = sqlOrIp.substring(sqlOrIp.indexOf("from")).split("\\s+")[1];
            if(!ip.equals("default")){
                hasCondition = true;
                when.append("ip == \"").append(ip).append("\"");
            }

            if(sqlOrIp.contains("where")){
                String[] where = sqlOrIp.substring(sqlOrIp.indexOf("where")+6).split("and");
                for(String c: where){
                    if(hasCondition) when.append(" && ");
                    else hasCondition = true;
                    when.append(c.replaceAll("=", "==").trim());
                }
            }
        }
        else{
            //添加条件
            if(!sqlOrIp.equals(""))
            when.append("ip == \"").append(sqlOrIp).append("\"");
            hasCondition = true;
        }
        String[] conditions = condition.split(" && ");
        for(String c: conditions){
            if(!c.equals("") && hasCondition) when.append(" && ");
            else hasCondition = true;
            when.append(c.replaceAll("=","==").trim());
        }

        when.append(")").append(System.getProperty("line.separator"));

        return when;
    }

    private StringBuilder generateAction(String sqlOrIp, String action){
        StringBuilder then = new StringBuilder("    then" + System.getProperty("line.separator"));
        if(action.equals("")){
            then.append("        ruleAction.doNothing();").append(System.getProperty("line.separator"));
        }
        else{
            then.append("        TransportVO transportVO = new TransportVO();").append(System.getProperty("line.separator"));
            if(sqlOrIp.startsWith("select")){
                String[] selected = sqlOrIp.split(" ")[1].split(",");
                if(selected[0].equals("*")){
                    then.append("        transportVO.setTransportId($p.getTransportId());").append(System.getProperty("line.separator"))
                            .append("        transportVO.setBatchId($p.getBatchId());").append(System.getProperty("line.separator"))
                            .append("        transportVO.setCAS_id($p.getCasId());").append(System.getProperty("line.separator"))
                            .append("        transportVO.setNum($p.getNum());").append(System.getProperty("line.separator"))
                            .append("        transportVO.setState($p.getState());").append(System.getProperty("line.separator"));
                }
                else{
                    for(String s:selected){
                        switch (s) {
                            case "transportId":
                                then.append("        transportVO.setTransportId($p.getTransportId());").append(System.getProperty("line.separator"));
                                break;
                            case "casId":
                                then.append("        transportVO.setCAS_id($p.getCasId());").append(System.getProperty("line.separator"));
                                break;
                            case "batchId":
                                then.append("        transportVO.setBatchId($p.getBatchId());").append(System.getProperty("line.separator"));
                                break;
                            case "num":
                                then.append("        transportVO.setNum($p.getNum());").append(System.getProperty("line.separator"));
                                break;
                            case "state":
                                then.append("        transportVO.setState($p.getState());").append(System.getProperty("line.separator"));
                                break;
                        }

                    }
                }
            }
            else {
                then.append("        transportVO.setTransportId($p.getTransportId());").append(System.getProperty("line.separator"))
                        .append("        transportVO.setBatchId($p.getBatchId());").append(System.getProperty("line.separator"))
                        .append("        transportVO.setCAS_id($p.getCasId());").append(System.getProperty("line.separator"))
                        .append("        transportVO.setNum($p.getNum());").append(System.getProperty("line.separator"))
                        .append("        transportVO.setState($p.getState());").append(System.getProperty("line.separator"));

            }

            String[] actions = action.split(" && ");
            for(String a:actions){
                switch (a){
                    case "database":{
                        then.append("        ruleAction.addTransport(transportVO);").append(System.getProperty("line.separator"));
                        break;
                    }
                    case "alert":{
                        then.append("        ruleAction.alert(transportVO);").append(System.getProperty("line.separator"));
                        break;
                    }
                    case "log":{
                        then.append("        ruleAction.log(transportVO);").append(System.getProperty("line.separator"));
                        break;
                    }
                    case "reScan":{
                        then.append("        ruleAction.reScan($p);").append(System.getProperty("line.separator"));
                        break;
                    }
                    case "shutDown":{
                        then.append("        ruleAction.shutDown($p);").append(System.getProperty("line.separator"));
                        break;
                    }
                }
            }
        }

        return then;
    }

    @Override
    public void startDataRule(String ruleName) {
        try
        {
            File file=new File(rulePath_sleep+ruleName+"D.drl"); //源文件
            if (file.renameTo(new File(rulePath_start+ruleName+"D.drl"))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            }
            else
            {
                System.out.println("File is failed to move !");//输出移动失败
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void startEquipmentRule(String ruleName) {
        try
        {
            File file=new File(rulePath_sleep+ruleName+"E.drl"); //源文件
            if (file.renameTo(new File(rulePath_start+ruleName+"E.drl"))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            }
            else
            {
                System.out.println("File is failed to move !");//输出移动失败
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyDataRule(String ruleName, String sql, String condition, String action) {
        removeDataRule(ruleName);
        addDataRule(ruleName, sql, condition, action);
    }

    @Override
    public void modifyEquipmentRule(String ruleName, String ip, String condition, String action) {
        removeEquipmentRule(ruleName);
        addEquipmentRule(ruleName, ip, condition, action);
    }

    @Override
    public void stopDataRule(String ruleName) {
        try
        {
            File file=new File(rulePath_start+ruleName+"D.drl"); //源文件
            if (file.renameTo(new File(rulePath_sleep+ruleName+"D.drl"))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            }
            else
            {
                System.out.println("File is failed to move !");//输出移动失败
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stopEquipmentRule(String ruleName) {
        try
        {
            File file=new File(rulePath_start+ruleName+"E.drl"); //源文件
            if (file.renameTo(new File(rulePath_sleep+ruleName+"E.drl"))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            }
            else
            {
                System.out.println("File is failed to move !");//输出移动失败
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void removeDataRule(String ruleName) {
        try{
            File file = new File(rulePath_sleep + ruleName + "D.drl");
            if(file.delete()){
                System.out.println(file.getName() + " 规则已被删除！");
            }else{
                System.out.println("规则删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeEquipmentRule(String ruleName) {
        try{
            File file = new File(rulePath_sleep + ruleName + "E.drl");
            if(file.delete()){
                System.out.println(file.getName() + " 规则已被删除！");
            }else{
                System.out.println("规则删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void fireRules(List<TransportInfoVO> transportInfos) {
        for (TransportInfoVO o : transportInfos){
            List<String> equipmentNameList = (List<String>) equipmentService.getEquipmentNameByIp(o.getIp()).getContent();
            if(equipmentNameList.contains(o.getName())){
                session.insert(o);
            }
            else System.err.println("invalid transport Information : ip = " + o.getIp() + " and name = " + o.getName());
        }
        session.setGlobal("ruleAction", ruleAction);
        session.fireAllRules();
    }

}
