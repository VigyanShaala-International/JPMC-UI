package com.ffg.Vigyanshaala.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vigyaanshalaPdfGenerator")
public class PdfGeneratorController {

    @RequestMapping("/generatePdf")
    String def(){
        return "Hello generated pdf";
    }
}
