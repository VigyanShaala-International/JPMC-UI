package com.ffg.Vigyanshaala.serviceImpl.PdfGeneratorServiceImpl;

import com.ffg.Vigyanshaala.entity.DummyStudentTable;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.CompanyNameRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobLocationRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobRepository;
import com.ffg.Vigyanshaala.repository.JobPortalRepository.JobTitleRepository;
import com.ffg.Vigyanshaala.repository.PdfGeneratorRepository.DummyStudentTableRepository;
import com.ffg.Vigyanshaala.response.Response;
import org.springframework.stereotype.Service;

@Service
public class DummyStudentServicesImpl {
    private final DummyStudentTableRepository dummyStudentTableRepository;

    public DummyStudentServicesImpl(DummyStudentTableRepository dummyStudentTableRepository) {
        this.dummyStudentTableRepository = dummyStudentTableRepository;
    }

    public Response saveStudent(DummyStudentTable dummyStudentTable)
    {
        dummyStudentTableRepository.save(dummyStudentTable);
        Response response=new Response();
        response.setStatusCode(200);
        return response;
    }
}
