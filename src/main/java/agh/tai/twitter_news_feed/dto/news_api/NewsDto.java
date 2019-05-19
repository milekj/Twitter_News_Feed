package agh.tai.twitter_news_feed.dto.news_api;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.News;

import java.io.Serializable;

public class NewsDto implements Serializable {
    private String url;
    private String urlToImage;
    private String description;

    public NewsDto() {
    }

    public News toNews() {
        return News.builder()
                .setUrl(url)
                .setUrlToImage(urlToImage)
                .setDescription(description)
                    .build();

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
