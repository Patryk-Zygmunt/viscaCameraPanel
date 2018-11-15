package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    Main main;

    @GetMapping("/command")
    public String command(@RequestParam String cmd){
        return main.command(cmd);
    }



}
