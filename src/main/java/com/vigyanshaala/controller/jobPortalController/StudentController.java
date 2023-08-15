package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.StudentServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/job/student")
public class StudentController {
    @Autowired
    StudentServices studentServices;

    @Autowired
    UserController userController;

    String getRole(@AuthenticationPrincipal OAuth2User principal)
    {
        log.info("principal"+principal);
        String name= principal.getAttribute("name");
        String email= principal.getAttribute("email");
        log.info("Name and email "+name+" "+email);
        String role = userController.getRole(email);
        return role;
    }
    @ApiOperation(value = "Get all jobs from the job table without a filter", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all jobs.")
    @GetMapping(value = "/job/all", produces = "application/json")
    ResponseEntity<Response> getAllJobs(@AuthenticationPrincipal OAuth2User principal) {
        String role=getRole(principal);
        log.info(role);
        ResponseEntity responseEntity;
        Response response = new Response();
        if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("student")) {
            try {
                responseEntity = studentServices.getAllJobs();
            } catch (Exception e) {
                log.error("Exception occurred while getting all the jobs ", e);
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setStatusMessage("Exception occurred while getting all the jobs" + e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            return responseEntity;
        }else{
            log.error("You need admin or student role to perform this action");
            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setStatusMessage("Student/Admin role is missing, please contact the vigyanshaala team");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }
}
