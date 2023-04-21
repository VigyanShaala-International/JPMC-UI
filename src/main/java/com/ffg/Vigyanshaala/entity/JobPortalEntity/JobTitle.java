package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This is the job_title table which stores all the job titles that the admin has submitted*/
@Entity
@Table(name="job_title")
public class JobTitle {

    private @Id @GeneratedValue Long jobTitleId;
    private  String jobTitle;

    public JobTitle(){}
    JobTitle(String jobTitle, Long jobTitleId){
        this.jobTitle=jobTitle;
        this.jobTitleId=jobTitleId;
    }
    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle){
        this.jobTitle=jobTitle;
    }

    public Long getJobTitleId()
    {
        return jobTitleId;
    }
    public  void setJobTitleId(Long jobTitleId)
    {
        this.jobTitleId=jobTitleId;
    }
}
