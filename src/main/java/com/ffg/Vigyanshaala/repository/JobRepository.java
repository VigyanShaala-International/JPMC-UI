package com.ffg.Vigyanshaala.repository;
import com.ffg.Vigyanshaala.model.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<JobDetails, String> {

}
