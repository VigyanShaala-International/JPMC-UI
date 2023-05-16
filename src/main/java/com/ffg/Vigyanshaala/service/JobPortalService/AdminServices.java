package com.ffg.Vigyanshaala.service.JobPortalService;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.Company;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobLocation;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobTitle;
import com.ffg.Vigyanshaala.model.JobPortal.JobDetails;
import com.ffg.Vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

/*Service interface which declares all the functions used by the admin */
public interface AdminServices {
    public Response createJob(Job job);
    public Response addCompany(String company);
    public Response addJobLocation(String jobLocation);
    public Response addJobTitle(String jobTitle);
    public ResponseEntity getCompanyList();
    public ResponseEntity getJobLocationList();
    public ResponseEntity getJobTitleList();
}
