package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This is the job_location table which stores all the job locations that the admin has submitted*/
@Entity
@Table(name="job_location")
public class JobLocation {

    private @Id @GeneratedValue Long jobLocationId;
    private  String jobLocation;

    public JobLocation(){}
    JobLocation(String job_location, Long jobId){
        this.jobLocation=jobLocation;
        this.jobLocationId=jobId;
    }
    public String getJobLocation()
    {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation){
        this.jobLocation=jobLocation;
    }
    public Long getJobLocationId()
    {
        return jobLocationId;
    }
    public void setJobLocationId(Long jobLocationId)
    {
        this.jobLocationId=jobLocationId;
    }

}
