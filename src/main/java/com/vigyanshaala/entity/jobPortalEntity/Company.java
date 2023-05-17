package com.vigyanshaala.entity.jobPortalEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**This is the company table that stores all the company details which the admin has submitted
 * */
@Entity
@Table(name="company")
@Data
public class Company {

    @Id
    private final String companyId;
    private final String companyName;

}
