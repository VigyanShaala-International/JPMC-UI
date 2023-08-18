package com.vigyanshaala.serviceImpl.jobPortalServiceImpl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vigyanshaala.entity.jobPortalEntity.EmailDetails;
import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.JobApplication;
import com.vigyanshaala.entity.jobPortalEntity.StudentDocument;
import com.vigyanshaala.repository.jobPortalRepository.ExpiredJobsRepository;
import com.vigyanshaala.repository.jobPortalRepository.JobApplicationRepository;
import com.vigyanshaala.repository.jobPortalRepository.JobRepository;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.SystemServices;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class SystemServiceImpl implements SystemServices {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private final ExpiredJobsRepository expiredJobsRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;

    public SystemServiceImpl(ExpiredJobsRepository expiredJobsRepository, JobApplicationRepository jobApplicationRepository, JobRepository jobRepository) {
        this.expiredJobsRepository = expiredJobsRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity deleteExpiredJobs(String date) {
//        ArrayList<Job> results = null;
        Response response = new Response();
        try {
              expiredJobsRepository.softdeleteJobs(date);
//            System.out.println(results);
            log.info("Successfully soft deleted expired jobs");
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully flagged expired jobs");
        } catch (Exception e) {
            log.error("Exception occurred while soft deleting titles ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while soft deleting titles " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
// working with earlier approach
//    @Override
//    public ResponseEntity mailJobApplicationsToHr(){
////        ArrayList<Job> results = null;
//        Response response = new Response();
//        List<String> hrEmailList = new ArrayList<>();
//        List<String> jobIdList = new ArrayList<>();
//        List<JobApplication> jobApplications = new ArrayList<>();
//        Map<String, List<JobApplication>> hrToJobApplicationMap = new HashMap<>();
//        //List<JobApplication> jobApplicationsCorrespondingToHr = new ArrayList<>();
//        try {
//            List<JobApplication> jobApplicationList = jobApplicationRepository.findByisJobApplicationPostedToHr(false);
//            System.out.println(jobApplicationList);
//            log.info("Job application list yet to be posted to HR is" + jobApplicationList);
//            //log.info("Successfully soft deleted expired jobs");
//            for(JobApplication jobApplication: jobApplicationList) {
//                String hrEmail = jobApplication.getJob().getHrEmail();
//                String jobId = jobApplication.getJob().getJobId();
//                if(!hrEmailList.contains(hrEmail))
//                    hrEmailList.add(hrEmail);
//                if(!jobIdList.contains(jobId))
//                    jobIdList.add(jobId);
//
//            }
//
//            for(String s : hrEmailList) {
//                jobApplications = new ArrayList<>();
//                for (JobApplication jobApplication : jobApplicationList) {
//                    if (jobApplication.getJob().getHrEmail().equals(s))
//                        jobApplications.add(jobApplication);
//                }
//                hrToJobApplicationMap.put(s, jobApplications);
//            }
//
//            for(Map.Entry<String, List<JobApplication>> entry : hrToJobApplicationMap.entrySet()) {
//                if(entry.getValue().size() != 0)
//                    generate(entry.getValue(), entry.getKey());
//            }
//
//
//            System.out.println(hrEmailList);
//            log.info("HR Email list is " + hrEmailList);
//
////            for(String email : hrEmailList) {
////               // j
////            }
//            response.setStatusCode(HttpStatus.OK.value());
//            response.setStatusMessage("Successfully flagged expired jobs");
//        }catch(Exception e)
//        {
//            log.error("Exception occurred while soft deleting titles ",e);
//            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setStatusMessage("Exception occurred while soft deleting titles "+e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @Override
    public ResponseEntity mailJobApplicationsToHr() {
//        ArrayList<Job> results = null;
        Response response = new Response();
        List<String> hrEmailList = new ArrayList<>();
        List<String> jobIdList = new ArrayList<>();
        List<String> studentIDList = new ArrayList<>();
        List<String> studentIDs = new ArrayList<>();
        List<JobApplication> jobApplications = new ArrayList<>();
        Map<String, List<String>> jobIdtoStudentIdMap = new HashMap<>();
        List<StudentDocument> studentDocumentList = new ArrayList<>();
        //List<JobApplication> jobApplicationsCorrespondingToHr = new ArrayList<>();
        try {
            List<JobApplication> jobApplicationList = jobApplicationRepository.findByisJobApplicationPostedToHr(false);
            System.out.println(jobApplicationList);
            log.info("Job application list yet to be posted to HR is" + jobApplicationList);
            //log.info("Successfully soft deleted expired jobs");
            for (JobApplication jobApplication : jobApplicationList) {
                String hrEmail = jobApplication.getJob().getHrEmail();
                String jobId = jobApplication.getJob().getJobId();
                if (!hrEmailList.contains(hrEmail))
                    hrEmailList.add(hrEmail);
                if (!jobIdList.contains(jobId))
                    jobIdList.add(jobId);
                for (String jobId1 : jobIdList) {
                    if (jobApplication.getJob().getJobId().equals(jobId1))
                        studentIDList.add(jobApplication.getStudentId());
                }
            }


            for (String s : jobIdList) {
                studentIDs = new ArrayList<>();
                for (JobApplication jobApplication : jobApplicationList) {
                    for (String sid : studentIDList) {
                        if (jobApplication.getStudentId().equals(sid))
                            studentIDs.add(jobApplication.getStudentId());
                    }
                    jobIdtoStudentIdMap.put(s, studentIDs);

                }
                //jobIdtoStudentIdMap.put(s, studentIDs);
            }
//
//            for(Map.Entry<String, List<JobApplication>> entry : hrToJobApplicationMap.entrySet()) {
//                if(entry.getValue().size() != 0)
//                    generate(entry.getValue(), entry.getKey());
//            }
//
            for (Map.Entry<String, List<String>> entry : jobIdtoStudentIdMap.entrySet()) {
                if (entry.getValue().size() != 0) {
                    generate(entry.getKey(), entry.getValue());
                }
            }
            System.out.println(hrEmailList);
            log.info("HR Email list is " + hrEmailList);

//            for(String email : hrEmailList) {
//               // j
//            }
            response.setStatusCode(HttpStatus.OK.value());
            response.setStatusMessage("Successfully flagged expired jobs");
        } catch (Exception e) {
            log.error("Exception occurred while soft deleting titles ", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while soft deleting titles " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public void generate(String jobId, List<String> studentIds) throws DocumentException, IOException {

        try {
            File jobApplication = new File("JobApplications");
            if (!jobApplication.exists())
                jobApplication.mkdir();
            Job job = jobRepository.findByJobId(jobId);
            File directory = new File("JobApplications\\" + jobId);
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (studentIds != null) {
                for (String s : studentIds) {
                    List<JobApplication> jobApplications = jobApplicationRepository.findBystudentId(s);
                    File studentFolder = new File(directory + "\\" + s);
                    if (!studentFolder.exists()) {
                        studentFolder.mkdir();
                    }
                    if (jobApplications != null) {
                        for (JobApplication j : jobApplications) {
                            if (j.getJob().getJobId().equals(jobId)) {
                                Document document = new Document(PageSize.A4);
                                String fileName = studentFolder + "\\" + s + "-jobApplication";
                                fileName = fileName.replaceAll("[-+.^:,]", "").concat(".pdf");
                                String zipFileName = fileName.concat(".zip");
                                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
                                writer.setCloseStream(false);
                                // Opening the created document to modify it
                                document.open();

                                // Creating font
                                // Setting font style and size
                                Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                                fontTitle.setSize(20);

                                // Creating paragraph
                                Paragraph paragraph = new Paragraph("Job application", fontTitle);

                                // Aligning the paragraph in document
                                paragraph.setAlignment(Paragraph.ALIGN_CENTER);

                                // Adding the created paragraph in document
                                document.add(paragraph);

                                // Creating a table of 3 columns
                                PdfPTable table = new PdfPTable(2);

                                // Setting width of table, its columns and spacing
                                table.setWidthPercentage(100f);
                                //table.setWidths(new int[] { 3, 3, 3 });
                                table.setSpacingBefore(5);

                                // Create Table Cells for table header
                                PdfPCell cell = new PdfPCell();

                                // Setting the background color and padding
                                cell.setBackgroundColor(CMYKColor.DARK_GRAY);
                                cell.setPadding(5);

                                // Creating font
                                // Setting font style and size
                                Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                                font.setColor(CMYKColor.WHITE);

                                // Adding headings in the created table cell/ header
                                // Adding Cell to table
                                cell.setPhrase(new Phrase("Job ID", font));
                                table.addCell(cell);
                                table.addCell(String.valueOf(j.getJobApplicationId()));

                                cell.setPhrase(new Phrase("Company", font));
                                table.addCell(cell);
                                table.addCell(String.valueOf(j.getJob().getCompany().getCompanyName()));

                                cell.setPhrase(new Phrase("Job Title", font));
                                table.addCell(cell);
                                table.addCell(String.valueOf(j.getJob().getJobTitle().getJobTitle()));

                                cell.setPhrase(new Phrase("Job Location", font));
                                table.addCell(cell);
                                table.addCell(String.valueOf(j.getJob().getJobLocation().getJobLocation()));

                                cell.setPhrase(new Phrase("Job description", font));
                                table.addCell(cell);
                                table.addCell(String.valueOf(j.getJob().getJobDescription()));

                                if (j.getJob().getQuestionnaire().getQuestion1() != "" || j.getJob().getQuestionnaire().getQuestion1() != null) {
                                    cell.setPhrase(new Phrase("Question 1", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getJob().getQuestionnaire().getQuestion1()));

                                    cell.setPhrase(new Phrase("Answer 1", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getAnswer1()));
                                }

                                if (j.getJob().getQuestionnaire().getQuestion2() != "" || j.getJob().getQuestionnaire().getQuestion2() != null) {
                                    cell.setPhrase(new Phrase("Question 2", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getJob().getQuestionnaire().getQuestion2()));

                                    cell.setPhrase(new Phrase("Answer 2", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getAnswer2()));
                                }

                                if (j.getJob().getQuestionnaire().getQuestion3() != "" || j.getJob().getQuestionnaire().getQuestion3() != null) {
                                    cell.setPhrase(new Phrase("Question 3", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getJob().getQuestionnaire().getQuestion3()));

                                    cell.setPhrase(new Phrase("Answer 3", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getAnswer3()));
                                }

                                if (j.getJob().getQuestionnaire().getQuestion4() != "" || j.getJob().getQuestionnaire().getQuestion4() != null) {
                                    cell.setPhrase(new Phrase("Question 4", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getJob().getQuestionnaire().getQuestion4()));

                                    cell.setPhrase(new Phrase("Answer 4", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getAnswer4()));
                                }

                                if (j.getJob().getQuestionnaire().getQuestion5() != "" || j.getJob().getQuestionnaire().getQuestion5() != null) {
                                    cell.setPhrase(new Phrase("Question 5", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getJob().getQuestionnaire().getQuestion5()));

                                    cell.setPhrase(new Phrase("Answer 5", font));
                                    table.addCell(cell);
                                    table.addCell(String.valueOf(j.getAnswer5()));
                                }
                                j.setIsJobApplicationPostedToHr(Boolean.TRUE);
                                jobApplicationRepository.save(j);
                                table.addCell(new Phrase());
                                table.addCell(new Phrase());


                                // Adding the created table to document
                                document.add(table);

                                // Closing the document
                                document.close();

                                List<StudentDocument> studentDocumentList = j.getStudentDocumentList();
                                for (StudentDocument studentDocument : studentDocumentList) {
                                    File fileStructure = new File(studentFolder + "\\" + studentDocument.getDocumentType() + ".pdf");
                                    if (!fileStructure.exists()) {
                                        fileStructure.createNewFile();
                                        //Blob blob = studentDocument.getBlobData();

                                        //InputStream is = new ByteArrayInputStream(studentDocument.getBlobData());
                                        //String mimeType = URLConnection.guessContentTypeFromStream(is);

                                        //InputStream in = blob.getBinaryStream();
                                        OutputStream out = new FileOutputStream(fileStructure);
//                                    byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
//                                    int len = 0;
//
//                                    while ((len = is.read(buff)) != -1) {
//                                        out.write(buff, 0, len);
//                                    }
                                        out.write(studentDocument.getBlobData());
                                        out.close();
                                    }
                                }

                            }
                        }

                    }
                    // Creating the Object of Document

                    FileOutputStream fos = new FileOutputStream(directory + "\\" + jobId);
                    ZipOutputStream zos = new ZipOutputStream(fos);

                    //zos.putNextEntry(new ZipEntry(jobId)); commenting now
                    ZipUtil.pack(new File("JobApplications\\" + jobId), new File("JobApplications\\" + jobId + ".zip"));

                    //String filePath = "C:\\Users\\harin\\IdeaProjects\\vigyanshaala-server-new"
                    byte[] bytes = Files.readAllBytes(Paths.get(jobId));
                    zos.write(bytes, 0, bytes.length);
                    //zos.closeEntry();
                    EmailDetails details = new EmailDetails();
                    details.setSubject("Details of job applications");
                    details.setRecipient(job.getHrEmail());
                    details.setMsgBody("Hi, PFA responses to the list of job applications you have posted. Best regards, Team VigyanShaala ");
                    details.setAttachment("JobApplications\\" + jobId + ".zip");
                    zos.close();
                    //sendMailWithAttachment(details);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.format("The file does not exist");
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
        // Getting instance of PdfWriter
    }

    public String sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be sent
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

}
