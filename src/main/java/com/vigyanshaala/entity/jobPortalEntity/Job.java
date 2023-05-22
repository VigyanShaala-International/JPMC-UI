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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public JobLocation getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(JobLocation jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;

    @ManyToOne(cascade = CascadeType.ALL)
    private JobLocation jobLocation;

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











}
