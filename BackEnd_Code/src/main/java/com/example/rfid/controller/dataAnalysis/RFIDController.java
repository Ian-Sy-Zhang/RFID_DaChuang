package com.example.rfid.controller.dataAnalysis;

import com.example.rfid.bl.dataAnalysis.RFIDService;
import com.example.rfid.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataAnalysis")
public class RFIDController {
    @Autowired
    RFIDService rfidService;

    @RequestMapping("/getDangerousBatchList")
    public ResponseVO getDangerousBatchList(){
        return rfidService.mostDangerousBatch();
    }

    @RequestMapping("/getDangerousChemList")
    public ResponseVO getDangerousChemList(){
        return rfidService.mostDangerousChem();
    }

}
