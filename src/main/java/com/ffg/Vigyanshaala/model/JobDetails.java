package com.ffg.Vigyanshaala.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job_details")
public class JobDetails implements Serializable {
    @Id
    @GeneratedValue
    private String jobId;

    @Column(name="company_name")
    private String companyName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "job_salary")
    private String salary;


    // Empty constructor
    public JobDetails() {
    }
// Todo to post jobdetails
    //    public JobDetails(String jobId, String companyName, String jobTitle, String jobDescription, String salary, String jobLocation, Date postingDate, Date expiryDate, String question1, String question2, String question3, String question4, String question5) {
//        this.jobId = jobId;
//        this.companyName = companyName;
//        this.jobTitle = jobTitle;
//        this.jobDescription = jobDescription;
//        this.salary = salary;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
//        this.jobLocation = jobLocation;
//        this.postingDate = postingDate;
//        this.expiryDate = expiryDate;
//        this.question1 = question1;
//        this.question2 = question2;
//        this.question3 = question3;
//        this.question4 = question4;
//        this.question5 = question5;
//    }

    @Column(name = "job_location")
    private String jobLocation;

    @Column(name = "posting_date")
    private Date postingDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "question_1")
    private String question1;

    @Column(name = "question_2")
    private String question2;

    @Column(name = "question_3")
    private String question3;

    @Column(name = "question_4")
    private String question4;

    @Column(name = "question_5")
    private String question5;














}
/*




@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobDetails {
    private String companyName;
    //private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String salary;
    private String jobLocation;
    private Date postingDate;
    private Date expiryDate;

    private String question1;



    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    private String question2;

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    private String question3;

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    private String question4;

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    private String question5;

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }
//
//    public String getJobId(){
//        return jobId;
//    }
//    public void setJobId(String jobId){
//        this.jobId=jobId;
//    }

    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle=jobTitle;
    }


    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }



    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }



    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }



    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }



    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }






}
*/

