package com.ffg.Vigyanshaala.service.JobPortalService;

import org.springframework.http.ResponseEntity;

public interface SystemServices {

    public ResponseEntity deleteExpiredJobs(String date);
}
