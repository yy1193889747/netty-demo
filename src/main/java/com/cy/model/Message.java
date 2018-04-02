package com.cy.model;

import com.cy.enums.MsgType;

import java.io.Serializable;

/**
 * @author ocly
 * @date 2018/4/2 11:38
 */
public class Message implements Serializable{

    private static final long serialVersionUID = -8564552139427491383L;

    private String clientId;//发送者客户端ID

    private MsgType type;//消息类型

    private String data;//数据

    private String targetId;//目标客户端ID


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
