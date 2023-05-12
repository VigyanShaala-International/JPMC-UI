package com.ffg.Vigyanshaala.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dummy_student_table")
public class DummyStudentTable {
    private @Id @GeneratedValue String studentId;
    private String studentName;
    private String studentEmail;
    private String studentMobileNumber;


}
