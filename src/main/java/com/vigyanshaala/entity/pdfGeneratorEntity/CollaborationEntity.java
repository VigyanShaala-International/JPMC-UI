package com.vigyanshaala.entity.pdfGeneratorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="collaboration_template")
@Data
public class CollaborationEntity {

    @Id
    private  String studentId;
    private  String studentEmail;
    private  Long version;
    private  String answerA;
    private  String answerB;
    private  String answerC;

    public CollaborationEntity() {

    }

}
