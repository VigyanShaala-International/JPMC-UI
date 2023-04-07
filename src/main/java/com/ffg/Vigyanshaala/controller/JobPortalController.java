package com.ffg.Vigyanshaala.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vigyanshaalaJobPortal")
public class JobPortalController {

    @RequestMapping("/createJob")
    String def(){
        return "Hello created Job";
    }
}
