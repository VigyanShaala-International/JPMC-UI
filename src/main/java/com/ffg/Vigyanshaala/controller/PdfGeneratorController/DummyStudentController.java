package com.ffg.Vigyanshaala.controller.PdfGeneratorController;

import com.ffg.Vigyanshaala.entity.DummyStudentTable;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.PdfGeneratorService.DummyStudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DummyStudentController {
    @Autowired
    DummyStudentService dummyStudentService;

    @PostMapping(value="/saveStudent",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody DummyStudentTable dummyStudentTable){
        Response response=new Response();
        try{
            System.out.println("The Student detail is : "+ dummyStudentTable.toString());
            response= dummyStudentService.saveStudent(dummyStudentTable);
        }catch(Exception e){
            System.out.println("Exception occurred while adding student  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding student  "+e);
        }
        return response;
    }
}
