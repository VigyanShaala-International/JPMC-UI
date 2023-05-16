package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.*;

/*This is the job_title table which stores all the job titles that the admin has submitted*/
@Entity
@Table(name="job_title")
public class JobTitle {

    private @Id String job_title_ID;
    private  String job_title;

    public JobTitle(){}
    JobTitle(String jobTitle, String jobTitleId){
        this.job_title=jobTitle;
        this.job_title_ID=jobTitleId;
    }
    public String getJobTitle()
    {
        return job_title;
    }

    public void setJobTitle(String jobTitle){
        this.job_title=jobTitle;
    }

    public String getJobTitleId()
    {
        return job_title_ID;
    }
    public  void setJobTitleId(String jobTitleId)
    {
        this.job_title_ID=jobTitleId;
    }
}
