package agh.tai.twitter_news_feed.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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


    public News() {
    }

    private News(String url, Interest interest, String urlToImage, String description, String title) {
        this.url = url;
        this.interest = interest;
        this.urlToImage = urlToImage;
        this.description = description;
        this.title = title;
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

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder {
        private String url;
        private Interest interest;
        private String urlToImage;
        private String description;
        private String title;

        public News build() {
            return new News(url, interest, urlToImage, description, title);
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setInterest(Interest interest) {
            this.interest = interest;
            return this;
        }

        public Builder setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

    }

}
