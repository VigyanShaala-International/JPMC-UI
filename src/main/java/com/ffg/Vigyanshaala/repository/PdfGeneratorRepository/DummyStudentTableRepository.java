package com.ffg.Vigyanshaala.repository.PdfGeneratorRepository;

import com.ffg.Vigyanshaala.entity.DummyStudentTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyStudentTableRepository extends JpaRepository<DummyStudentTable,Long> {
    DummyStudentTable findByStudentEmail(String studentEmail);
}
