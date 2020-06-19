package com.example.rfid.controller.connection;


import com.example.rfid.bl.Connection.ConnectionService;
import com.example.rfid.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Connection")
public class ConnectionController {

    @Autowired
    ConnectionService connectionService;



    @GetMapping("/connect/{id}")
    public ResponseVO connect(@PathVariable int id){
        return connectionService.connect(id);
    }

    @GetMapping("/close/{id}")
    public ResponseVO close(@PathVariable int id){
        return connectionService.close(id);
    }

}
