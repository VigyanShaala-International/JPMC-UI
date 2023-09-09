package com.vigyanshaala.controller.jobPortalController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.StudentServices;
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
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/job/student")
public class StudentController {
    @Autowired
    StudentServices studentServices;

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
        catch(Exception e) {
            log.info("Exception "+e+" occurred while decoding the bearer token");
            return null;
        }
    }
    @ApiOperation(value = "Get all jobs from the job table without a filter", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all jobs.")
    @GetMapping(value = "/job/all", produces = "application/json")
    ResponseEntity<Response> getAllJobs(@RequestHeader("Authorization") String bearerToken) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                String role= userController.getRole(email);
                log.info(role);
                if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("student")) {
                    responseEntity = studentServices.getAllJobs();
                }else{
                    log.error("You need admin or student role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Student/Admin role is missing, please contact the vigyanshaala team");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            }
            else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred in getAllJobs controller : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
        return responseEntity;
    }}
