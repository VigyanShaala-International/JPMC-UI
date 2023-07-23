package com.vigyanshaala.controller.jobPortalController;

import com.vigyanshaala.entity.jobPortalEntity.Admin;
import com.vigyanshaala.entity.jobPortalEntity.Job;
import com.vigyanshaala.entity.jobPortalEntity.Questionnaire;
import com.vigyanshaala.response.Response;
import com.vigyanshaala.service.jobPortalService.AdminServices;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


/**The following Admin Controller contains all the get and post calls for the Admin tasks
 POST : saving the company details, job titles, job locations, job postings
 GET : get company details,job titles, job locations
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job/admin")
@Slf4j
public class AdminController {

    @Autowired
    AdminServices adminServices;

    private static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, "AES");
        return key;
    }

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String rsaPrivateKey = "-----BEGIN PRIVATE KEY-----" +
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDK7c0HtOvefMRM" +
                "s1tkdiJm+A16Df85lQlmXjQvMHNgY4P/znvl4kRON9DdBdo3K81OG7pR/0H9XvdB" +
                "TEojj/6vCVuMDeeIiBrgx0OJjhv0r8oUD4d52+1kXXITaniyZcbJ08s4sF7fUSCu" +
                "IZOhvvwQTd/tIwXGU1qqfg+bsomQ6h2czPSKXAux54vUiRO2IWf/Y6twyk8cy1PH" +
                "IOfelCVUJ4kzmP+CsOH7Rh3JMwZ0Mc4GAzndWpKwNXKjVM20/bKE9FgIiIjzmEQd" +
                "VpSdUz2MbAKM1kskdaHXQyuaHoHfPwESYuEwBld4vh9AGMF3jYMu8ggnAzVRIoWG" +
                "Mr5eCE2tAgMBAAECggEBAKBPXiKRdahMzlJ9elyRyrmnihX7Cr41k7hwAS+qSetC" +
                "kpu6RjykFCvqgjCpF+tvyf/DfdybF0mPBStrlkIj1iH29YBd16QPSZR7NkprnoAd" +
                "gzl3zyGgcRhRjfXyrajZKEJ281s0Ua5/i56kXdlwY/aJXrYabcxwOvbnIXNxhqWY" +
                "NSejZn75fcacSyvaueRO6NqxmCTBG2IO4FDc/xGzsyFKIOVYS+B4o/ktUOlU3Kbf" +
                "vwtz7U5GAh9mpFF+Dkr77Kv3i2aQUonja6is7X3JlA93dPu4JDWK8jrhgdZqY9p9" +
                "Q8odbKYUaBV8Z8CnNgz2zaNQinshzwOeGfFlsd6H7SECgYEA7ScsDCL7omoXj4lV" +
                "Mt9RkWp6wQ8WDu5M+OCDrcM1/lfyta2wf7+9hv7iDb+FwQnWO3W7eFngYUTwSw5x" +
                "YP2uvOL5qbe7YntKI4Q9gHgUd4XdRJJSIdcoY9/d1pavkYwOGk7KsUrmSeoJJ2Jg" +
                "54ypVzZlVRkcHjuwiiXKvHwj2+UCgYEA2w5YvWSujExREmue0BOXtypOPgxuolZY" +
                "pS5LnuAr4rvrZakE8I4sdYjh0yLZ6qXJHzVlxW3DhTqhcrhTLhd54YDogy2IT2ff" +
                "0GzAV0kX+nz+mRhw0/u+Yw6h0QuzH9Q04Wg3T/u/K9+rG335j/RU1Tnh7nxetfGb" +
                "EwJ1oOqcXikCgYEAqBAWmxM/mL3urH36ru6r842uKJr0WuhuDAGvz7iDzxesnSvV" +
                "5PKQ8dY3hN6xfzflZoXssUGgTc55K/e0SbP93UZNAAWA+i29QKY6n4x5lKp9QFch" +
                "dXHw4baIk8Z97Xt/kw07f6FAyijdC9ggLHf2miOmdEQzNQm/9mcJ4cFn+DECgYEA" +
                "gvOepQntNr3gsUxY0jcEOWE3COzRroZD0+tLFZ0ZXx/L5ygVZeD4PwMnTNrGvvmA" +
                "tAFt54pomdqk7Tm3sBQkrmQrm0+67w0/xQ9eJE/z37CdWtQ7jt4twHXc0mVWHa70" +
                "NdPhTRVIAWhil7rFWANOO3Gw2KrMy6O1erW7sAjQlZECgYBmjXWzgasT7JcHrP72" +
                "fqrEx4cg/jQFNlqODNb515tfXSBBoAFiaxWJK3Uh/60/I6cFL/Qoner4trNDWSNo" +
                "YENBqXLZnWGfIo0vAIgniJ6OD67+1hEQtbenhSfeE8Hou2BnFOTajUxmYgGm3+hx" +
                "h8TPOvfHATdiwIm7Qu76gHhpzQ==" +
                "-----END PRIVATE KEY-----";

        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }


    @GetMapping(value="/getEntitlement/{encryptedEmail}", produces="application/json")
    ResponseEntity verifyAdmin( @PathVariable("encryptedEmail") String email) {

        log.info(email);
        ResponseEntity responseEntity;
        Response response=new Response();


        try{
//            String secret = "randomSTRING4564";
//            Base64.Decoder decoder = Base64.getDecoder();
//            byte[] encrypted1 = decoder.decode(email);
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            SecretKeySpec keyspec = new SecretKeySpec(secret.getBytes(), "AES");
//            IvParameterSpec ivspec = new IvParameterSpec(secret.getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original);
//            log.info(originalString.trim());


            responseEntity = adminServices.verifyAdmin(email);
            log.info("responseEntity"+responseEntity);
            response= (Response) responseEntity.getBody();
            log.info("response"+response.getData());
            String role="";

           if(Objects.nonNull(response.getData())) {
               role="ADMIN";
           }
           else
           {
               role="STUDENT";
           }
               Instant now = Instant.now();
               String jwtToken = Jwts.builder()
                       .claim("role", role)
                       .claim("email", email)
                       .setSubject((String) response.getData())
                       .setId(UUID.randomUUID().toString())
                       .setIssuedAt(Date.from(now))
                       .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                       .compact();


            log.info(jwtToken);
            //pass in bearrer token in authorizatio---oauth2.0(openid)

//            String role=jwtToken;
//            String secretKey = "75ac5e7677e7218a7f059f0bda073a6b";
//            Key key = generateKey(secretKey);
//            Cipher c = Cipher.getInstance("AES");
//            c.init(Cipher.ENCRYPT_MODE, key);
//            byte[] encVal = c.doFinal(role.getBytes());
//            String encryptedValue = Base64.getEncoder().encodeToString(encVal);
//            log.info(encryptedValue);



            return ResponseEntity.status(HttpStatus.OK).body(jwtToken);



        }catch(Exception e){
            log.error("Exception occurred while verifying admin ",e);
            response.setStatusCode(500);
            response.setStatusMessage("Exception in verifying admin");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }

    @PostMapping(value="/admin", produces="application/json")
    Response addAdmin( Admin admin) {
        Response response=new Response();

        try{

            response = adminServices.addAdmin(admin);
        }catch(Exception e){
            log.error("Exception occurred while verifying admin ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding admin"+e);
            return response;
        }
        return response;

    }

    @GetMapping(value="/admin/all", produces="application/json")
    ResponseEntity<Response> getAdminList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getAdminList();
        }catch(Exception e){
            log.error("Exception occurred while getting admin list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting admin list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add company details in the Company table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/company",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody String company){
        Response response=new Response();
        try{
            log.info("The company name is : {}", company);
            response= adminServices.addCompany(company);
        }catch(Exception e){
            log.error("Exception occurred while adding company name ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding company name "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get company list from the Company table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all companies.")
    @GetMapping(value="/company/all", produces="application/json")
    ResponseEntity<Response> getCompanyList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getCompanyList();
        }catch(Exception e){
            log.error("Exception occurred while getting company name list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting company name list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job title in the JobTitle table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/title",consumes="application/json", produces="application/json")
    Response addJobTitle(@RequestBody String jobTitle){
        Response response=new Response();
        try{
            log.info("The company name list is : {}",jobTitle);
            response= adminServices.addJobTitle(jobTitle);
        }catch(Exception e){
            log.error("Exception occurred while adding job title "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job title "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job title list from the JobTitle table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job titles.")
    @GetMapping(value="/title/all", produces="application/json")
    ResponseEntity<Response> getJobTitleList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobTitleList();
        }catch(Exception e){
            log.error("Exception occurred while getting job title list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while getting job title list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }



    @ApiOperation(value = "Add job location in the JobLocation table", notes = "Returns a response with status code 200 for successful addition in the table")
    @PostMapping(value="/location",consumes="application/json", produces="application/json")
    Response addJobLocationList(@RequestBody String jobLocation){
        Response response=new Response();
        try{
            log.info("The company name list is : {}",jobLocation);
            response= adminServices.addJobLocation(jobLocation);
        }catch(Exception e){
            log.error("Exception occurred while adding job location ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occured while adding job location "+e);
        }
        return response;
    }

    @ApiOperation(value = "Get job location list from the JobLocation table", notes = "Returns a response entity with status code 200 and response in the body. The response data contains the list of all job locations.")
    @GetMapping(value="/location/all", produces="application/json")
    ResponseEntity<Response> getJobLocationList(){
        ResponseEntity responseEntity;
        Response response=new Response();
        try{
            responseEntity= adminServices.getJobLocationList();
        }catch(Exception e){
            log.error("Exception occurred while getting job location list ",e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while getting job location list"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add job in the job table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/job",consumes="application/json", produces="application/json")
    Response addCompany(@RequestBody Job job){
        Response response=new Response();
        try{
            log.info("The Job detail is : "+ job.toString());
            response= adminServices.createJob(job);
        }catch(Exception e){
            log.error("Exception occurred while adding job  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  "+e);
        }
        return response;
    }

    @ApiOperation(value = "Add questions for a job posting in the questionnaire table", notes = "Returns a response with status code 200 for successful addition in the table.")
    @PostMapping(value="/questionnaire",consumes="application/json", produces="application/json")
    Response createQuestionnaire(@RequestBody Questionnaire questionnaire){
        Response response=new Response();
        try{
            log.info("The questionnaire received is : "+ questionnaire.toString());
            response= adminServices.createQuestionnaire(questionnaire);
        }catch(Exception e){
            log.error("Exception occurred while adding job  "+e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatusMessage("Exception occurred while adding job  "+e);
        }
        return response;
    }

}