package com.ffg.Vigyanshaala.serviceImpl.JobPortalServiceImpl;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobLocation;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.CompanyNameRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobLocationRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobTitleRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.StudentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
            System.out.println("The job list is "+jobList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all job locations");
            response.setData(jobList);

        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting job locations "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job locations "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
