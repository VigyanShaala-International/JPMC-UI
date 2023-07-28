package com.vigyanshaala.controller.pdfGeneratorController;

import com.vigyanshaala.model.pdfGeneratorModel.CriticalThinking;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.pdfGeneratorService.CriticalThinkingServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pdf/ct")
@Slf4j
public class CriticalThinkingController {
    @Autowired
    CriticalThinkingServices criticalThinkingServices;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    Response createCriticalThinkingTemplate(@RequestBody CriticalThinking criticalThinking) {
        Response response = new Response();
        try {
            log.info("The critical thinking template  is : {}" , criticalThinking.toString());
            response = criticalThinkingServices.saveCriticalThinkingTemplate(criticalThinking);
        } catch (Exception e) {
            log.error("Exception occurred while adding criticalThinking template data  " , e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding critical thinking template  " + e);
        }
        return response;
    }
    @ApiOperation(value = "Get swot template latest version from the critical thinking template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/version/{studentEmail}", produces="application/json")
    ResponseEntity getCTLatestVersion(@PathVariable("studentEmail") String studentEmail){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= criticalThinkingServices.getCriticalThinkingLatestVersion(studentEmail);
        }catch(Exception e){
            log.error("Exception occurred while getting ct latest version "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT latest version"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get swot template data from the critical thinking template table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all swot versions.")
    @GetMapping(value="/{studentEmail}/{version}", produces="application/json")
    ResponseEntity<Response> getCriticalThinkingTemplate(@PathVariable("studentEmail") String studentEmail,@PathVariable("version") Long version){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= criticalThinkingServices.getCriticalThinkingTemplate(studentEmail,version);
        }catch(Exception e){
            log.error("Exception occurred while getting CT Template data "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting CT Template data"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }
}
