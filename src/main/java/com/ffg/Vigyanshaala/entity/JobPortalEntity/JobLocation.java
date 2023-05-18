package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.*;

/*This is the job_location table which stores all the job locations that the admin has submitted*/
@Entity
@Table(name="job_location")
public class JobLocation {

    private @Id String job_location_ID;
    private  String job_location;

    public JobLocation(){}
    JobLocation(String jobLocation, String jobId){
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
    public String getJobLocationId()
    {
        return job_location_ID;
    }
    public void setJobLocationId(String jobLocationId)
    {
        this.job_location_ID=jobLocationId;
    }

}
