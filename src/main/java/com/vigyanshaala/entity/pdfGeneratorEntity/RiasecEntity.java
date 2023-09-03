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
    private  String realisticScore;
    private  String investigativeScore;
    private  String artisticScore;
    private  String socialScore;
    private  String enterprisingScore;
    private  String conventionalScore;
    private  String firstInterest;
    private  String secondInterest;
    private  String thirdInterest;

    public RiasecEntity(){
    }
}
