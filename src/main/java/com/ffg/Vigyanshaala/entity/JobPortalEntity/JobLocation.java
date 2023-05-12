package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.*;

/*This is the job_location table which stores all the job locations that the admin has submitted*/
@Entity
@Table(name="job_location")
public class JobLocation {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long job_location_ID;
    private  String job_location;

    public JobLocation(){}
    JobLocation(String jobLocation, Long jobId){
        this.job_location=jobLocation;
        this.job_location_ID=jobId;
    }
    public String getJobLocation()
    {
        return job_location;
    }

    public void setJobLocation(String jobLocation){
        this.job_location=jobLocation;
    }
    public Long getJobLocationId()
    {
        return job_location_ID;
    }
    public void setJobLocationId(Long jobLocationId)
    {
        this.job_location_ID=jobLocationId;
    }

}
