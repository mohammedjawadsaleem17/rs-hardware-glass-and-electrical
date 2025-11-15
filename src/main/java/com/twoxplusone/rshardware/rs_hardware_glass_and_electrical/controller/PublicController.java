package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.controller;

import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity.SmartSettleUsers;
import com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Repository.PublicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private PublicRepository publicRepository;
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        SmartSettleUsers bismillah = new SmartSettleUsers("1", "Bismillah");
        publicRepository.save(bismillah);
        return new ResponseEntity<>("All Ok", HttpStatus.OK);
    }
}
