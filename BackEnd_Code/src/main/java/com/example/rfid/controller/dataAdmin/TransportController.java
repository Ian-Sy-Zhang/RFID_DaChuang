package com.example.rfid.controller.dataAdmin;

import com.example.rfid.bl.dataAdmin.TransportService;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.vo.TransportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransportController {
    
    @Autowired
    TransportService transportService;
    
    @PostMapping("/addEquipment")
    public ResponseVO addEquipment(@RequestBody TransportVO transportVO){
        return transportService.addTransport(transportVO);
    }

    @GetMapping("/removeEquipment/{id}")
    public ResponseVO removeEquipment(@PathVariable int id){
        return transportService.removeTransport(id);
    }

    @PostMapping("/updateEquipment")
    public ResponseVO updateEquipment(@RequestBody TransportVO transportVO){
        return transportService.updateTransport(transportVO);
    }

    @GetMapping("/searchOneEquipment/{id}")
    public ResponseVO searchOneEquipment(@PathVariable int id){
        return transportService.searchOneTransport(id);
    }

    @RequestMapping("/searchEquipmentList")
    public ResponseVO searchEquipmentList(){
        return transportService.searchTransportList();
    }

}
