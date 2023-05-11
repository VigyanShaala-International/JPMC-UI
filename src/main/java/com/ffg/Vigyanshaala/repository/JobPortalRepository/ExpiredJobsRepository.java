package com.ffg.Vigyanshaala.repository.JobPortalRepository;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface ExpiredJobsRepository extends JpaRepository<Job,Long> {
    //    LocalTime localTime = LocalTime.now();
    @Modifying
    @Query("UPDATE Job a set isActive='N' where a.expiryDate<:date and isActive='Y'")
    public ArrayList<Job> softdeleteJobs(
            @Param("date") Date date);

}

