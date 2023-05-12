package com.ffg.Vigyanshaala.repository.PdfGenerator;
import com.ffg.Vigyanshaala.entity.PdfGeneratorEntity.SwotTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SwotTemplateRepository extends JpaRepository<SwotTemplateEntity,String> {

    @Transactional
    @Query(value="SELECT max(version) from swot_template a where a.student_email=:studentEmail", nativeQuery = true)
    public Long getLatestVersion (
            @Param("studentEmail") String studentEmail);


    @Transactional
    @Query(value="SELECT * from swot_template a where a.student_email=:studentEmail", nativeQuery = true)
    public List<SwotTemplateEntity> getAllVersions(
            @Param("studentEmail") String studentEmail);
}

