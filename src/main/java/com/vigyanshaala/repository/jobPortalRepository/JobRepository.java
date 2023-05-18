package com.ffg.Vigyanshaala.repository.JobPortalRepository;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,String>{
}
