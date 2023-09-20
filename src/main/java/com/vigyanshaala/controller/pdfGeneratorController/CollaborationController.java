package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.Collaboration;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.CollaborationServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/collaboration")
@Slf4j
public class CollaborationController {
    @Autowired
    CollaborationServices collaborationServices;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createCollaborationTemplate(@RequestBody Collaboration collaboration) {
        Response response = new Response();
        try {
            log.info("Collaboration template  is : {}" , collaboration.toString());
            response = collaborationServices.saveCollaborationTemplate(collaboration);
        } catch (Exception e) {
            log.error("Exception occurred while adding collaborationTemplateData  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding collaborationTemplateData  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get collaboration template latest version from the collaboration template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all collaboration versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getCollaborationLatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= collaborationServices.getCollaborationLatestVersion(studentEmail);
        }catch(Exception e){
            log.error("Exception occurred while getting collaboration latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting collaboration latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get collaboration template data from the collaboration template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all collaboration versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getCollaborationTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= collaborationServices.getCollaborationTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting Collaboration Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting Collaboration Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
