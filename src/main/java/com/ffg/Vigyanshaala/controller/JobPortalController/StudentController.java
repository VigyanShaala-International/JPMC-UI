package com.ffg.Vigyanshaala.controller.JobPortalController;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Company;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.AdminServices;
import com.ffg.Vigyanshaala.service.JobPortalService.StudentServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jobPortal/student")
public class StudentController {
    @Autowired
    StudentServices studentServices;

    @ApiOperation(value = "Health Check for the admin route", notes = "Checks whether the route /jobPortal/admin/ is healthy or not")
    @GetMapping("/")
    String healthCheck(){
        return "This student route is healthy";
    }



    @ApiOperation(value = "Get all jobs from the job table without a filter", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all jobs.")
    @GetMapping(value="/getJobs", produces="application/json")
    ResponseEntity<Response> getAllJobs(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= studentServices.getAllJobs();
        }catch(Exception e){
            System.out.println("Exception occurred while getting all the jobs "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting all the jobs"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
