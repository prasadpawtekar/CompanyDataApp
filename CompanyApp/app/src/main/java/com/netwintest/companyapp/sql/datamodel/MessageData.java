package com.netwintest.companyapp.sql.datamodel;

/**
 * Created by Admin on 7/24/2018.
 */

public class MessageData {
    String message;

    public MessageData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "message='" + message + '\'' +
                '}';
    }
}
