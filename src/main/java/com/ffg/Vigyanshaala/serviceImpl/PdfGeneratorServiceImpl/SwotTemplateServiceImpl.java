package com.ffg.Vigyanshaala.serviceImpl.PdfGeneratorServiceImpl;

import com.ffg.Vigyanshaala.entity.PdfGeneratorEntity.SwotTemplateEntity;
import com.ffg.Vigyanshaala.model.PdfGenerator.SwotTemplate;
import com.ffg.Vigyanshaala.repository.PdfGenerator.SwotTemplateRepository;
import com.ffg.Vigyanshaala.response.Response;
import com.ffg.Vigyanshaala.service.PdfGeneratorService.SwotTemplateServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwotTemplateServiceImpl implements SwotTemplateServices {

    private final SwotTemplateRepository swotTemplateRepository;
    public SwotTemplateServiceImpl(SwotTemplateRepository swotTemplateRepository){
        this.swotTemplateRepository = swotTemplateRepository;

    }

    @Override
    public Response saveSwotTemplate(SwotTemplate swotTemplate){

        Response response=new Response();
        try {
            SwotTemplateEntity swotTemplateEntity = new SwotTemplateEntity();
            Long version = swotTemplateRepository.getLatestVersion(swotTemplate.getStudentEmail());
            System.out.println("Version " + version);
            if (version == null)
                version = 0L;
            else
                version = version + 1;
            swotTemplateEntity.setStudentID(swotTemplate.getStudentEmail() + "_" + version);
            swotTemplateEntity.setStudentEmailID(swotTemplate.getStudentEmail());
            swotTemplateEntity.setGoal(swotTemplate.getGoal());
            swotTemplateEntity.setStudentName(swotTemplate.getStudentName());
            swotTemplateEntity.setThreat(swotTemplate.getThreat());
            swotTemplateEntity.setOpportunity(swotTemplate.getOpportunity());
            swotTemplateEntity.setStrength(swotTemplate.getStrength());
            swotTemplateEntity.setWeakness(swotTemplate.getWeakness());
            swotTemplateEntity.setStudentDegree(swotTemplate.getStudentDegree());
            swotTemplateEntity.setVersion(version);


            System.out.println("Entity is :"+swotTemplateEntity.toString());

            swotTemplateRepository.save(swotTemplateEntity);
        }catch(Exception e)
        {
            System.out.println("Exception occurred while saving swot template "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while saving job location "+e);
        }


        response.setStatusCode(200);
        response.setStatusMessage("Successfully saved Swot Template data");
        return  response;
    }
    @Override
    public ResponseEntity getSwotLatestVersion(String studentEmail)
    {
        Response response=new Response();
        try{
            Long version=swotTemplateRepository.getLatestVersion(studentEmail);
            response.setData(version);
            response.setStatusMessage("Successfully received latest version of swot template for email "+studentEmail);
            response.setStatusCode(200);
        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting latest version of swot template "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting latest version of swot template "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Override
    public ResponseEntity getSwotTemplate(String studentEmail, Long version)
    {
        Response response=new Response();
        try{
            List<SwotTemplateEntity> swotTemplateList=swotTemplateRepository.getTemplate(studentEmail,version);
            response.setData(swotTemplateList);
            response.setStatusMessage("Successfully received swot template data for email "+studentEmail+" and version "+version);
            response.setStatusCode(200);
        }catch(Exception e)
        {
            System.out.println("Exception occurred while getting swot template "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting swot template "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
