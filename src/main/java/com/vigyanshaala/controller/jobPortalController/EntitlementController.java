package com.vigyanshaala.controller.jobPortalController;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.vigyanshaala.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The following Entitlement Controller contains all the get call for the entitlement
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/entitlement")
@Slf4j
public class EntitlementController {
    @Autowired
    UserController userController;
    @Value("${client-id}")
    private String clientId;


    @GetMapping(value="/getRoles", produces="application/json")
    public ResponseEntity<Response> role(@RequestHeader("Authorization") String bearerToken) throws IOException {
        log.info("client id "+clientId);
        log.info("bearer token"+bearerToken);
        Response response=new Response();
        String name="";
        String email="";
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
                email=payload.getEmail();
                name=(String) payload.get("name");
                log.info("Name and email " + name + " " + email);
                String role = userController.getRole(email);
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("Successfully got the role for email "+email);
                Map<String,String>entitlementMap=new HashMap<>();
                entitlementMap.put("name",name);
                entitlementMap.put("email",email);
                entitlementMap.put("role",role);
                response.setData(entitlementMap);

            } else {
                log.info("Invalid ID token.");
                throw new Exception("Invalid ID token");
            }
        }catch(Exception e)
        {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception "+e.getMessage()+" occured while getting entitlement for email "+email );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }}

