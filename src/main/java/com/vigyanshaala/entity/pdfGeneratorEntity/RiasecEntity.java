package com.vigyanshaala.entity.pdfGeneratorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ria_template")
@Data
public class RiasecEntity {

    @Id
    private  String studentId;
    private  String studentEmail;
    private  Long version;
    private  String realistic;
    private  String investigative;
    private  String artistic;
    private  String social;
    private  String enterprising;
    private  String conventional;
    private  String hollandCode;

    public RiasecEntity(){
    }
}
