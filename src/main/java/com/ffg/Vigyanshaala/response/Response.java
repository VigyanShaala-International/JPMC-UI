package com.ffg.Vigyanshaala.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Response<T> {
    private int statusCode;
    private int errorCode;
    private String statusMessage;
    private String errorMessage;
    private T data;

    public Response(int statusCode, int errorCode, String statusMessage, String errorMessage,T data)
    {
        this.statusCode=statusCode;
        this.errorCode=errorCode;
        this.statusMessage=statusMessage;
        this.errorMessage=errorMessage;
        this.data=data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }



    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData(){return data;}
    public void setData(T data){
        this.data=data;
    }



}
