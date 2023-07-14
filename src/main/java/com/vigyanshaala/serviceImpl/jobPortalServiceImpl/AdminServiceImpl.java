package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.vigyanshaala.entity.jobPortalEntity.*;
import com.vigyanshaala.repository.jobPortalRepository.*;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.AdminServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AdminServiceImpl implements AdminServices {
    private final CompanyNameRepository companyNameRepository;
    private final JobLocationRepository jobLocationRepository;
    private final JobTitleRepository jobTitleRepository;
    private final JobRepository jobRepository;
    private final WorkModeRepository workModeRepository;
    private final IndustryRepository industryRepository;
    private final EducationLevelRepository educationLevelRepository;

    private final QuestionnaireRepository questionnaireRepository;

//    @PersistenceContext
//    private EntityManager em;

    public AdminServiceImpl(WorkModeRepository workModeRepository, IndustryRepository industryRepository, EducationLevelRepository educationLevelRepository, JobRepository jobRepository, CompanyNameRepository companyDetailsRepository, JobLocationRepository jobLocationRepository, JobTitleRepository jobTitleRepository, QuestionnaireRepository questionnaireRepository) {
        this.companyNameRepository = companyDetailsRepository;
        this.jobLocationRepository = jobLocationRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.jobRepository = jobRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.workModeRepository = workModeRepository;
        this.industryRepository = industryRepository;
        this.educationLevelRepository = educationLevelRepository;
    }


    /**to create a job posting*/
    @Override
    public Response createJob(Job job){

        //Response response=new Response();
        String jobID = UUID.randomUUID().toString();
        String questionnaireId = UUID.randomUUID().toString();
        job.setJobId(jobID);
        job.setIsActive("Y");
        job.getQuestionnaire().setQuestionnaireId(questionnaireId);
        job.setPostingDate(LocalDate.now());
        job.setExpiryDate(LocalDate.now( ).plusMonths( 2 ));

        return saveJob(job, "CREATE");
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

    /**to get a list of work mode to choose from while creating a job posting*/
    @Override
    public ResponseEntity getWorkmodeList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<WorkMode> workModeList=workModeRepository.findAll();
            log.info("The work mode list is {}",workModeList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all work modes names");
            response.setData(workModeList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting work mode list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting work mode list "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**to get a list of industry to choose from while creating a job posting*/
    @Override
    public ResponseEntity getIndustryList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<Industry> industryList=industryRepository.findAll();
            log.info("The industry list is {}",industryList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all industry names");
            response.setData(industryList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting industry list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting industry list "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**to get a list of educationLevel to choose from while creating a job posting*/
    @Override
    public ResponseEntity getEducationLevelList()
    {
        ResponseEntity responseEntity;
        Response response=new Response();
        try {
            List<EducationLevel> educationLevelList=educationLevelRepository.findAll();
            log.info("The educationLevel list is {}",educationLevelList);
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully received all education levels");
            response.setData(educationLevelList);

        }catch(Exception e)
        {
            log.error("Exception occurred while getting education level list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting education level list "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    /**to add a company detail from the admin page*/
    @Override
    public Response addCompany(Company company){
        Response response=new Response();
        String companyId = UUID.randomUUID().toString();
        company.setCompanyId(companyId);

        List<Company> companyList = companyNameRepository.findAll();
        log.info("The list is : ");
        for(Company company1:companyList) {
            log.info(company1.getCompanyId()+" "+company1.getCompanyName());
            if (company1.getCompanyName().equals(company.getCompanyName())) {
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

    /**to add a education level from the admin page*/
    @Override
    public Response addEducationLevel(EducationLevel educationLevel){
        Response response=new Response();
        String educationLevelId = UUID.randomUUID().toString();
        educationLevel.setEducationLevelId(educationLevelId);

        List<EducationLevel>educationLevelList= educationLevelRepository.findAll();
        log.info("The list is : "+educationLevelList);

        for(EducationLevel educationLevel1:educationLevelList) {
            if (educationLevel1.getEducationLevel().equals(educationLevel.getEducationLevel())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The education level already exists in the table");
                return response;
            }
        }

        try {
            educationLevelRepository.save(educationLevel);
            log.info("Successfully saved education level");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved education level");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving education level ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving education level "+e);
        }
        return response;
    }

    /**to add a industry from the admin page*/
    @Override
    public Response addIndustry(Industry industry){
        Response response=new Response();
        String industryId = UUID.randomUUID().toString();
        industry.setIndustryId(industryId);
        List<Industry>industryList= industryRepository.findAll();
        log.info("The list is : "+industryList);

        for(Industry industry1:industryList) {
            if (industry1.getIndustry().equals(industry.getIndustry())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The industry already exists in the table");
                return response;
            }
        }

        try {
            industryRepository.save(industry);
            log.info("Successfully saved industry");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved industry");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving industry ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving industry "+e);
        }
        return response;
    }
    /**to add a workmode from the admin page*/
    @Override
    public Response addWorkmode(WorkMode workmode){
        Response response=new Response();
        String workmodeId = UUID.randomUUID().toString();
        workmode.setWorkModeId(workmodeId);

        List<WorkMode>workModeList= workModeRepository.findAll();
        log.info("The list is : "+workModeList);

        for(WorkMode workMode1:workModeList) {
            if (workMode1.getWorkMode().equals(workmode.getWorkMode())) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("The workmode already exists in the table");
                return response;
            }
        }

        try {
            workModeRepository.save(workmode);
            log.info("Successfully saved workmode");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully saved workmode");
        }catch(Exception e)
        {
            log.error("Exception occurred while saving workmode ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving workmode "+e);
        }
        return response;
    }
    /**to add a job location from the admin page*/
    @Override
    public Response addJobLocation(JobLocation jobLocation){
        Response response=new Response();
        String jobLocationId = UUID.randomUUID().toString();
        jobLocation.setJobLocationId(jobLocationId);

        List<JobLocation>jobLocationList= jobLocationRepository.findAll();
        log.info("The list is : "+jobLocationList);

        for(JobLocation jobLocation1:jobLocationList) {
            if (jobLocation1.getJobLocation().equals(jobLocation.getJobLocation())) {
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
    public Response addJobTitle(JobTitle jobTitle){
        Response response=new Response();
        String jobTitleId = UUID.randomUUID().toString();
        jobTitle.setJobTitleId(jobTitleId);

        List<JobTitle>jobTitleList= jobTitleRepository.findAll();
        log.info("The list is : {}",jobTitleList);
        for(JobTitle jobTitle1:jobTitleList){
            if(jobTitle1.getJobTitle().equals(jobTitle.getJobTitle()))
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

    /* To save a job posting only if it is not a duplicate */
    private Response saveJob(Job job, String operation) {
        Response response=new Response();
        //Finding if a job posting already exists
        Job duplicateJob = jobRepository.findDuplicateJob(job.getCompany().getCompanyName(), job.getJobLocation().getJobLocation(), job.getJobTitle().getJobTitle(), job.getJobDescription());
        log.info("The job which already exists in the table is {}", duplicateJob);
        if (duplicateJob != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("The job detail already exists in the table");
            response.setData(duplicateJob);
            return response;
        }

        log.info("The job detail received for adding is {}",job);
        try {
            jobRepository.save(job);
            questionnaireRepository.save(job.getQuestionnaire());
            if (operation == "CREATE") {
                log.info("Successfully created Job");
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("Successfully created Job");
            } else if (operation == "UPDATE") {
                log.info("Successfully updated Job");
                response.setStatusCode(HttpStatus.OK.value());
                response.setStatusMessage("Successfully updated Job");
            }
        } catch (Exception e) {
            log.error("Exception occurred while creating Job " + e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while creating Job " + e);
        }

        return response;

    }

    /*to update a job posting*/
    @Override
    public Response updateJob(Job job){

        Response response=new Response();
        Job job1 = jobRepository.findByJobId(job.getJobId());
        String questionnaireId = job1.getQuestionnaire().getQuestionnaireId();
        log.info("The job before updating is {}", job1);

        job.getQuestionnaire().setQuestionnaireId(questionnaireId);

        job1.setQuestionnaire(job.getQuestionnaire());
        job1.setJobDescription(job.getJobDescription());
        job1.setJobLocation(job.getJobLocation());
        job1.setJobTitle(job.getJobTitle());
        job1.setExpiryDate(job.getExpiryDate());
        job1.setJobId(job.getJobId());
        log.info("The job after updating is {}", job1);
        return saveJob(job1, "UPDATE");
    }

//    @Override
//    public ResponseEntity getJobById(String jobId){
//
//        ResponseEntity responseEntity;
//        Response response=new Response();
//        try {
//            Job job1 = jobRepository.findByJobId(jobId);
//            if( job1!= null) {
//                log.info("The job fetched is {}", job1);
//                response.setStatusCode(HttpStatus.OK.value());
//                response.setStatusMessage("Successfully received the job details");
//                response.setData(job1);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            }
//            else {
//                log.info("No job was found for the corresponding job ID");
//                response.setStatusCode(HttpStatus.NOT_FOUND.value());
//                response.setStatusMessage("No job was found for the corresponding job ID");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//            }
//
//        }catch(Exception e)
//        {
//            log.error("Exception occurred while getting job details ",e);
//            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setStatusMessage("Exception occurred while getting job details "+e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }

}