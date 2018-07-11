package com.treebricks.ewuhub.model;

import java.io.Serializable;

public class NoticeView implements Serializable
{
    public String notice_title;
    public String notice_date;
    public String notice_url;
    public String notice_number;

    public NoticeView() {
    }

    public NoticeView(String notice_title, String notice_date, String notice_url, String notice_number) {
        this.notice_title = notice_title;
        this.notice_date = notice_date;
        this.notice_url = notice_url;
        this.notice_number = notice_number;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public String getNotice_url() {
        return notice_url;
    }

    public void setNotice_url(String notice_url) {
        this.notice_url = notice_url;
    }

    public String getNotice_number() {
        return notice_number;
    }

    public void setNotice_number(String notice_number) {
        this.notice_number = notice_number;
    }
}
