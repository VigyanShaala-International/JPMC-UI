package com.ffg.Vigyanshaala.repository;

import com.ffg.Vigyanshaala.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpiredJobsRepository extends JpaRepository<JobPosting,Long> {
//    LocalTime localTime = LocalTime.now();
    @Modifying
    @Query("UPDATE job_posting_table a set is_active='N' where a.expiry_date<:date and is_active='Y'")
    void softdeleteJobs(@Param("date") LocalDate date);
}
