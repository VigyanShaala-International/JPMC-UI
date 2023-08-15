package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.entity.user.UserRole;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

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

    @PostMapping(value="/userRole", produces="application/json")
    Response addUserRole( @AuthenticationPrincipal OAuth2User principal, UserRole userRole) {
        Response response=new Response();
        log.info("principal"+principal);
        String name= principal.getAttribute("name");
        String email= principal.getAttribute("email");
        log.info("Name and email "+name+" "+email);
        String role=getRole(name);
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
    }
}
