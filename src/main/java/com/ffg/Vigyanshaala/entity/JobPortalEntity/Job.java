package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.print.attribute.standard.JobName;

@Entity
@Table(name="job")
public class Job {

    private @Id
    @GeneratedValue Long jobId;
    private
    String jobName;

    public Job(){}
    Job(String jobName, Long jobId){
        this.jobName=jobName;
        this.jobId=jobId;
    }
    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName){
        this.jobName=jobName;
    }
    public Long getJobId()
    {
        return jobId;
    }
    public void setJobId(Long jobId)
    {
        this.jobId=jobId;
    }
}
