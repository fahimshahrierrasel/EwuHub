package com.treebricks.ewuhub.view;

public class NoticeView
{
    private String notice_title;
    private String notice_date;
    private String notice_url;
    private String notice_number;

    public NoticeView(String noticeTitle, String noticeDate, String noticeUrl, String notice_number) {
        this.notice_title = noticeTitle;
        this.notice_date = noticeDate;
        this.notice_url = noticeUrl;
        this.notice_number = notice_number;
    }

    @Override
    public String toString() {
        return "NoticeView{" +
                "notice_title='" + notice_title + '\'' +
                ", notice_date='" + notice_date + '\'' +
                ", notice_url='" + notice_url + '\'' +
                ", notice_number='" + notice_number + '\'' +
                '}';
    }

    public NoticeView()
     {

     }

    public String getNotice_title() {
        return notice_title;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public String getNotice_url() {
        return notice_url;
    }

    public String getNotice_number() { return notice_number; }
}
