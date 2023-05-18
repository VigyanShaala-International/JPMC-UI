package com.vigyanshaala.entity.pdfGeneratorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**This is the job_title table which stores all the job titles that the admin has submitted*/
@Entity
@Table(name="swot_template")
@Data
public class SwotTemplateEntity {

    @Id
    private  String studentId;
    private  String studentEmail;
    private  Long version;
    private  String strength;
    private  String weakness;
    private  String opportunity;
    private  String threat;
    private  String studentName;
    private  String studentDegree;
    private  String goal;

    public SwotTemplateEntity(){
    }
}