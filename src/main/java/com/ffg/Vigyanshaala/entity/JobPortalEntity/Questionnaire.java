package com.ffg.Vigyanshaala.entity.JobPortalEntity;

import jakarta.persistence.*;

/*This is the company table that stores all the company details which the admin has submitted */
@Entity
@Table(name="questionnaire")
public class Questionnaire {

    private @Id
    String questionnaire_ID;

    @OneToOne(cascade = CascadeType.ALL)
    private Job job;
    private String question1;
    private String question2;
    private String question3;
    private String question4;

    public Questionnaire() {
    }

    public String getQuestionnaire_ID() {
        return questionnaire_ID;
    }

    public Questionnaire(String questionnaire_ID, Job job, String question1, String question2, String question3, String question4, String question5) {
        this.questionnaire_ID = questionnaire_ID;
        this.job = job;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    public void setQuestionnaire_ID(String questionnaire_ID) {
        this.questionnaire_ID = questionnaire_ID;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    private String question5;



}
