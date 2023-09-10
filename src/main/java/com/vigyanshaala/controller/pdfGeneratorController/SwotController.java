package com.vigyanshaala.controller.pdfGeneratorController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.vigyanshaala.controller.jobPortalController.UserController;
import com.vigyanshaala.model.pdfGeneratorModel.SwotTemplate;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.SwotTemplateServices;
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
@RequestMapping("/pdf/swot")
@Slf4j
public class SwotController {
    @Autowired
    SwotTemplateServices swotTemplateServices;
    @Autowired
    UserController userController;
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
    Response createSwotTemplate(@RequestHeader("Authorization") String bearerToken,@RequestBody SwotTemplate swotTemplate) {
        Response response = new Response();
        try {
            String email = decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                if(userController.getRole(email).equalsIgnoreCase("student")) {
                    response = swotTemplateServices.saveSwotTemplate(swotTemplate);
                }else{
                    response.setStatusCode(HttpStatus.FORBIDDEN.value());
                    response.setStatusMessage("TESTING : Student role not present, it's forbidden....");
                }
            } else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding swotTemplateData  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get swot template latest version from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getSwotLatestVersion(@RequestHeader("Authorization") String bearerToken,@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)) {
                responseEntity = swotTemplateServices.getSwotLatestVersion(studentEmail);
            } else throw new Exception("bearer token is invalid");
        }
        catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting swot latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get swot template data from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getSwotTemplate(@RequestHeader("Authorization") String bearerToken,@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            String email=decodeToken(bearerToken);
            if(Objects.nonNull(email)){
                responseEntity= swotTemplateServices.getSwotTemplate(studentEmail,version);
            }else throw new Exception("bearer token is invalid");
        }catch(Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting Swot Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
