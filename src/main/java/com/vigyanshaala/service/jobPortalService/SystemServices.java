package com.vigyanshaala.service.jobPortalService;

import org.springframework.http.ResponseEntity;

import java.io.File;

public interface SystemServices {

    ResponseEntity deleteExpiredJobs(String date);

    ResponseEntity mailJobApplicationsToHr();

    ResponseEntity deleteDirectory(File directory);
}
