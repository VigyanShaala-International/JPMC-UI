package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.vigyanshaala.repository.jobPortalRepository.ExpiredJobsRepository;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.SystemServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SystemServiceImpl implements SystemServices {

    private final ExpiredJobsRepository expiredJobsRepository;

    public SystemServiceImpl(ExpiredJobsRepository expiredJobsRepository) {
        this.expiredJobsRepository = expiredJobsRepository;
    }

    @Override
    public ResponseEntity deleteExpiredJobs(String date){
//        ArrayList<Job> results = null;
        Response response = new Response();
        try {
              expiredJobsRepository.softdeleteJobs(date);
//            System.out.println(results);
            log.info("Successfully soft deleted expired jobs");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully flagged expired jobs");
        }catch(Exception e)
        {
            log.error("Exception occurred while soft deleting titles ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while soft deleting titles "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
