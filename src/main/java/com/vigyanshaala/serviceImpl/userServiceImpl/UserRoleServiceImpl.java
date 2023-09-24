package com.vigyanshaala.serviceImpl.userServiceImpl;

import com.vigyanshaala.repository.jobPortalRepository.JobRepository;
import com.vigyanshaala.repository.jobPortalRepository.UserRoleRepository;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vigyanshaala.entity.user.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserServices {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;
    }

    public ResponseEntity getRole(String email)
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            String studentName = String.valueOf(userRoleRepository.findById(email));

            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received");
            response.setData(studentName);

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
