package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="job")
@Data
public class Job {

    private @Id
    String jobId;

    private String postingDate;

    private String expiryDate;
    private String isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    private JobTitle jobTitle;

    private String jobDescription;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;


    public Job() {
    }

    public Job(String jobId, String postingDate, String expiryDate, String isActive, Company company, JobTitle jobTitle, String jobDescription, JobLocation jobLocation, Questionnaire questionnaire) {
        this.jobId = jobId;
        this.postingDate = postingDate;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.company = company;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.questionnaire = questionnaire;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJob_ID(String job_ID) {
        this.jobId = job_ID;
    }

    public String getPosting_date() {
        return postingDate;
    }

    public void setPosting_date(String posting_date) {
        this.postingDate = posting_date;
    }

    public String getExpiry_date() {
        return expiryDate;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiryDate = expiry_date;
    }

    public String getIs_active() {
        return isActive;
    }

    public void setIs_active(String is_active) {
        this.isActive = is_active;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public JobTitle getJob_title() {
        return jobTitle;
    }

    public void setJob_title(JobTitle job_title) {
        this.jobTitle = job_title;
    }

    public String getJob_description() {
        return jobDescription;
    }

    public void setJob_description(String job_description) {
        this.jobDescription = job_description;
    }

    public JobLocation getJob_location() {
        return jobLocation;
    }

    public void setJob_location(JobLocation job_location) {
        this.jobLocation = job_location;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private JobLocation jobLocation;




}
