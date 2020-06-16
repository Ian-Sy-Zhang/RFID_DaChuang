package com.example.rfid.blImpl.RuleEngine;

import com.example.rfid.bl.RuleEngine.RuleEngine;
import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.vo.People;
import com.example.rfid.vo.Rule;
import com.example.rfid.vo.TransportInfoVO;
import org.kie.api.KieBase;
import org.kie.api.builder.*;
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
    private KieFileSystem kieFileSystem;


    @Override
    public List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
        File file = new File(rulePath_sleep);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            Rule rule = new Rule();
            rule.setState("sleep");
            rule.setName(f.getName());
            readRule(rule, f);
            rules.add(rule);
        }

        file = new File(rulePath_start);
        fs = file.listFiles();
        assert fs != null;
        for(File f:fs){					//遍历File[]数组
            Rule rule = new Rule();
            rule.setState("running");
            rule.setName(f.getName());
            readRule(rule, f);
            rules.add(rule);
        }


        return rules;
    }

    private void readRule(Rule rule, File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                line++;
                if(tempString.startsWith("    when")){
                    tempString = reader.readLine();
                    rule.setCondition(tempString.trim());
                }
                else if(tempString.startsWith("    then")){
                    tempString = reader.readLine();
                    rule.setAction(tempString.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRule(String ruleName, String bean, String condition, String action) {

        StringBuilder rule = new StringBuilder("package rules.example.rfid.vo").append(System.getProperty("line.separator"));
        rule.append("import com.example.rfid.vo.").append(bean).append(System.getProperty("line.separator"));
        rule.append("dialect \"java\"").append(System.getProperty("line.separator"));
        rule.append("rule \"").append(ruleName).append("\"").append(System.getProperty("line.separator"));
        rule.append("   when").append(System.getProperty("line.separator"));
        rule.append("       $p : ").append(bean).append("(").append(condition).append(")").append(System.getProperty("line.separator"));
        rule.append("   then").append(System.getProperty("line.separator"));
        rule.append("       ").append(action).append(System.getProperty("line.separator"));
        rule.append("end");

        String filePath = rulePath_sleep + ruleName+".drl";

        File myFilePath = new File(filePath);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(rule);
            resultFile.flush();
            resultFile.close();
        }
        catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    @Override
    public void startRule(String ruleName) {
        try
        {
            File file=new File(rulePath_sleep+ruleName+".drl"); //源文件
            if (file.renameTo(new File(rulePath_start+ruleName+".drl"))) //源文件移动至目标文件目录
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
    public void modifyRule(String ruleName, String bean, String condition, String action) {
        removeRule(ruleName);
        addRule(ruleName, bean, condition, action);
    }

    @Override
    public void stopRule(String ruleName) {
        try
        {
            File file=new File(rulePath_start+ruleName+".drl"); //源文件
            if (file.renameTo(new File(rulePath_sleep+ruleName+".drl"))) //源文件移动至目标文件目录
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
    public void removeRule(String ruleName) {
        try{
            File file = new File(rulePath_sleep + ruleName + ".drl");
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
//        session.setGlobal("transportService", transportService);
//        for (TransportInfoVO o : transportInfos){
//            List<String> equipmentNameList = (List<String>) equipmentService.getEquipmentNameByIp(o.getIp()).getContent();
//            if(equipmentNameList.contains(o.getName())){
//                session.insert(o);
//            }
//            else System.err.println("invalid transport Information : ip = " + o.getIp() + " and name = " + o.getName());
//        }
//        session.fireAllRules();
        for (Object o : transportInfos)session.insert(o);
        session.fireAllRules();
    }

}
