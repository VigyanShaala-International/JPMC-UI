package com.ffg.Vigyanshaala.repository.JobPortalRepository;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ExpiredJobsRepository extends JpaRepository<Job,Long> {
        @Modifying
        @Transactional
        @Query(value="UPDATE Job a set is_active='N' where a.expiry_date<:expiryDate and is_active='Y'", nativeQuery = true)
        public void softdeleteJobs (
            @Param("expiryDate") String expiryDate);

}

