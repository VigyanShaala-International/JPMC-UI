package com.ffg.Vigyanshaala.controller.JobPortalController;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Company;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobLocation;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobTitle;
import com.ffg.Vigyanshaala.model.JobPortal.JobDetails;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.AdminServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*The following Admin Controller contains all the get and post calls for the Admin tasks
POST : saving the company details, job titles, job locations, job postings
GET : get company details,job titles, job locations
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jobPortal/admin")
public class AdminController {

    @Autowired
    AdminServices adminServices;

    @ApiOperation(value = "Health Check for the admin route", notes = "Checks whether the route /jobPortal/admin/ is healthy or not")
    @GetMapping("/")
    String healthCheck(){
        return "This admin route is healthy";
    }

    @ApiOperation(value = "Add company details in the Company table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/addCompany",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody String company){
        Response response=new Response();
        try{
            System.out.println("The company name is : "+ company);
            response= adminServices.addCompany(company);
        }catch(Exception e){
            System.out.println("Exception occurred while adding company name "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding company name "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get company list from the Company table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all companies.")
    @GetMapping(value="/getCompanyList", produces="application/json")
    ResponseEntity<Response> getCompanyList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getCompanyList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting company name list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting company name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job title in the JobTitle table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/addJobTitle",consumes="application/json", produces="application/json")
    Response addJobTitle(@RequestBody String jobTitle){
        Response response=new Response();
        try{
            System.out.println("The company name list is : "+jobTitle.toString());
            response= adminServices.addJobTitle(jobTitle);
        }catch(Exception e){
            System.out.println("Exception occurred while adding job title "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job title "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job title list from the JobTitle table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job titles.")
    @GetMapping(value="/getJobTitleList", produces="application/json")
    ResponseEntity<Response> getJobTitleList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobTitleList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting job title list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job title list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }



    @ApiOperation(value = "Add job location in the JobLocation table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/addJobLocation",consumes="application/json", produces="application/json")
    Response addJobLocationList(@RequestBody String jobLocation){
        Response response=new Response();
        try{
            System.out.println("The company name list is : "+jobLocation.toString());
            response= adminServices.addJobLocation(jobLocation);
        }catch(Exception e){
            System.out.println("Exception occurred while adding job location "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job location "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job location list from the JobLocation table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job locations.")
    @GetMapping(value="/getJobLocationList", produces="application/json")
    ResponseEntity<Response> getJobLocationList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobLocationList();
        }catch(Exception e){
            System.out.println("Exception occurred while getting job location list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job location list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Add job in the job table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/createJob",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody Job job){
        Response response=new Response();
        try{
            System.out.println("The Job detail is : "+ job.toString());
            response= adminServices.createJob(job);
        }catch(Exception e){
            System.out.println("Exception occurred while adding job  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  "+e);
        }
        return response;
    }
}
