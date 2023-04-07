package com.ffg.Vigyanshaala.controller;

import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vigyanshaalaJobPortal")
public class JobPortalController {

    @Autowired
    JobPortalServices jobPortalServices;
    @RequestMapping("/createJob")
    Response createJob(JobDetails jobDetails){

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
}
