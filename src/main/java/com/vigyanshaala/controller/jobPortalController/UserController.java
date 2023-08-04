package com.vigyanshaala.controller.jobPortalController;

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
    @GetMapping(value = "/{encryptedEmail}", produces = "application/json")
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
    Response addUserRole( UserRole userRole) {
        Response response=new Response();

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

    }
}
