package com.ffg.Vigyanshaala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job_title_table")
public class JobTitle {
    private @Id
    @GeneratedValue Long job_title_id;
    private String job_title;

    public JobTitle(){}
    JobTitle(String job_title){
        this.job_title=job_title;
    }
    public String getJob_title()
    {
        return job_title;
    }
    public Long getJob_title_id()
    {
        return job_title_id;
    }
    public void setJob_title_id(Long job_title_id){
        this.job_title_id=job_title_id;
    }
    public void setJob_title(String job_title){
        this.job_title=job_title;
    }
}
