package agh.tai.twitter_news_feed.dto;

import java.util.Date;

public class TweetDto {

    private String fromUser;

    private String text;

    private Date createdAt;

    private String profileImageUrl;

    public TweetDto(String fromUser, String text, Date createdAt, String profileImageUrl) {
        this.fromUser = fromUser;
        this.text = text;
        this.createdAt = createdAt;
        this.profileImageUrl = profileImageUrl;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}
