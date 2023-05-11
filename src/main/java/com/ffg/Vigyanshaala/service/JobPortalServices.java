package com.ffg.Vigyanshaala.service;
import com.ffg.Vigyanshaala.entity.JobPosting;
import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface JobPortalServices {
    public Response createJobImpl(JobDetails jobDetails);
    public Response addCompanyNameList(List<String> companyNameList);
    public Response addJobLocationList(List<String> jobLocationList);
    public Response addJobTitleList(List<String> jobTitleList);
    public ResponseEntity getCompanyNameList();
    public ResponseEntity getJobLocationList();
    public ResponseEntity getJobTitleList();
    public ArrayList<JobPosting> deleteExpiredJobs(Date date);
}
