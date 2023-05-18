package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="job")
@Data
public class Job {

    private @Id
    String job_ID;

    private String posting_date;

    private String expiry_date;
    private String is_active;

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    private JobTitle job_title;

    private String job_description;

    public Job() {
    }

    public Job(String job_ID, String posting_date, String expiry_date, String is_active, Company company, JobTitle job_title, String job_description, JobLocation job_location) {
        this.job_ID = job_ID;
        this.posting_date = posting_date;
        this.expiry_date = expiry_date;
        this.is_active = is_active;
        this.company = company;
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_location = job_location;
    }

    public String getJob_ID() {
        return job_ID;
    }

    public void setJob_ID(String job_ID) {
        this.job_ID = job_ID;
    }

    public String getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(String posting_date) {
        this.posting_date = posting_date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public JobTitle getJob_title() {
        return job_title;
    }

    public void setJob_title(JobTitle job_title) {
        this.job_title = job_title;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public JobLocation getJob_location() {
        return job_location;
    }

    public void setJob_location(JobLocation job_location) {
        this.job_location = job_location;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private JobLocation job_location;




}
