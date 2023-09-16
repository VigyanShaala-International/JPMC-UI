package com.vigyanshaala.controller.jobPortalController;

import com.lowagie.text.DocumentException;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.SystemServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/system")
@Slf4j
public class SystemController {

    @Autowired
    SystemServices systemServices;
    @PostMapping(value="/deleteExpiredJobs", produces="application/json")
    ResponseEntity<Response> deleteExpiredJobs() {
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            responseEntity = systemServices.deleteExpiredJobs(formatter.format(date));
        } catch (Exception e) {
            log.error("Exception occurred while deleting job ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while deleting job" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @GetMapping("/pdf/jobApplications")
    public ResponseEntity generatePdf() throws DocumentException, IOException {
        Response response = new Response();
        //HttpServletResponse response = null;
        //response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        //response.setHeader(headerkey, headervalue);
        ResponseEntity responseEntity;
        //ResponseEntity jobApplicationList = systemServices.mailJobApplicationsToHr();

        try {
            responseEntity = systemServices.mailJobApplicationsToHr();
        } catch (Exception e) {
            log.error("Exception occurred while deleting job ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while deleting job" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;


    }
}
