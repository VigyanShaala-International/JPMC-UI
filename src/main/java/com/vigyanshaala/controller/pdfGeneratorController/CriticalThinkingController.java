package com.vigyanshaala.controller.pdfGeneratorController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.vigyanshaala.model.pdfGeneratorModel.CriticalThinking;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.CriticalThinkingServices;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/pdf/ct")
@Slf4j
public class CriticalThinkingController {
    @Autowired
    CriticalThinkingServices criticalThinkingServices;

    @Value("${client-id")
    String clientId;

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
        catch(Exception e) {
            log.info("Exception "+e+" occurred while decoding the bearer token");
            return null;
        }
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createCriticalThinkingTemplate(@RequestHeader("Authorization") String bearerToken,@RequestBody CriticalThinking criticalThinking) {
        Response response = new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                response = criticalThinkingServices.saveCriticalThinkingTemplate(criticalThinking);
            }
            else throw new Exception("bearer token is invalid");
        }
        catch (Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding critical thinking template  " + e.getMessage());
        }
        return response;
    }
    @ApiOperation(value = "Get swot template latest version from the critical thinking template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getCTLatestVersion(@RequestHeader("Authorization") String bearerToken,@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            String email = decodeToken(bearerToken);
            if (Objects.nonNull(email)) {
                responseEntity = criticalThinkingServices.getCriticalThinkingLatestVersion(studentEmail);
            } else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT latest version" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get swot template data from the critical thinking template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getCriticalThinkingTemplate(@RequestHeader("Authorization")String bearerToken,@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                responseEntity= criticalThinkingServices.getCriticalThinkingTemplate(studentEmail,version);
            }else throw new Exception("bearer token is invlid");
        }
        catch (Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
