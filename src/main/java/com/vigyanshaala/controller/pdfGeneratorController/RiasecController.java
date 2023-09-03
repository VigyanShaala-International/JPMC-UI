package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.RiasecTemplate;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.RiasecServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/raisec")
@Slf4j
public class RiasecController {
    @Autowired
    RiasecServices riasecServices;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createRiasecTemplate(@RequestBody RiasecTemplate riasecTemplate) {
        Response response = new Response();
        try {
            log.info("The riasec template  is : {}" , riasecTemplate.toString());
            response = riasecServices.saveRiasecTemplate(riasecTemplate);
        } catch (Exception e) {
            log.error("Exception occurred while adding Riasec template data  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding riasec template  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get riasec template latest version from the riasec template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getRILatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= riasecServices.getRiasecLatestVersion(studentEmail);
        }catch(Exception e){
            log.error("Exception occurred while getting RI latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting RI latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get Riasec template data from the Riasec template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all riasec versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getRiasecTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= riasecServices.getRiasecTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting Riasec Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting Riasec Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
