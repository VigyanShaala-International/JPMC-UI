package com.ffg.Vigyanshaala.controller.JobPortalController;

import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.SystemServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jobPortal/system")
public class SystemController {

    @Autowired
    SystemServices systemServices;

    @ApiOperation(value = "Health Check for the admin route", notes = "Checks whether the route /jobPortal/system/ is healthy or not")
    @GetMapping("/")
    String healthCheck(){
        return "This system route is healthy";
    }

    @PostMapping(value="/deleteExpiredJobs", produces="application/json")
    ResponseEntity<Response> deleteExpiredJobs() {
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         Date date = new Date();
            responseEntity= systemServices.deleteExpiredJobs(formatter.format(date));
        }catch(Exception e){
            System.out.println("Exception occurred while deleting job "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while deleting job"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
