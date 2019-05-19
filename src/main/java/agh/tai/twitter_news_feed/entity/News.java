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

    private LocalDate creationDate;

    public News() {
    }

    private News(String url, Interest interest, String urlToImage, String description) {
        this.url = url;
        this.interest = interest;
        this.urlToImage = urlToImage;
        this.description = description;
        this.creationDate = LocalDate.now();
        System.out.println(creationDate);
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

    public static class Builder {
        private String url;
        private Interest interest;
        private String urlToImage;
        private String description;

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

        public News build() {
            return new News(url, interest, urlToImage, description);
        }
    }

}
