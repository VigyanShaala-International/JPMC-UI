package com.ffg.Vigyanshaala.controller.PdfGeneratorController;

import com.ffg.Vigyanshaala.model.PdfGenerator.SwotTemplate;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.PdfGeneratorService.SwotTemplateServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vigyaanshalaPdfGenerator")
public class PdfGeneratorController {
    @Autowired
    SwotTemplateServices swotTemplateServices;

    @ApiOperation(value = "Health Check for the pdfGenerator route", notes = "Checks whether the route /pdfGenerator/ is healthy or not")
    @GetMapping("/")
    String healthCheck() {
        return "This pdfGenerator route is healthy";
    }


    @PostMapping(value = "/saveSwotTemplate", consumes = "application/json", produces = "application/json")
    Response addCompany(@RequestBody SwotTemplate swotTemplate) {
        Response response = new Response();
        try {
            System.out.println("The Job detail is : " + swotTemplate.toString());
            response = swotTemplateServices.saveSwotTemplate(swotTemplate);
        } catch (Exception e) {
            System.out.println("Exception occurred while adding swotTemplateData  " + e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding swotTemplateData  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get swot template latest version from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/getSwotLatestVersion/{studentEmail}", produces="application/json")
    ResponseEntity getSwotLatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= swotTemplateServices.getSwotLatestVersion(studentEmail);
        }catch(Exception e){
            System.out.println("Exception occurred while getting swot latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting swot latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get swot template data from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/getSwotTemplate/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getSwotTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= swotTemplateServices.getSwotTemplate(studentEmail,version);
        }catch(Exception e){
            System.out.println("Exception occurred while getting Swot Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting Swot Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
