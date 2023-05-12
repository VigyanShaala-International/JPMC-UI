package com.ffg.Vigyanshaala.entity.PdfGeneratorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This is the job_title table which stores all the job titles that the admin has submitted*/
@Entity
@Table(name="swot_template")
public class SwotTemplateEntity {

    private @Id String student_ID;
    private String student_email;
    private Long version;
    private String strength;
    private String weakness;
    private String opportunity;
    private String threat;
    private  String student_name;
    private String student_degree;
    private String goal;

    public SwotTemplateEntity(){}
    SwotTemplateEntity(String studentID, String studentEmail, Long version, String strength, String weakness, String opportunity, String threat, String studentName, String studentDegree, String goal){
        this.student_ID=studentID;
        this.student_email=studentEmail;
        this.version=version;
        this.strength=strength;
        this.weakness=weakness;
        this.opportunity=opportunity;
        this.threat=threat;
        this.student_name=studentName;
        this.student_degree=studentDegree;
        this.goal=goal;

    }
    public String getStrength()
    {
        return strength;
    }

    public void setStrength(String strength){
        this.strength=strength;
    }

    public String getStudentID()
    {
        return student_ID;
    }
    public  void setStudentID(String studentID)
    {
        this.student_ID=studentID;
    }
    public Long getVersion(){return version;}
    public void setVersion(Long version){this.version=version;}
    public String getWeakness(){return weakness;}
    public void setWeakness(String weakness){this.weakness=weakness;}
    public String getThreat(){return threat;}
    public void setThreat(String threat){this.threat=threat;}
    public String getOpportunity(){return opportunity;}
    public void setOpportunity(String opportunity){this.opportunity=opportunity;}
    public void setStudentName(String studentName){this.student_name=studentName;}
    public String getStudentName(){return student_name;}
    public void setStudentDegree(String studentDegree){this.student_degree=studentDegree;}
    public String getStudentDegree(){return student_degree;}
    public void setGoal(String goal){this.goal=goal;}
    public String getGoal(){return goal;}
    public void setStudentEmailID(String studentEmail){this.student_email=studentEmail;}
    public String getStudentEmailID(){return  student_email;}
}