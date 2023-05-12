package com.ffg.Vigyanshaala.service.PdfGeneratorService;

import com.ffg.Vigyanshaala.model.PdfGenerator.SwotTemplate;
import com.ffg.Vigyanshaala.response.Response;
import org.springframework.http.ResponseEntity;

public interface SwotTemplateServices {

    public Response saveSwotTemplate(SwotTemplate swotTemplate);
}
