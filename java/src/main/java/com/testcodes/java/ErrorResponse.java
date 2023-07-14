package com.testcodes.java;

public class ErrorResponse {
    private String code;
    private String[] messages;

    public ErrorResponse(String code, String... messages) {
        this.code = code;
        this.messages = messages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }
}

