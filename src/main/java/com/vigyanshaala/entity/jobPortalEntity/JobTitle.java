package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**This is the job_title table which stores all the job titles that the admin has submitted
 * */
@Entity
@Table(name="job_title")
@Data
public class JobTitle {

    @Id
    private  String jobTitleId;
    private  String jobTitle;
    public JobTitle(String jobTitleId,String jobTitle)
    {
        this.jobTitleId=jobTitleId;
        this.jobTitle=jobTitle;
    }


}
