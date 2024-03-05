package edu.brown.cs.securemusicstorage.Response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;
    private int code;
    private String message;

    public ApiResponse() {
        this.code = 200;
        this.message = "success";
    }

    public ApiResponse(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public void fail(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
