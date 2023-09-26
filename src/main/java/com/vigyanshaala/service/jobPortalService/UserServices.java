package com.vigyanshaala.service.jobPortalService;
import com.vigyanshaala.entity.user.UserRole;
import com.vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;


public interface UserServices {
    ResponseEntity getRole( String email);
    Response addUserRole(UserRole userRole);

}
