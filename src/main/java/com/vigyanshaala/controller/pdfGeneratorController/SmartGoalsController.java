package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.SmartGoalsTemplate;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.SmartGoalsTemplateServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/smartgoals")
@Slf4j
public class SmartGoalsController {
    @Autowired
    SmartGoalsTemplateServices smartGoalsTemplateServices;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createSmartGoalsTemplate(@RequestBody SmartGoalsTemplate smartGoalsTemplate) {
        Response response = new Response();
        try {
            log.info("The smartGoals template  is : {}" , smartGoalsTemplate.toString());
            response = smartGoalsTemplateServices.saveSmartGoalsTemplate(smartGoalsTemplate);
        } catch (Exception e) {
            log.error("Exception occurred while adding smartGoalsTemplateData  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding smartGoalsTemplateData  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get smartGoals template latest version from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all smartGoals versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getSmartGoalsLatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= smartGoalsTemplateServices.getSmartGoalsLatestVersion(studentEmail);
        }catch(Exception e){
            log.error("Exception occurred while getting smartGoals latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting smartGoals latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get swot template data from the smartGoals template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all smartGoals versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getSmartGoalsTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= smartGoalsTemplateServices.getSmartGoalsTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting SmartGoals Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting SmartGoals Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
