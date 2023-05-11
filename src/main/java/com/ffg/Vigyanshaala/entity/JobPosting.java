package com.ffg.Vigyanshaala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

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
    private LocalDate posting_date;
    private LocalDate expiry_date;
    private String is_active;
    public LocalDate getPosting_date()
    {
        return posting_date;
    }
    public void setPosting_date(String posting_date){
        this.posting_date= LocalDate.parse(posting_date);
    }
    public LocalDate getExpiry_date()
    {
        return expiry_date;
    }
    public void setExpiry_date(String expiry_date){
        this.expiry_date= LocalDate.parse(expiry_date);
    }
    public String getIs_active()
    {
        return is_active;
    }
    public void setIs_active(String is_active){
        this.is_active=is_active;
    }
}
