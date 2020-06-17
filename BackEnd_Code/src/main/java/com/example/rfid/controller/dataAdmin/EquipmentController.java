package com.example.rfid.controller.dataAdmin;

import com.example.rfid.bl.dataAdmin.EquipmentService;
import com.example.rfid.vo.EquipmentVO;
import com.example.rfid.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataAdmin/Equipment")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping("/addEquipment")
    public ResponseVO addEquipment(@RequestBody EquipmentVO equipmentVO){
        return equipmentService.addEquipment(equipmentVO);
    }

    @GetMapping("/removeEquipment/{id}")
    public ResponseVO removeEquipment(@PathVariable int id){
        return equipmentService.removeEquipment(id);
    }

    @PostMapping("/updateEquipment")
    public ResponseVO updateEquipment(@RequestBody EquipmentVO equipmentVO){
        return equipmentService.updateEquipment(equipmentVO);
    }

    @GetMapping("/searchOneEquipment/{id}")
    public ResponseVO searchOneEquipment(@PathVariable int id){
        return equipmentService.searchOneEquipment(id);
    }

    @RequestMapping("/searchEquipmentList")
    public ResponseVO searchEquipmentList(){
        return equipmentService.searchEquipmentList();
    }

}
