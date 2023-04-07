package com.ffg.Vigyanshaala.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobDetails {
    private String companyName;

    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName=companyName;
    }
    private String jobId;
    public String getJobId(){
        return jobId;
    }
    public void setJobId(String jobId){
        this.jobId=jobId;
    }
    private String jobTitle;
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle=jobTitle;
    }
    private String jobDescription;

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    private String salary;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    private String jobLocation;

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    private Date postingDate;

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    private Date expiryDate;

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    private String candidateName;

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    private String candidateEmail;

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    private String candidateMobileNumber;

    public void setCandidateMobileNumber(String candidateMobileNumber) {
        this.candidateMobileNumber = candidateMobileNumber;
    }

    public String getCandidateMobileNumber() {
        return candidateMobileNumber;
    }

    private String candidateQualification;

    public String getCandidateQualification() {
        return candidateQualification;
    }

    public void setCandidateQualification(String candidateQualification) {
        this.candidateQualification = candidateQualification;
    }

    private List<Questionairre> questionairreList;

    public List<Questionairre> getQuestionairreList() {
        return questionairreList;
    }

    public void setQuestionairreList(List<Questionairre> questionairreList) {
        this.questionairreList = questionairreList;
    }
}
