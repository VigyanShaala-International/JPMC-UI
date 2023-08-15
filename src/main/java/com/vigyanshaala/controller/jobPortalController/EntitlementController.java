package com.vigyanshaala.controller.jobPortalController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

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

    @Value("${adminLandingPage}")
    String adminLandingUrl;
    @Value("${studentLandingPage}")
    String studentLandingUrl;
    @Value("${errorLandingPage}")
    String errorLandingUrl;



    @GetMapping(value="/getRoles", produces="application/json")
    public RedirectView role(@AuthenticationPrincipal OAuth2User principal)
    {
        log.info("principal"+principal);
        String name= principal.getAttribute("name");
        String email= principal.getAttribute("email");
        log.info("Name and email "+name+" "+email);
        String role = userController.getRole(email);
        RedirectView redirectView = new RedirectView();
        if(role.equalsIgnoreCase("ADMIN")) {
            redirectView.setUrl(adminLandingUrl);
        }
        else if(role.equalsIgnoreCase("STUDENT")){
            redirectView.setUrl(studentLandingUrl);
        }
        else
            redirectView.setUrl(errorLandingUrl);


        Map<String, String>mp=new HashMap<>();
        mp.put("name",name);
        mp.put("email",email);
        redirectView.setAttributesMap(mp);
        log.info("redirectView"+redirectView);
        log.info("redirectViewMap"+redirectView.getAttributesMap());
        return redirectView;
    }
}

