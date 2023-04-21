package com.ffg.Vigyanshaala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job_title_table")
public class JobTitle {

    private @Id String job_title;

    public JobTitle(){}
    JobTitle(String job_title){
        this.job_title=job_title;
    }
    public String getJob_title()
    {
        return job_title;
    }

    public void setJob_title(String job_title){
        this.job_title=job_title;
    }
}
