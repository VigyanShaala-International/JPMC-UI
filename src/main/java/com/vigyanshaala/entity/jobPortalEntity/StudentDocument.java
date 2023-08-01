package com.vigyanshaala.entity.jobPortalEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_document")
public class StudentDocument {
    public String getStudentDocumentId() {
        return studentDocumentId;
    }

    public void setStudentDocumentId(String studentDocumentId) {
        this.studentDocumentId = studentDocumentId;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }

    public StudentDocument(String studentDocumentId, JobApplication jobApplication, byte[] blobData) {
        this.studentDocumentId = studentDocumentId;
        this.jobApplication = jobApplication;
        this.documentType = documentType;
        this.blobData = blobData;
    }

    public StudentDocument() {
    }

    @Id
    private String studentDocumentId;
    @ManyToOne(cascade = CascadeType.MERGE)
    private JobApplication jobApplication;
    @OneToOne(cascade = CascadeType.MERGE)
    private DocumentType documentType;
    @Lob
    private byte[] blobData;
}
