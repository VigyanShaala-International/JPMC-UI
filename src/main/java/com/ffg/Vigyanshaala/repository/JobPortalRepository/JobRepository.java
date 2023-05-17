package com.ffg.Vigyanshaala.repository.JobPortalRepository;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobLocation;
import com.ffg.Vigyanshaala.entity.JobPortalEntity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface JobRepository extends JpaRepository<Job,String>{

//    @Transactional
//    @Query(value="SELECT * from job where jobtitle_ID=:jobTitleId", nativeQuery = true)
//    public JobTitle getJobTitle (
//            @Param("jobTitleId") String jobTitleId);
//
//    @Transactional
//    @Query(value="SELECT * from  job where a.job_location_ID=:jobLocationId", nativeQuery = true)
//    public JobLocation getJobLocation (
//            @Param("jobLocationId") String jobLocationId);
}
