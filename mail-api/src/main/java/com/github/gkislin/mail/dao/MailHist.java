package com.github.gkislin.mail.dao;

import java.util.Date;

/**
* User: gkislin
* Date: 26.09.13
*/
public final class MailHist {
    private int id;
    private String listTo;
    private String listCc;
    private String subject;
    private String body;
    private String state;
    private Date date;
    private String attachList;

    public void setId(int id) {
        this.id = id;
    }

    public void setListTo(String listTo) {
        this.listTo = listTo;
    }

    public void setListCc(String listCc) {
        this.listCc = listCc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getListTo() {
        return listTo;
    }

    public String getListCc() {
        return listCc;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    public String getAttachList() {
        return attachList;
    }

    public void setAttachList(String attachList) {
        this.attachList = attachList;
    }

    @Override
    public String toString() {
        return "\nMailHist{" +
                "id=" + id +
                ", listTo='" + listTo + '\'' +
                ", listCc='" + listCc + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", state='" + state + '\'' +
                ", date=" + date +
                ", attachList=" + attachList +
                '}';
    }
}
