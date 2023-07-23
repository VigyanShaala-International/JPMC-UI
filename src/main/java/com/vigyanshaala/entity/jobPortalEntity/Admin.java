package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="admin")
@Data
public class Admin {

    @Id
    private  String adminId;
    private  String adminName;
    public Admin(){}
    public Admin(String adminId,String adminName)
    {
        this.adminName=adminName;
        this.adminId=adminId;
    }

}
