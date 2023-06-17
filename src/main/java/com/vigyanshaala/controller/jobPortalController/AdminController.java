package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.repository.jobPortalRepository.CustomJobRepositoryImpl;
import com.vigyanshaala.repository.jobPortalRepository.JobFilter;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.AdminServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The following Admin Controller contains all the get and post calls for the Admin tasks
 * POST : saving the company details, job titles, job locations, job postings
 * GET : get company details,job titles, job locations
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/admin")
@Slf4j
public class AdminController {

    @Autowired
    AdminServices adminServices;

    @Autowired
    CustomJobRepositoryImpl customJobRepository;

    @ApiOperation(value = "Add work mode in the WorkMode table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value = "/workmode", produces = "application/json")
    Response addWorkmode(@RequestBody String workmode) {
        Response response = new Response();
        try {
            log.info("The work mode is : {}", workmode);
            response = adminServices.addWorkmode(workmode);
        } catch (Exception e) {
            log.error("Exception occurred while adding workmode name ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding workmode name " + e);
        }
        return response;
    }

    @ApiOperation(value = "Get workmode list from the Workmode table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all workmodes.")
    @GetMapping(value="/workmode/all", produces="application/json")
    ResponseEntity<Response> getWorkmodeList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getWorkmodeList();
        }catch(Exception e){
            log.error("Exception occurred while getting workmode name list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting workmode name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Add education level in the EducationLevel table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/educationLevel", produces="application/json")
    Response addEducationLevel(@RequestBody String educationLevel){
        Response response=new Response();
        try{
            log.info("The education level is : {}", educationLevel);
            response= adminServices.addEducationLevel(educationLevel);
        }catch(Exception e){
            log.error("Exception occurred while adding educationLevel name ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding educationLevel name "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get education Level list from the educationLevel table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all education levls.")
    @GetMapping(value="/educationLevel/all", produces="application/json")
    ResponseEntity<Response> getEducationLevelList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getEducationLevelList();
        }catch(Exception e){
            log.error("Exception occurred while getting education level list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting education level list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add industry in the Industry table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/industry", produces="application/json")
    Response addIndustry(@RequestBody String industry){
        Response response=new Response();
        try{
            log.info("The industry is : {}", industry);
            response= adminServices.addIndustry(industry);
        }catch(Exception e){
            log.error("Exception occurred while adding industry name ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding industry name "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get industry list from the Industry table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all industry.")
    @GetMapping(value="/industry/all", produces="application/json")
    ResponseEntity<Response> getIndustryList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getIndustryList();
        }catch(Exception e){
            log.error("Exception occurred while getting industry name list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting industry name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add company details in the Company table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/company", produces="application/json")
    Response addCompany(@RequestBody String company){
        Response response=new Response();
        try{
            log.info("The company name is : {}", company);
            response= adminServices.addCompany(company);
        }catch(Exception e){
            log.error("Exception occurred while adding company name ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding company name "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get company list from the Company table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all companies.")
    @GetMapping(value="/company/all", produces="application/json")
    ResponseEntity<Response> getCompanyList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getCompanyList();
        }catch(Exception e){
            log.error("Exception occurred while getting company name list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting company name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job title in the JobTitle table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/title", produces="application/json")
    Response addJobTitle(@RequestBody String jobTitle){
        Response response=new Response();
        try{
            log.info("The company name list is : {}",jobTitle);
            response= adminServices.addJobTitle(jobTitle);
        }catch(Exception e){
            log.error("Exception occurred while adding job title "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job title "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job title list from the JobTitle table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job titles.")
    @GetMapping(value="/title/all", produces="application/json")
    ResponseEntity<Response> getJobTitleList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobTitleList();
        }catch(Exception e){
            log.error("Exception occurred while getting job title list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job title list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }



    @ApiOperation(value = "Add job location in the JobLocation table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/location", produces="application/json")
    Response addJobLocationList(@RequestBody String jobLocation){
        Response response=new Response();
        try{
            log.info("The company name list is : {}",jobLocation);
            response= adminServices.addJobLocation(jobLocation);
        }catch(Exception e){
            log.error("Exception occurred while adding job location ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job location "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job location list from the JobLocation table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job locations.")
    @GetMapping(value="/location/all", produces="application/json")
    ResponseEntity<Response> getJobLocationList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobLocationList();
        }catch(Exception e){
            log.error("Exception occurred while getting job location list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job location list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job in the job table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/job",consumes="application/json", produces="application/json")
    Response addJob(@RequestBody Job job){
        Response response=new Response();
        try{
            log.info("The Job detail is : "+ job.toString());
            response= adminServices.createJob(job);
        }catch(Exception e){
            log.error("Exception occurred while adding job  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  "+e);
        }
        return response;
    }


    @ApiOperation(value = "Update job in the job table", notes = "Returns a response with status code 200 for successful updation in the table.")
    @PostMapping(value="/job/update",consumes="application/json", produces="application/json")
    Response updateJob(@RequestBody Job job){
        Response response=new Response();
        try{
            log.info("The Job ID is : "+ job);
            response= adminServices.updateJob(job);
        }catch(Exception e){
            log.error("Exception occurred while adding job  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  "+e);
        }
        return response;
    }

    @ApiOperation(value = "Add questions for a job posting in the questionnaire table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/questionnaire",consumes="application/json", produces="application/json")
    Response createQuestionnaire(@RequestBody Questionnaire questionnaire){
        Response response=new Response();
        try {
            log.info("The questionnaire received is : " + questionnaire.toString());
            response = adminServices.createQuestionnaire(questionnaire);
        } catch (Exception e) {
            log.error("Exception occurred while adding job  " + e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  " + e);
        }
        return response;
    }


//    @ApiOperation(value = "Fetch job from the job table", notes = "Returns a response with status code 200 for successful fetch from the job table.")
//    @GetMapping(value="/job/{jobId}", produces="application/json")
//    ResponseEntity getJobById(@PathVariable("jobId") String jobId){
//        ResponseEntity responseEntity;
//        Response response=new Response();
//        try{
//            responseEntity = adminServices.getJobById(jobId);
//            log.info("The Job is : "+ response);
//        }catch(Exception e){
//            log.error("Exception occurred while fetching job  "+e);
//            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setStatusMessage("Exception occurred while fetching job"+e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//        return responseEntity;
//    }

    @ApiOperation(value = "Fetch job from the job table", notes = "Returns a response with status code 200 for successful fetch from the job table.")
    @GetMapping(value = "/jobs/", produces = "application/json")
    ResponseEntity<Response> getJob(JobFilter jobFilter) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try {
            List<Job> jobList = customJobRepository.fetchAll(jobFilter);
            if (jobList.size() != 0) {
                log.info("JobFilter is {} :" + jobFilter);
                log.info("The job fetched is {}", jobList);
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("Successfully received the job details");
                response.setData(jobList);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                log.info("No job was found for the corresponding job ID");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setStatusMessage("No job was found for the corresponding job ID");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            log.error("Exception occurred while getting job details ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job details " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}