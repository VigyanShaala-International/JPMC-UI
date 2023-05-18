package com.ffg.Vigyanshaala.service.JobPortalService;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.*;

import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

/*Service interface which declares all the functions used by the admin */
public interface AdminServices {
    public Response createJob(Job job);
    public Response addCompany(Company company);
    public Response addJobLocation(JobLocation jobLocation);
    public Response addJobTitle(JobTitle jobTitle);
    public ResponseEntity getCompanyList();
    public ResponseEntity getJobLocationList();
    public ResponseEntity getJobTitleList();
    public Response createQuestionnaire(Questionnaire questionnaire);
}
