package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.vigyanshaala.entity.jobPortalEntity.*;
import com.vigyanshaala.repository.jobPortalRepository.*;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.AdminServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AdminServiceImpl implements AdminServices {
    private final CompanyNameRepository companyNameRepository;
    private final JobLocationRepository jobLocationRepository;
    private final JobTitleRepository jobTitleRepository;
    private final JobRepository jobRepository;

    private final QuestionnaireRepository questionnaireRepository;

    public AdminServiceImpl(JobRepository jobRepository, CompanyNameRepository companyDetailsRepository, JobLocationRepository jobLocationRepository, JobTitleRepository jobTitleRepository, QuestionnaireRepository questionnaireRepository) {
        this.companyNameRepository = companyDetailsRepository;
        this.jobLocationRepository = jobLocationRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.jobRepository=jobRepository;
        this.questionnaireRepository = questionnaireRepository;
    }


    /**to create a job posting*/
    @Override
    public Response createJob(Job job){

        Response response=new Response();
        String jobID = UUID.randomUUID().toString();
        String questionnaireId = UUID.randomUUID().toString();
        job.setJobId(jobID);
        job.setIsActive("Y");
        job.getQuestionnaire().setQuestionnaireId(questionnaireId);

        List<Job> jobList = jobRepository.findAll();
        log.info("The list is : ");
        for(Job job1:jobList) {
            log.info(job1.getJobId()+" "+job1.getJobDescription());
            if (job1.getCompany().getCompanyName().equals(job.getCompany().getCompanyName()) && job1.getJobLocation().getJobLocation().equals(job.getJobLocation().getJobLocation()) &&  job1.getJobTitle().getJobTitle().equals(job.getJobTitle().getJobTitle())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The job detail already exists in the table");
                return response;
            }
        }

        log.info("The job detail received for adding is {}",job);
        try {
            jobRepository.save(job);
            questionnaireRepository.save(job.getQuestionnaire());
            log.info("Successfully created Job");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully created Job");
        }catch(Exception e)
        {
            log.error("Exception occurred while creating Job "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while creating Job "+e);
        }
        return response;

    }

    /**to get the list of companies to choose from the company table while creating a job posting */
    @Override
    public ResponseEntity getCompanyList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<Company> companyNameList=companyNameRepository.findAll();
            log.info("The company name list is {}",companyNameList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all company names");
            response.setData(companyNameList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting company names ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting company names "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**to get the list of job locations to choose from while creating a job posting*/
    @Override
    public ResponseEntity getJobLocationList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<JobLocation> jobLocationList=jobLocationRepository.findAll();
            log.info("The company name list is {}",jobLocationList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all job locations");
            response.setData(jobLocationList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting job locations ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job locations "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**to get a list of job titles to choose from while creating a job posting*/
    @Override
    public ResponseEntity getJobTitleList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<JobTitle> jobTitleList=jobTitleRepository.findAll();
            log.info("The job title list is {}",jobTitleList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all company names");
            response.setData(jobTitleList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting job title list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job title list "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**to add a company detail from the admin page*/
    @Override
    public Response addCompany(String companyName){
        Response response=new Response();
        String companyId = UUID.randomUUID().toString();
        Company company=new Company(companyId,companyName);

        List<Company> companyList = companyNameRepository.findAll();
        log.info("The list is : ");
        for(Company company1:companyList) {
            log.info(company1.getCompanyId()+" "+company1.getCompanyName());
            if (company1.getCompanyName().equals(companyName)) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The company detail already exists in the table");
                return response;
            }
        }

        try {
            companyNameRepository.save(company);
            log.info("Successfully saved company Detail");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved company detail");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving company detail ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving company detail "+e);
        }
        return response;
    }

    /**to add a job location from the admin page*/
    @Override
    public Response addJobLocation(String jobLocationName){
        Response response=new Response();
        String jobLocationId = UUID.randomUUID().toString();
        JobLocation jobLocation=new JobLocation(jobLocationId,jobLocationName);

        List<JobLocation>jobLocationList= jobLocationRepository.findAll();
        log.info("The list is : "+jobLocationList);

        for(JobLocation jobLocation1:jobLocationList) {
            if (jobLocation1.getJobLocation().equals(jobLocationName)) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The job location already exists in the table");
                return response;
            }
        }

        try {
            jobLocationRepository.save(jobLocation);
            log.info("Successfully saved  job location");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved  job location");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving job location ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job location "+e);
        }
        return response;
    }

    /**to add a job title from the admin page*/
    @Override
    public Response addJobTitle(String jobTitleName){
        Response response=new Response();
        String jobTitleId = UUID.randomUUID().toString();
        JobTitle jobTitle=new JobTitle(jobTitleId,jobTitleName);

        List<JobTitle>jobTitleList= jobTitleRepository.findAll();
        log.info("The list is : {}",jobTitleList);
        for(JobTitle jobTitle1:jobTitleList){
            if(jobTitle1.getJobTitle().equals(jobTitleName))
            {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The jobTitle already exists in the table");
                return response;
            }}

        try {
            jobTitleRepository.save(jobTitle);
            log.info("Successfully saved  job title");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved  job title");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving job title ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job title "+e);
        }
        return response;
    }

    /*to create questionnaire for a job posting*/
    @Override
    public Response createQuestionnaire(Questionnaire questionnaire){

        Response response=new Response();
        String questionnaireId = UUID.randomUUID().toString();
        questionnaire.setQuestionnaireId(questionnaireId);
        log.info("The questionnaire received for adding is {}", questionnaire);
        try {
            questionnaireRepository.save(questionnaire);
            log.info("Successfully created Questionnaire");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully created Questionnaire");
        }catch(Exception e)
        {
            log.error("Exception occurred while creating Job "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while creating Job "+e);
        }
        return response;

    }
}