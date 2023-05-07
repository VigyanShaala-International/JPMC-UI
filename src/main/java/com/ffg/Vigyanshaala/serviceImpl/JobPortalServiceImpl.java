package com.ffg.Vigyanshaala.serviceImpl;

import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.repository.JobRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JobPortalServiceImpl implements JobPortalServices {

    @Autowired
    private JobRepository jobRepository;
    @Override
    public Response createJobImpl(JobDetails jobDetails){

        Response response=new Response();
        System.out.println("The job detail received for adding is "+jobDetails);
        try {
            System.out.println("Successfully created Job");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully created Job");
            jobRepository.save(jobDetails);
            response.setStatusMessage("Successfully added job to db");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while creating Job "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while creating Job "+e);
        }
        return response;

    }
}
