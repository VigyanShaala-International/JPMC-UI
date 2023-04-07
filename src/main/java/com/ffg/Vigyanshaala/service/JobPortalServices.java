package com.ffg.Vigyanshaala.service;
import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.response.Response;

public interface JobPortalServices {
    public Response createJobImpl(JobDetails jobDetails);
}
