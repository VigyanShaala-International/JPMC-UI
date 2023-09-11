package com.vigyanshaala.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name="user_role")
@Data
public class UserRole {

//    @Enumerated(EnumType.STRING)
    private  String role;
    @Id
    private  String emailId;


    public UserRole(){}

}
