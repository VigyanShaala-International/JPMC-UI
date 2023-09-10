package com.vigyanshaala.controller.jobPortalController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
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
import java.util.Arrays;
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

    public String getRole(@PathVariable("encryptedEmail") String email) {
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
            String token=bearerToken.substring(7);
            log.info("token"+token);
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory )
                    .setAudience(Arrays.asList(clientId))
                    .setIssuer("https://accounts.google.com")
                    .build();

            GoogleIdToken idToken = verifier.verify(token);
            log.info("ID token:"+idToken);
            if (idToken != null) {
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
                    response = userServices.addUserRole(userRole);
                }else{
                    log.error("You need admin role to perform this action");
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("Admin role is missing, please contact the vigyanshaala team");
                }
            }
            else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding user"+e);
        }
        return response;
}}
