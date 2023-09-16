package com.vigyanshaala.entity.pdfGeneratorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "idp_template")
@Data
public class IDPTemplateEntity {
    @Id
    private String studentId;
    private String studentEmail;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private String studentName;
    private Long version;
    private String goalagoal1;
    private String goalaname;
    private String goalaeducation1;
    private String goalaeducation2;
    private String goalaproudMoments;
    private String goalaraisec;
    private String goalamatchStrength;
    private String goaladecreaseThreat;
    private String goalaaquiredHardSkills;
    private String goalaaquiredSoftSkills;

    private String goalagoal2;
    private String goalacareerOptions;
    private String goalahardSkillsToAquire;
    private String goalasoftSkillsToAquire;
    private String goalaemployers;
    private String goalamentor1;
    private String goalamentor2;
    private String goalamentor3;
    private String goalamentor4;
    private String goalamileStone1;
    private String goalamileStone2;
    private String goalamileStone3;
    private String goalam1Step1;
    private String goalam1Step2;
    private String goalam1Step3;
    private String goalam2Step1;
    private String goalam2Step2;
    private String goalam2Step3;
    private String goalam3Step1;
    private String goalam3Step2;
    private String goalam3Step3;
    private String goalaadjustment;
    private String goalareview;
    private String goalbgoal1;
    private String goalbname;
    private String goalbeducation1;
    private String goalbeducation2;
    private String goalbproudMoments;
    private String goalbraisec;
    private String goalbmatchStrength;
    private String goalbdecreaseThreat;
    private String goalbaquiredHardSkills;
    private String goalbaquiredSoftSkills;

    private String goalbgoal2;
    private String goalbcareerOptions;
    private String goalbhardSkillsToAquire;
    private String goalbsoftSkillsToAquire;
    private String goalbemployers;
    private String goalbmentor1;
    private String goalbmentor2;
    private String goalbmentor3;
    private String goalbmentor4;
    private String goalbmileStone1;
    private String goalbmileStone2;
    private String goalbmileStone3;
    private String goalbm1Step1;
    private String goalbm1Step2;
    private String goalbm1Step3;
    private String goalbm2Step1;
    private String goalbm2Step2;
    private String goalbm2Step3;
    private String goalbm3Step1;
    private String goalbm3Step2;
    private String goalbm3Step3;

    public IDPTemplateEntity() {
    }

    private String goalbadjustment;
    private String goalbreview;
}
