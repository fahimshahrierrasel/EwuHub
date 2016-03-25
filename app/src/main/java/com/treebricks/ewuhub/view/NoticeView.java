package com.treebricks.ewuhub.view;


public class NoticeView
{
    private String noticeTitle;
    private String noticeDate;
    private String noticeUrl;

    public NoticeView(String noticeTitle, String noticeDate, String noticeUrl) {
        this.noticeTitle = noticeTitle;
        this.noticeDate = noticeDate;
        this.noticeUrl = noticeUrl;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
}
