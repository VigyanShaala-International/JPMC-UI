package com.ffg.Vigyanshaala.serviceImpl;

import com.ffg.Vigyanshaala.entity.CompanyName;
import com.ffg.Vigyanshaala.entity.JobLocation;
import com.ffg.Vigyanshaala.entity.JobTitle;
import com.ffg.Vigyanshaala.model.JobDetails;
import com.ffg.Vigyanshaala.repository.CompanyNameRepository;
import com.ffg.Vigyanshaala.repository.JobLocationRepository;
import com.ffg.Vigyanshaala.repository.JobTitleRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.JobPortalServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPortalServiceImpl implements JobPortalServices {
 private final CompanyNameRepository companyNameRepository;
 private final JobLocationRepository jobLocationRepository;
 private final JobTitleRepository jobTitleRepository;

    public JobPortalServiceImpl(CompanyNameRepository companyDetailsRepository, JobLocationRepository jobLocationRepository, JobTitleRepository jobTitleRepository) {
        this.companyNameRepository = companyDetailsRepository;
        this.jobLocationRepository = jobLocationRepository;
        this.jobTitleRepository = jobTitleRepository;
    }

    @Override
    public Response createJobImpl(JobDetails jobDetails){

        Response response=new Response();
        System.out.println("The job detail received for adding is "+jobDetails);
        try {
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

    @Override
    public ResponseEntity getCompanyNameList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<CompanyName> companyNameList=companyNameRepository.findAll();
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

    @Override
    public Response addCompanyNameList(List<String> companyNameList){
        Response response=new Response();
        List<CompanyName> companyNameObjectList=new ArrayList<>();
        companyNameList.forEach((cn)-> {
                    CompanyName companyName = new CompanyName();
                    companyName.setCompany_name(cn);
                    companyNameObjectList.add(companyName);
                });

        System.out.println("The company name List is  "+companyNameObjectList);
        try {
            companyNameRepository.saveAll(companyNameObjectList);
            System.out.println("Successfully saved all company names");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved all company names");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving company names "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving company names "+e);
        }
        return response;
    }

    @Override
    public Response addJobLocationList(List<String> jobLocationList){
        Response response=new Response();
        List<JobLocation> jobLocationObjectList=new ArrayList<>();
        jobLocationList.forEach((jl)-> {
            JobLocation jobLocation = new JobLocation();
            jobLocation.setJob_location(jl);
            jobLocationObjectList.add(jobLocation);
        });

        System.out.println("The job location List is  "+jobLocationObjectList);
        try {
            jobLocationRepository.saveAll(jobLocationObjectList);
            System.out.println("Successfully saved all job locations");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved all job locations");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving job locations "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job locations "+e);
        }
        return response;
    }

    @Override
    public Response addJobTitleList(List<String> jobTitleList){
        Response response=new Response();
        List<JobTitle> jobTitleObjectList=new ArrayList<>();
        jobTitleList.forEach((jt)-> {
            JobTitle jobTitle = new JobTitle();
            jobTitle.setJob_title(jt);
            jobTitleObjectList.add(jobTitle);
        });

        System.out.println("The job title List is  "+jobTitleObjectList);
        try {
            jobTitleRepository.saveAll(jobTitleObjectList);
            System.out.println("Successfully saved all job titles");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved all job titles");
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving job titles "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job titles "+e);
        }
        return response;
    }
}
