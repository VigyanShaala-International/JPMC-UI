package com.ffg.Vigyanshaala.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="company_name_table")
public class CompanyName {
    private @Id
    @GeneratedValue Long company_id;
    private String company_name;

    public CompanyName(){}
    CompanyName(String company_name){
        this.company_name=company_name;
    }
    public String getCompany_name()
    {
        return company_name;
    }
    public Long getCompany_id()
    {
        return company_id;
    }
    public void setCompany_id(Long company_id){
        this.company_id=company_id;
    }
    public void setCompany_name(String company_name){
        this.company_name=company_name;
    }
}
