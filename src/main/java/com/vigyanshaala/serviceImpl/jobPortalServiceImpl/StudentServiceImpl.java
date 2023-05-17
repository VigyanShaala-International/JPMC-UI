package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.repository.jobPortalRepository.JobRepository;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.StudentServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentServices {

    private final JobRepository jobRepository;
    public StudentServiceImpl(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity getAllJobs()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<Job> jobList=jobRepository.findAll();
            log.info("The job list is {}",jobList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all job locations");
            response.setData(jobList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting job locations ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job locations "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
