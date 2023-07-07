package com.vigyanshaala.service.jobPortalService;
import com.vigyanshaala.entity.jobPortalEntity.Admin;
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
    ResponseEntity getCompanyList();
    ResponseEntity getJobLocationList();
    ResponseEntity getJobTitleList();
    Response createQuestionnaire(Questionnaire questionnaire);
    ResponseEntity verifyAdmin( String email);
    Response addAdmin(Admin admin);
    ResponseEntity getAdminList();
}
