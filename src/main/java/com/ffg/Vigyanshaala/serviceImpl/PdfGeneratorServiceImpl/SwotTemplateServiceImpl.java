package com.ffg.Vigyanshaala.serviceImpl.PdfGeneratorServiceImpl;

import com.ffg.Vigyanshaala.entity.DummyStudentTable;
import com.ffg.Vigyanshaala.model.PdfGenerator.SwotTemplate;
import com.ffg.Vigyanshaala.repository.PdfGeneratorRepository.DummyStudentTableRepository;
import com.ffg.Vigyanshaala.repository.PdfGeneratorRepository.SwotTemplateRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.PdfGeneratorService.SwotTemplateServices;
import org.springframework.http.HttpStatus;

import java.util.List;

public class SwotTemplateServiceImpl implements SwotTemplateServices {

    private final SwotTemplateRepository swotTemplateRepository;
    private final DummyStudentTableRepository dummyStudentTableRepository;

    public SwotTemplateServiceImpl(SwotTemplateRepository swotTemplateRepository, DummyStudentTableRepository dummyStudentTableRepository) {
        this.swotTemplateRepository = swotTemplateRepository;
        this.dummyStudentTableRepository=dummyStudentTableRepository;
    }

    @Override
    public Response saveSwotTemplate(SwotTemplate swotTemplate){

//        Response response=new Response();
//        DummyStudentTable dummyStudentTable= dummyStudentTableRepository.findByStudentEmail(swotTemplate.getStudentEmail());
//        System.out.println("Data "+dummyStudentTable);
        Response response1=new Response();
        response1.setStatusCode(200);
        return  response1;
    }
}
