package com.ffg.Vigyanshaala.entity.JobPortalEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This is the company table that stores all the company details which the admin has submitted */
@Entity
@Table(name="company")
public class Company {

    private @Id
    @GeneratedValue Long companyId;
    private
    String companyName;

    public Company(){}
    Company(String companyName, Long companyId){
        this.companyName=companyName;
        this.companyId=companyId;
    }
    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }
    public Long getCompanyId()
    {
        return companyId;
    }
    public void setCompanyId(Long companyId)
    {
        this.companyId=companyId;
    }
}
