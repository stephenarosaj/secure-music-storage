package edu.brown.cs.securemusicstorage.Response;

public enum ResponseCode {

    SUCCESS(200, "success"),
    BAD_REQUEST(400, "Bad Request");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
