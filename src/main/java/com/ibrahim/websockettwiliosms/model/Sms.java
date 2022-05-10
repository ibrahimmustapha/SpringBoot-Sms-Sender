package com.ibrahim.websockettwiliosms.model;

public class Sms {
    private String receiver;
    private String message;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
