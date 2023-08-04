package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return (String) response.getData();

    }
}
