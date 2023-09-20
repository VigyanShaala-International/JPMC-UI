package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.CreativeMindset;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.CreativeMindsetServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/creativemindset")
@Slf4j
public class CreativeMindsetController {
    @Autowired
    CreativeMindsetServices creativeMindsetServices;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createCreativeMindsetTemplate(@RequestBody CreativeMindset creativeMindset) {
        Response response = new Response();
        try {
            log.info("creativeMindset template  is : {}" , creativeMindset.toString());
            response = creativeMindsetServices.saveCreativeMindsetTemplate(creativeMindset);
        } catch (Exception e) {
            log.error("Exception occurred while adding swotTemplateData  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding creativemindsetTemplateData  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get swot template latest version from the swot template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all creative mindset versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getCreativeMindsetLatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= creativeMindsetServices.getCreativeMindsetLatestVersion(studentEmail);
        }catch(Exception e){
            log.error("Exception occurred while getting creativemindset latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting creativemindset latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get creativemindset template data from the creativemindset template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all creativemindset versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getCreativeMindsetTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity=  creativeMindsetServices.getCreativeMindsetTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting CreativeMindset Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CreativeMindset Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
