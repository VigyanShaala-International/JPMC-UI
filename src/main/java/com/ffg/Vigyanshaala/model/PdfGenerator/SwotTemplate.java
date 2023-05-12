package com.ffg.Vigyanshaala.model.PdfGenerator;

import jakarta.persistence.Id;

public class SwotTemplate {
    private String strength;
    private String weakness;
    private String opportunity;
    private String threat;
    private  String studentEmail;
    private String studentDegree;
    private String shortTermGoal;

    public SwotTemplate(){}
    SwotTemplate(String strength,String weakness, String opportunity, String threat, String studentName, String studentDegree, String shortTermGoal){

        this.strength=strength;
        this.weakness=weakness;
        this.opportunity=opportunity;
        this.threat=threat;
        this.studentEmail=studentName;
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

    public String getWeakness(){return weakness;}
    public void setWeakness(String weakness){this.weakness=weakness;}
    public String getThreat(){return threat;}
    public void setThreat(String threat){this.threat=threat;}
    public String getOpportunity(){return opportunity;}
    public void setOpportunity(String opportunity){this.opportunity=opportunity;}
    public void setStudentEmail(String studentEmail){this.studentEmail=studentEmail;}
    public String getStudentEmail(){return studentEmail;}
    public void setStudentDegree(String studentDegree){this.studentDegree=studentDegree;}
    public String getStudentDegree(){return studentDegree;}
    public void setShortTermGoal(String shortTermGoal){this.shortTermGoal=shortTermGoal;}
    public String getShortTermGoal(){return shortTermGoal;}
}
