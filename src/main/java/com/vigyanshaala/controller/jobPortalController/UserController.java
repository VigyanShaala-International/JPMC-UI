package com.vigyanshaala.controller.jobPortalController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.vigyanshaala.entity.user.UserRole;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;
    @Value("${client-id")
    String clientId;

    String getRole(@PathVariable("encryptedEmail") String email) {
        log.info(email);
        ResponseEntity responseEntity;
        Response response = new Response();
        responseEntity = userServices.getRole(email);
        log.info("responseEntity" + responseEntity);
        response = (Response) responseEntity.getBody();
        log.info("response" + response.getData());
        if(Objects.nonNull(response.getData())) {
            UserRole userRole = (UserRole) response.getData();
            return userRole.getRole();
        }
        return "None";
    }
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

    @PostMapping(value="/userRole", produces="application/json")
    Response addUserRole( @RequestHeader("Authorization") String bearerToken, UserRole userRole) {

        Response response=new Response();
        try{

        String email=decodeToken(bearerToken);
        if(Objects.nonNull(email)){
        String role=getRole(email);
        if(role.equalsIgnoreCase("Admin"))
        {
        log.info("inside adduser role controlleer");
        log.info("userRole"+userRole);
        try{
            response = userServices.addUserRole(userRole);
        }catch(Exception e){
            log.error("Exception occurred while adding user ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding user"+e);
            return response;
        }
        return response;

    }else{
            log.error("You need admin role to perform this action");
            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
            return response;
        }
    }else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding user");
            return response;
        }}catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding user"+e);
            return response;
        }
}}
