package com.vigyanshaala.controller.jobPortalController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.repository.jobPortalRepository.CustomJobRepositoryImpl;
import com.vigyanshaala.repository.jobPortalRepository.JobFilter;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.AdminServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    UserController userController;
    @Value("${client-id")
    String clientId;

    private String decodeToken(String bearerToken) throws IOException, GeneralSecurityException {
    try {
        String token = bearerToken.substring(7);
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).setAudience(Collections.singletonList(clientId)).build();
        GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        boolean tokenIsValid = (idToken != null) && verifier.verify(idToken);
        if (tokenIsValid) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            return payload.getEmail();
        }
        return null;
    }
    catch(Exception e)
    {
        log.info("Exception "+e+" occurred while decoding the bearer token");
        return null;
    }
    }
    @ApiOperation(value = "Add work mode in the WorkMode table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value = "/workmode", produces = "application/json")
    Response addWorkmode (@RequestHeader("Authorization") String bearerToken,@RequestBody String workmode) {
        Response response = new Response();
        try{
            String email= decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")) {
                    response = adminServices.addWorkmode(workmode);
                }
                else {
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the Vigyanshaala Team");
                }
            } else throw new Exception("bearer token invalid");
        }
        catch(Exception e){
            log.info("Exception "+e.getMessage()+" occurred in addWorkMode controller");
            response.setStatusMessage("Exception "+e.getMessage()+" occurred in addWorkMode controller");
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @ApiOperation(value = "Get workmode list from the Workmode table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all workmodes.")
    @GetMapping(value="/workmode/all", produces="application/json")
    ResponseEntity<Response> getWorkmodeList(@RequestHeader ("Authorization") String bearerToken) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try{
            String email= decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                log.info(role);
                if (role.equalsIgnoreCase("Admin")) {
                    responseEntity = adminServices.getWorkmodeList();
                }
                else {
                log.error("You need admin role to perform this action");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }}
            else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured in get workmode controller : " +e );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
    @ApiOperation(value = "Add education level in the EducationLevel table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/educationLevel", produces="application/json")
    Response addEducationLevel(@RequestHeader("Authorization") String bearerToken,@RequestBody String educationLevel) {
        Response response=new Response();
        try{
        String email=decodeToken(bearerToken);
        if(Objects.nonNull(email)) {
            String role = userController.getRole(email);
            log.info(role);
            if (role.equalsIgnoreCase("Admin")) {
                    response = adminServices.addEducationLevel(educationLevel);
            }
            else {
                log.error("You need admin role to perform this action");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
            }
        } else throw new Exception("bearer token is invalid ");
        }
        catch(Exception e){
            log.error("Exception occurred while adding educationLevel name : ", e.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding educationLevel name " + e);
        }
        return response;
    }


    @ApiOperation(value = "Get education Level list from the educationLevel table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all education levls.")
    @GetMapping(value="/educationLevel/all", produces="application/json")
    ResponseEntity<Response> getEducationLevelList(@RequestHeader("Authorization") String bearerToken) {
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                if (role.equalsIgnoreCase("Admin")) {
                    responseEntity = adminServices.getEducationLevelList();
                }
                else {
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            } else throw new Exception ("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in getEducationLevelList controller : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return  responseEntity;
    }

    @ApiOperation(value = "Add industry in the Industry table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/industry", produces="application/json")
    Response addIndustry(@RequestHeader("Authorization") String bearerToken,@RequestBody String industry) {
        Response response = new Response();
        try {
            String email = decodeToken(bearerToken);
            if (Objects.nonNull(email)) {
                String role = userController.getRole(email);
                log.info(role);
                if (role.equalsIgnoreCase("Admin")) {
                    response = adminServices.addIndustry(industry);
                } else {
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                }
            } else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in addIndustry controller "+e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Get industry list from the Industry table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all industry.")
    @GetMapping(value="/industry/all", produces="application/json")
    ResponseEntity<Response> getIndustryList(@RequestHeader("Authorization") String bearerToken){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                if (role.equalsIgnoreCase("Admin")) {
                    responseEntity = adminServices.getIndustryList();
                }
                else {
                log.error("You need admin role to perform this action");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Bearer token is invalid");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add company details in the Company table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/company", produces="application/json")
    Response addCompany(@RequestHeader("Authorization") String bearerToken,@RequestBody String company){
        Response response=new Response();
        try {
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                log.info(role);
                if (role.equalsIgnoreCase("Admin")) {
                        response = adminServices.addCompany(company);
                } else {
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                    return response;
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Bearer token is invalid");
        }
        return response;
    }

    @ApiOperation(value = "Get company list from the Company table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all companies.")
    @GetMapping(value="/company/all", produces="application/json")
    ResponseEntity<Response> getCompanyList(@RequestHeader("Authorization") String bearerToken){

        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
            String role= userController.getRole(email);
            log.info(role);
            if(role.equalsIgnoreCase("Admin")) {
                responseEntity = adminServices.getCompanyList();
            }
            else{
                log.error("You need admin role to perform this action");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }} else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in getCompanyList controller : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job title in the JobTitle table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/title", produces="application/json")
    Response addJobTitle(@RequestHeader("Authorization") String bearerToken,@RequestBody String jobTitle){

        Response response = new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")) {
                    response = adminServices.addJobTitle(jobTitle);
                }
                else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                    return response;}
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Bearer token is invalid");
        }
        return response;
    }

    @ApiOperation(value = "Get job title list from the JobTitle table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job titles.")
    @GetMapping(value="/title/all", produces="application/json")
    ResponseEntity<Response> getJobTitleList(@RequestHeader("Authorization") String bearerToken){
        ResponseEntity responseEntity;
        Response response = new Response();
        try {
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")){
                    responseEntity = adminServices.getJobTitleList();
                }else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }}
            else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in getJobTitleList controller" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }



    @ApiOperation(value = "Add job location in the JobLocation table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/location", produces="application/json")
    Response addJobLocationList(@RequestHeader("Authorization") String bearerToken,@RequestBody String jobLocation){
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")) {
                    response = adminServices.addJobLocation(jobLocation);
                }else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in addJobLocationList : "+e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Get job location list from the JobLocation table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job locations.")
    @GetMapping(value="/location/all", produces="application/json")
    ResponseEntity<Response> getJobLocationList(@RequestHeader("Authorization") String bearerToken){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
            String role= userController.getRole(email);
            log.info(role);
            if(role.equalsIgnoreCase("Admin")) {
                responseEntity = adminServices.getJobLocationList();
            }else{
                log.error("You need admin role to perform this action");
                response.setStatusCode(HttpStatus.FORBIDDEN.value());
                response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }}else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job location list" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job in the job table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/job",consumes="application/json", produces="application/json")
    Response addJob(@RequestHeader("Authorization") String bearerToken,@RequestBody Job job){
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")) {
                    response = adminServices.createJob(job);
                }
                else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in addJob controller : "+e.getMessage());
        }
        return response;
    }


    @ApiOperation(value = "Update job in the job table", notes = "Returns a response with status code 200 for successful updation in the table.")
    @PostMapping(value="/job/update",consumes="application/json", produces="application/json")
    Response updateJob(@RequestHeader("Authorization") String bearerToken,@RequestBody Job job){
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                String role = userController.getRole(email);
                if(role.equalsIgnoreCase("admin")) {
                    response = adminServices.updateJob(job);
                }
                else {
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");

                }
            }else throw new Exception("bearer token is invalid");
        }
        catch (Exception e){
            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setStatusMessage("Exception occurred in updateJob controller : "+e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "Add questions for a job posting in the questionnaire table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/questionnaire",consumes="application/json", produces="application/json")
    Response createQuestionnaire(@RequestHeader("Authorization") String bearerToken,@RequestBody Questionnaire questionnaire){
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")) {
                    response = adminServices.createQuestionnaire(questionnaire);
                }else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in createQuestionnaire controller : "+e.getMessage());
        }
        return response;
    }



    @ApiOperation(value = "Fetch job from the job table", notes = "Returns a response with status code 200 for successful fetch from the job table.")
    @GetMapping(value = "/jobs/", produces = "application/json")
    ResponseEntity<Response> getJob(@RequestHeader("Authorization") String bearerToken, JobFilter jobFilter) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("Admin")){
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
                }else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            }else throw new Exception("bearer token is invalid");
        }
        catch(Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job details " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}}