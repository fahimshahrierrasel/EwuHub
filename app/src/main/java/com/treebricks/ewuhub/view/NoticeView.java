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

    public String getNoticeDate() {
        return noticeDate;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }
}
