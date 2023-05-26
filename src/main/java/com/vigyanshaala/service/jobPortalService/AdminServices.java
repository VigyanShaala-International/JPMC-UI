package com.vigyanshaala.service.jobPortalService;
import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

/*Service interface which declares all the functions used by the admin */
public interface AdminServices {
    Response createJob(Job job);
    Response addCompany(String company);
    Response addJobLocation(String jobLocation);
    Response addJobTitle(String jobTitle);
    Response addWorkmode(String workmode);
    Response addIndustry(String industry);
    Response addEducationLevel(String educationLevel);
    ResponseEntity getCompanyList();
    ResponseEntity getJobLocationList();
    ResponseEntity getJobTitleList();
    ResponseEntity getWorkmodeList();
    ResponseEntity getIndustryList();
    ResponseEntity getEducationLevelList();
    Response createQuestionnaire(Questionnaire questionnaire);
}
