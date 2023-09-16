package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.vigyanshaala.entity.user.UserRole;
import com.vigyanshaala.repository.jobPortalRepository.UserRoleRepository;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserServices {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;
    }

    public Response addUserRole(UserRole userRole)
    {
        log.info("inside add user role");
        Response response=new Response();

        List<UserRole>userRoleList= userRoleRepository.findAll();
        log.info("The list is : {}",userRoleList);
        for(UserRole userRole1:userRoleList){
            if(userRole1.getEmailId().equals(userRole.getEmailId()) && userRole1.getRole().equalsIgnoreCase(userRole.getRole()))
            {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The user already exists in the table with the following role "+userRole1.getRole());
                return response;
            }}

        try {
            userRoleRepository.save(userRole);
            log.info("Successfully saved user");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved user");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving user ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving user "+e);
        }
        return response;
    }

    @Cacheable(value="role-cache")
    public ResponseEntity getRole(String email)
    {
        log.info("inside get Role method");
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            UserRole userRole = userRoleRepository.findByEmail(email);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received userRole");
            response.setData(userRole);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting student name ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting student name "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
