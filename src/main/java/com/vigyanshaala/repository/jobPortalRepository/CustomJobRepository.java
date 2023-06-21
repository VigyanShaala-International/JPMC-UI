package com.vigyanshaala.repository.jobPortalRepository;

import com.vigyanshaala.entity.jobPortalEntity.Job;

import java.util.List;

public interface CustomJobRepository {
    List<Job> fetchAll(JobFilter filterParams);
}
