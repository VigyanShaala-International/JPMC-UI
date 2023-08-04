package com.vigyanshaala.controller.jobPortalController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The following Admin Controller contains all the get and post calls for the Admin tasks
 * POST : saving the company details, job titles, job locations, job postings
 * GET : get company details,job titles, job locations
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/entitlement")
@Slf4j
public class EntitlementController {

    @Autowired
    UserController userController;

    @GetMapping(value="/getRoles", produces="application/json")
    public String role(@AuthenticationPrincipal OAuth2User principal)
    {
        log.info("principal"+principal);

        String name= principal.getAttribute("name");
        String email= principal.getAttribute("email");

        log.info("Name and email "+name+" "+email);
        String role = userController.getRole(email);
        String msg="";
        if(!role.equalsIgnoreCase("None"))
        msg= " Hi "+name+" , email "+email+" has role "+role;
        else
            msg= " Hi "+name+" , email "+email+" has no role attached. Please contact vigyanshaala team.";
        return msg;
    }
}

