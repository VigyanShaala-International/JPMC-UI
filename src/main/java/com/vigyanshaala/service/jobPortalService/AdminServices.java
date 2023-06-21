package com.vigyanshaala.service.jobPortalService;
import com.vigyanshaala.entity.jobPortalEntity.*;

import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

/*Service interface which declares all the functions used by the admin */
public interface AdminServices {
    Response createJob(Job job);
    Response addCompany(Company company);
    Response addJobLocation(JobLocation jobLocation);
    Response addJobTitle(JobTitle jobTitle);
    Response addWorkmode(WorkMode workmode);
    Response addIndustry(Industry industry);
    Response addEducationLevel(EducationLevel educationLevel);
    ResponseEntity getCompanyList();
    ResponseEntity getJobLocationList();
    ResponseEntity getJobTitleList();
    ResponseEntity getWorkmodeList();
    ResponseEntity getIndustryList();
    ResponseEntity getEducationLevelList();
    Response createQuestionnaire(Questionnaire questionnaire);
    Response updateJob(Job job);
    //ResponseEntity getJobById(String jobId);
}
