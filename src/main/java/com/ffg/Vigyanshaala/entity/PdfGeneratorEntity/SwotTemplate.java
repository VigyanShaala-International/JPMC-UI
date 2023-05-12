package com.ffg.Vigyanshaala.entity.PdfGeneratorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This is the job_title table which stores all the job titles that the admin has submitted*/
@Entity
@Table(name="swot_template")
public class SwotTemplate {

    private @Id Long studentID;
    private @Id Long version;
    private String strength;
    private String weakness;
    private String opportunity;
    private String threat;
    private  String studentName;
    private String studentDegree;
    private String shortTermGoal;

    public SwotTemplate(){}
    SwotTemplate(Long studentID, Long version, String strength,String weakness, String opportunity, String threat, String studentName, String studentDegree, String shortTermGoal){
        this.studentID=studentID;
        this.version=version;
        this.strength=strength;
        this.weakness=weakness;
        this.opportunity=opportunity;
        this.threat=threat;
        this.studentName=studentName;
        this.studentDegree=studentDegree;
        this.shortTermGoal=shortTermGoal;

    }
    public String getStrength()
    {
        return strength;
    }

    public void setStrength(String strength){
        this.strength=strength;
    }

    public Long getStudentID()
    {
        return studentID;
    }
    public  void setStudentID(Long studentID)
    {
        this.studentID=studentID;
    }
    public Long getVersion(){return version;}
    public void setVersion(Long version){this.version=version;}
    public String getWeakness(){return weakness;}
    public void setWeakness(String weakness){this.weakness=weakness;}
    public String getThreat(){return threat;}
    public void setThreat(String threat){this.threat=threat;}
    public String getOpportunity(){return opportunity;}
    public void setOpportunity(String opportunity){this.opportunity=opportunity;}
    public void setStudentName(String studentName){this.studentName=studentName;}
    public String getStudentName(){return studentName;}
    public void setStudentDegree(String studentDegree){this.studentDegree=studentDegree;}
    public String getStudentDegree(){return studentDegree;}
    public void setShortTermGoal(String shortTermGoal){this.shortTermGoal=shortTermGoal;}
    public String getShortTermGoal(){return shortTermGoal;}
}