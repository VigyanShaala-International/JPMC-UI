package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="job")
@Data
public class Job {

    private @Id
    Long job_ID;
    private
    String job_name;
    private String expiry_date;
    private String is_active;

    public Job(){}
    Job(String jobName, Long jobId, String expiryDate, String isActive){
        this.job_name=jobName;
        this.job_ID=jobId;
        this.expiry_date=expiryDate;
        this.is_active=isActive;
    }
    public String getJobName()
    {
        return job_name;
    }

    public void setJobName(String jobName){
        this.job_name=jobName;
    }
    public Long getJobId()
    {
        return job_ID;
    }
    public void setJobId(Long jobId)
    {
        this.job_ID=jobId;
    }
    public String getExpiryDate()
    {
        return expiry_date;
    }
    public void setExpiryDate(String expiryDate)
    {
        this.expiry_date=expiryDate;
    }

    public String getIsActive()
    {
        return is_active;
    }

    public void setIsActive(String isActive){
        this.is_active=isActive;
    }

}
