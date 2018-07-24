package com.cy.metaq;

import java.io.Serializable;

/**
 * @author congyang.guo
 */
public class Message implements Serializable {

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                '}';
    }
}
