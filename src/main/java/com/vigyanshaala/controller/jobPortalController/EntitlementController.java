package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.response.Response;
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

import java.util.HashMap;
import java.util.Map;

/**
 * The following Entitlement Controller contains all the get call for the entitlement
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/entitlement")
@Slf4j
public class EntitlementController {
    @Autowired
    UserController userController;

    @GetMapping(value="/getRoles", produces="application/json")
    public ResponseEntity<Response> role(@AuthenticationPrincipal OAuth2User principal)
    {
        Response response=new Response();
        try {
            log.info("principal" + principal);
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            log.info("Name and email " + name + " " + email);
            String role = userController.getRole(email);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully got the role for email "+email);
            Map<String,String>entitlementMap=new HashMap<>();
            entitlementMap.put("name",name);
            entitlementMap.put("email",email);
            entitlementMap.put("role",role);
            response.setData(entitlementMap);
        }catch(Exception e)
        {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception "+e.getMessage()+" occured while getting entitlement for email "+principal.getAttribute("email") );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

