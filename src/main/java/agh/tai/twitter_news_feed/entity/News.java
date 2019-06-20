package agh.tai.twitter_news_feed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class News {

    @Id
    @Column(length = 1024)
    private String url;

    @ManyToOne
    private Interest interest;

    @Column(length = 1024)
    private String urlToImage;

    @Column(length = 1024)
    private String description;

    private String title;

    private LocalDate publishedAt;


    public News() {
    }

    public News(String url, Interest interest, String urlToImage, String description, String title, LocalDate publishedAt) {
        this.url = url;
        this.interest = interest;
        this.urlToImage = urlToImage;
        this.description = description;
        this.title = title;
        this.publishedAt = publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static NewsBuilder builder() {
        return new NewsBuilder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

}
