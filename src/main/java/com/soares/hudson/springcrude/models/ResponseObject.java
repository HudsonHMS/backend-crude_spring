package com.soares.hudson.springcrude.models;

public class ResponseObject<T> {
    private T responseData;
    private String message;
    private int statusCode;

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseObject(T responseData, String message, int statusCode) {
        this.responseData = responseData;
        this.message = message;
        this.statusCode = statusCode;
    }

}
