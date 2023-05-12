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

    private String statusMessage;

    private T data;

    public Response(int statusCode, String statusMessage, T data)
    {
        this.statusCode=statusCode;

        this.statusMessage=statusMessage;

        this.data=data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }





    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }




    public T getData(){return data;}
    public void setData(T data){
        this.data=data;
    }



}
