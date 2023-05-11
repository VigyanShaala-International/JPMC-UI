package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.print.attribute.standard.JobName;
import java.util.Date;

@Entity
@Table(name="job")
public class Job {

    private @Id
    @GeneratedValue Long jobId;
    private
    String jobName;
    private Date expiryDate;
    private String isActive;

    public Job(){}
    Job(String jobName, Long jobId, Date expiryDate, String isActive){
        this.jobName=jobName;
        this.jobId=jobId;
        this.expiryDate=expiryDate;
        this.isActive=isActive;
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
    public Date getExpiryDate()
    {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate=expiryDate;
    }

    public String getIsActive()
    {
        return isActive;
    }

    public void setIsActive(String isActive){
        this.isActive=isActive;
    }

}
