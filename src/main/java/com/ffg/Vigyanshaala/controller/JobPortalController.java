package com.ffg.Vigyanshaala.controller;

import com.ffg.Vigyanshaala.entity.JobPosting;
import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vigyanshaalaJobPortal")
public class JobPortalController {

    @Autowired
    JobPortalServices jobPortalServices;

    @RequestMapping("/healthCheck")
    String healthCheck(){
        return "This is healthy";
    }
    @PostMapping(value="/addCompanyNameList",consumes="application/json", produces="application/json")
    Response addCompanyNameList(@RequestBody List<String> companyNameList){
        Response response=new Response();
        try{
            System.out.println("The company name list is : "+companyNameList.toString());
            response=jobPortalServices.addCompanyNameList(companyNameList);
        }catch(Exception e){
            System.out.println("Exception occurred while adding company name "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding company name "+e);
        }
        return response;
    }

    @GetMapping(value="/getCompanyNameList", produces="application/json")
    ResponseEntity<Response> getCompanyNameList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity=jobPortalServices.getCompanyNameList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting company name list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting company name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @PostMapping(value="/addJobTitleList",consumes="application/json", produces="application/json")
    Response addJobTitleList(@RequestBody List<String> jobTitleList){
        Response response=new Response();
        try{
            System.out.println("The company name list is : "+jobTitleList.toString());
            response=jobPortalServices.addJobTitleList(jobTitleList);
        }catch(Exception e){
            System.out.println("Exception occurred while adding job title "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job title "+e);
        }
        return response;
    }

    @GetMapping(value="/getJobTitleList", produces="application/json")
    ResponseEntity<Response> getJobTitleList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity=jobPortalServices.getJobTitleList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting job title list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job title list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }


    @PostMapping(value="/addJobLocationList",consumes="application/json", produces="application/json")
    Response addJobLocationList(@RequestBody List<String> jobLocationList){
        Response response=new Response();
        try{
            System.out.println("The company name list is : "+jobLocationList.toString());
            response=jobPortalServices.addJobLocationList(jobLocationList);
        }catch(Exception e){
            System.out.println("Exception occurred while adding job location "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job location "+e);
        }
        return response;
    }

    @GetMapping(value="/getJobLocationList", produces="application/json")
    ResponseEntity<Response> getJobLocationList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity=jobPortalServices.getJobLocationList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting job location list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job location list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
    @PostMapping (value = "/createJob",consumes = "application/json",produces = "application/json")
    Response createJob(@RequestBody JobDetails jobDetails){

        Response response = new Response();
        try {
            System.out.println("Job Details are : " + jobDetails);
            response = jobPortalServices.createJobImpl(jobDetails);
        }
        catch(Exception e)
        {
            System.out.println("Exception occurred while createJobImpl "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while createJobImp "+e);


        }
        return response;
    }
    @PostMapping(value="/deleteExpiredJobs", produces="application/json")
    ArrayList<JobPosting> deleteExpiredJobs(){
        ArrayList<JobPosting> result = null;
        try{
            Date date = new Date();
            result=jobPortalServices.deleteExpiredJobs(date);
            System.out.println(result);
        }catch(Exception e){
            System.out.println("Exception occurred while deleting job "+e);
//            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setStatusMessage("Exception occured while deleting job "+e);
        }
        return result;
    }
}
