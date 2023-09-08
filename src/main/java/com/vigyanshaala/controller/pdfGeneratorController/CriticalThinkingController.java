package com.vigyanshaala.controller.pdfGeneratorController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
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
import java.util.Collections;
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


    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createCriticalThinkingTemplate(@RequestHeader("Authorization") String bearerToken,@RequestBody CriticalThinking criticalThinking) {
        Response response = new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
        try {
            log.info("The critical thinking template  is : {}" , criticalThinking.toString());
            response = criticalThinkingServices.saveCriticalThinkingTemplate(criticalThinking);
        } catch (Exception e) {
            log.error("Exception occurred while adding criticalThinking template data  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding critical thinking template  " + e);
        }
        }
        else{
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setStatusMessage("Exception occurred while adding critical thinking template  " );
            }}catch (Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding critical thinking template  " + e);
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

                try {
                    responseEntity = criticalThinkingServices.getCriticalThinkingLatestVersion(studentEmail);
                } catch (Exception e) {
                    log.error("Exception occurred while getting ct latest version " + e);
                    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    response.setStatusMessage("Exception occured while getting CT latest version" + e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            } else {
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setStatusMessage("Exception occured while getting CT latest version" );
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }catch(Exception e){
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
        try{
            responseEntity= criticalThinkingServices.getCriticalThinkingTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting CT Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }}else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT Template data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }}catch (Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
