package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.IDPTemplate;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.IDPTemplateServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/swot")
@Slf4j
public class IDPController {
    @Autowired
    IDPTemplateServices idpTemplateServices;

    @PostMapping(value = "/idp", consumes = "application/json", produces = "application/json")
    Response createIDPTemplate(@RequestBody IDPTemplate idpTemplate) {
        Response response = new Response();
        try {
            log.info("The idp template  is : {}", idpTemplate.toString());
            response = idpTemplateServices.saveIDPTemplate(idpTemplate);
        } catch (Exception e) {
            log.error("Exception occurred while adding idpTemplateData  ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding idpTemplateData  " + e);
        }
        return response;
    }

    @ApiOperation(value = "Get idp template latest version from the idp template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all idp versions.")
    @GetMapping(value = "/idp/version/{studentEmail}", produces = "application/json")
    ResponseEntity getIDPLatestVersion(@PathVariable("studentEmail") String studentEmail) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try {
            responseEntity = idpTemplateServices.getIDPLatestVersion(studentEmail);
        } catch (Exception e) {
            log.error("Exception occurred while getting idp latest version " + e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting idp latest version" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get idp template data from the idp template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all idp versions.")
    @GetMapping(value = "/idp/{studentEmail}/{version}", produces = "application/json")
    ResponseEntity<Response> getIDPTemplate(@PathVariable("studentEmail") String studentEmail, @PathVariable("version") Long version) {
        ResponseEntity responseEntity;
        Response response = new Response();
        try {
            responseEntity = idpTemplateServices.getIDPTemplate(studentEmail, version);
        } catch (Exception e) {
            log.error("Exception occurred while getting idp Template data " + e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting idp Template data" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}