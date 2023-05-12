package com.ffg.Vigyanshaala.entity.JobPortalEntity;


import jakarta.persistence.*;

/*This is the company table that stores all the company details which the admin has submitted */
@Entity
@Table(name="company")
public class Company {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long company_ID;
    private
    String company_name;

    public Company(){}
    Company(String companyName, Long companyId){
        this.company_name=companyName;
        this.company_ID=companyId;
    }
    public String getCompanyName()
    {
        return company_name;
    }

    public void setCompanyName(String companyName){
        this.company_name=companyName;
    }
    public Long getCompanyId()
    {
        return company_ID;
    }
    public void setCompanyId(Long companyId)
    {
        this.company_ID=companyId;
    }
}
