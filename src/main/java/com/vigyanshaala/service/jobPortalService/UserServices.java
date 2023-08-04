package com.vigyanshaala.service.jobPortalService;
import org.springframework.http.ResponseEntity;


public interface UserServices {
    ResponseEntity getRole( String email);

}
