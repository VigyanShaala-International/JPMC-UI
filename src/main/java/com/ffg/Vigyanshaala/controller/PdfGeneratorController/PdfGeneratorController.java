package com.ffg.Vigyanshaala.controller.PdfGeneratorController;

import com.ffg.Vigyanshaala.entity.JobPortalEntity.Job;
import com.ffg.Vigyanshaala.model.PdfGenerator.SwotTemplate;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.PdfGeneratorService.SwotTemplateServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdfGenerator")
public class PdfGeneratorController {

    @Autowired
    SwotTemplateServices swotTemplateServices;

    @ApiOperation(value = "Health Check for the pdfGenerator route", notes = "Checks whether the route /pdfGenerator/ is healthy or not")
    @GetMapping("/")
    String healthCheck(){
        return "This pdfGenerator route is healthy";
    }



    @PostMapping(value="/saveSwotTemplate",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody SwotTemplate swotTemplate){
        Response response=new Response();
        try{
            System.out.println("The Job detail is : "+ swotTemplate.toString());
            response= swotTemplateServices.saveSwotTemplate(swotTemplate);
        }catch(Exception e){
            System.out.println("Exception occurred while adding swotTemplateData  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding swotTemplateData  "+e);
        }
        return response;
    }

}
