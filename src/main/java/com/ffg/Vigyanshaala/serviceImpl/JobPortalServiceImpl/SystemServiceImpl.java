package com.ffg.Vigyanshaala.serviceImpl.JobPortalServiceImpl;

import com.ffg.Vigyanshaala.repository.JobPortalRepository.ExpiredJobsRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.SystemServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
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
            System.out.println("Successfully soft deleted expired jobs");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully flagged expired jobs");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while soft deleting titles "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while soft deleting titles "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
