package agh.tai.twitter_news_feed.entity;

import java.time.LocalDate;

public class NewsBuilder {

    private String url;
    private Interest interest;
    private String urlToImage;
    private String description;
    private String title;
    private LocalDate publishedAt;

    public News build() {
        return new News(url, interest, urlToImage, description, title, publishedAt);
    }

    public NewsBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public NewsBuilder setInterest(Interest interest) {
        this.interest = interest;
        return this;
    }

    public NewsBuilder setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public NewsBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public NewsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NewsBuilder setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

}
