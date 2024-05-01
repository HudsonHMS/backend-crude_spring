package com.soares.hudson.springcrude.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseObject<T> {
    private T responseData;
    private String message;
    private int statusCode;
    @JsonInclude(value = Include.NON_DEFAULT)
    private int totalPages;
    @JsonInclude(value = Include.NON_DEFAULT)
    private int totalRegistros;

    public T getResponseData() {
        return responseData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
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

    public ResponseObject(T responseData, String message, int statusCode, int totalPages, int totalRegistros) {
        this.responseData = responseData;
        this.message = message;
        this.statusCode = statusCode;
        this.totalRegistros = totalRegistros;
        this.totalPages     = totalPages;
    }

}
