package com.treebricks.ewuhub.model;

/**
 * Created by fahim on 11/24/16.
 */

public class NewsFeedModel
{
    private String feed_title;
    private String feed_number;
    private String feed_date;
    private String feed_data;

    public NewsFeedModel() {
    }

    public NewsFeedModel(String feed_title, String feed_number, String feed_date, String feed_data) {
        this.feed_title = feed_title;
        this.feed_number = feed_number;
        this.feed_date = feed_date;
        this.feed_data = feed_data;
    }

    public String getFeed_title() {
        return feed_title;
    }

    public void setFeed_title(String feed_title) {
        this.feed_title = feed_title;
    }

    public String getFeed_number() {
        return feed_number;
    }

    public void setFeed_number(String feed_number) {
        this.feed_number = feed_number;
    }

    public String getFeed_date() {
        return feed_date;
    }

    public void setFeed_date(String feed_date) {
        this.feed_date = feed_date;
    }

    public String getFeed_data() {
        return feed_data;
    }

    public void setFeed_data(String feed_data) {
        this.feed_data = feed_data;
    }
}
