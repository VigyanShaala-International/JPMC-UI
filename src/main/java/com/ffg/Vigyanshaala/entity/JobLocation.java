package com.ffg.Vigyanshaala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="job_location_table")
public class JobLocation {

    private @Id String job_location;

    public JobLocation(){}
    JobLocation(String job_location){
        this.job_location=job_location;
    }
    public String getJob_location()
    {
        return job_location;
    }

    public void setJob_location(String job_location){
        this.job_location=job_location;
    }
}
