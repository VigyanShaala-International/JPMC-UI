package com.ffg.Vigyanshaala.serviceImpl.JobPortalServiceImpl;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Company;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobLocation;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobTitle;
import com.ffg.Vigyanshaala.model.JobPortal.JobDetails;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.CompanyNameRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobLocationRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobTitleRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalService.AdminServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServices {
 private final CompanyNameRepository companyNameRepository;
 private final JobLocationRepository jobLocationRepository;
 private final JobTitleRepository jobTitleRepository;
 private final JobRepository jobRepository;

    public AdminServiceImpl(JobRepository jobRepository,CompanyNameRepository companyDetailsRepository, JobLocationRepository jobLocationRepository, JobTitleRepository jobTitleRepository) {
        this.companyNameRepository = companyDetailsRepository;
        this.jobLocationRepository = jobLocationRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.jobRepository=jobRepository;
    }


    /*to create a job posting*/
    @Override
    public Response createJob(Job job){

        Response response=new Response();
        System.out.println("The job detail received for adding is "+job);
        try {
            jobRepository.save(job);
            System.out.println("Successfully created Job");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully created Job");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while creating Job "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while creating Job "+e);
        }
        return response;

    }

    /*to get the list of companies to choose from the company table while creating a job posting */
    @Override
    public ResponseEntity getCompanyList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<Company> companyNameList=companyNameRepository.findAll();
            System.out.println("The company name list is "+companyNameList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all company names");
            response.setData(companyNameList);

        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting company names "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting company names "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /*to get the list of job locations to choose from while creating a job posting*/
    @Override
    public ResponseEntity getJobLocationList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<JobLocation> jobLocationList=jobLocationRepository.findAll();
            System.out.println("The company name list is "+jobLocationList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all job locations");
            response.setData(jobLocationList);

        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting job locations "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job locations "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /*to get a list of job titles to choose from while creating a job posting*/
    @Override
    public ResponseEntity getJobTitleList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<JobTitle> jobTitleList=jobTitleRepository.findAll();
            System.out.println("The job title list is "+jobTitleList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all company names");
            response.setData(jobTitleList);

        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting job title list "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job title list "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /*to add a company detail from the admin page*/
    @Override
    public Response addCompany(Company company){
        Response response=new Response();
        List<Company> companyList = companyNameRepository.findAll();
        System.out.println("The list is : ");
        for(Company company1:companyList) {
            System.out.println(company1.getCompanyId()+" "+company1.getCompanyName());
            if (company1.getCompanyName().equals(company.getCompanyName())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The company detail already exists in the table");
                return response;
            }
        }

        try {
            companyNameRepository.save(company);
            System.out.println("Successfully saved company Detail");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved company detail");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving company detail "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving company detail "+e);
        }
        return response;
    }

    /*to add a job location from the admin page*/
    @Override
    public Response addJobLocation(JobLocation jobLocation){
        Response response=new Response();
        List<JobLocation>jobLocationList= jobLocationRepository.findAll();
        System.out.println("The list is : "+jobLocationList);

        for(JobLocation jobLocation1:jobLocationList) {
            if (jobLocation1.getJobLocation().equals(jobLocation.getJobLocation())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The job location already exists in the table");
                return response;
            }
        }

        try {
            jobLocationRepository.save(jobLocation);
            System.out.println("Successfully saved  job location");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved  job location");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving job location "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job location "+e);
        }
        return response;
    }

    /*to add a job title from the admin page*/
    @Override
    public Response addJobTitle(JobTitle jobTitle){
        Response response=new Response();

        List<JobTitle>jobTitleList= jobTitleRepository.findAll();
        System.out.println("The list is : "+jobTitleList);
        for(JobTitle jobTitle1:jobTitleList){
        if(jobTitle1.getJobTitle().equals(jobTitle.getJobTitle()))
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("The jobTitle already exists in the table");
            return response;
        }}

        try {
            jobTitleRepository.save(jobTitle);
            System.out.println("Successfully saved  job title");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved  job title");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving job title "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job title "+e);
        }
        return response;
    }
}
