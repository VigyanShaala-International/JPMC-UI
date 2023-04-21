package com.ffg.Vigyanshaala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;

@Entity
public class JobPosting {
    private @Id
    @GeneratedValue Long job_id;

//    @OneToOne
//    @JoinColumn(
//            name="company_id", unique=true, nullable=false, updatable=false)
//    public CompanyId getCompanyId() { return companyid; }
//
//    @OneToOne
//    public Company getCompany() { return company; }
    public Long getJob_id()
{
    return job_id;
}
    public void setJob_id(Long job_id){
        this.job_id=job_id;
    }
    private String posting_date;
    private String expiry_date;
    private String is_active;
    public String getPosting_date()
    {
        return posting_date;
    }
    public void setPosting_date(String posting_date){
        this.posting_date=posting_date;
    }
    public String getExpiry_date()
    {
        return expiry_date;
    }
    public void setExpiry_date(String expiry_date){
        this.expiry_date=expiry_date;
    }
    public String getIs_active()
    {
        return is_active;
    }
    public void setIs_active(String is_active){
        this.is_active=is_active;
    }
}
