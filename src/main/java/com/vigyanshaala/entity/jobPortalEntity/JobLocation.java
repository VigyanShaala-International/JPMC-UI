package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*This is the job_location table which stores all the job locations that the admin has submitted*/
@Entity
@Table(name="job_location")
@Data
public class JobLocation {

    @Id
    private final String jobLocationId;
    private final String jobLocation;

}
