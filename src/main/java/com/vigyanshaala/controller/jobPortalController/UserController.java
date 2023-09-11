package com.vigyanshaala.controller.jobPortalController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.vigyanshaala.controller.EntitlementController;
import com.vigyanshaala.entity.user.UserRole;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    EntitlementController entitlementController;
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


    @PostMapping(value="/userRole", produces="application/json")
    Response addUserRole( @RequestHeader("Authorization") String bearerToken, UserRole userRole) {
        Response response=new Response();
        try{
            GoogleIdToken idToken=entitlementController.decodeToken(bearerToken);
            if(Objects.nonNull(idToken)){
                String email=idToken.getPayload().getEmail();
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
