package com.ffg.Vigyanshaala.service.JobPortalService;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface SystemServices {

    public ResponseEntity deleteExpiredJobs(Date date);
}
