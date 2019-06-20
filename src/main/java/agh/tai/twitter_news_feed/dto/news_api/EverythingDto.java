package agh.tai.twitter_news_feed.dto.news_api;

import java.io.Serializable;
import java.util.List;
//in my opinion name of class does not tell anything
public class EverythingDto implements Serializable {
    private List<NewsDto> articles;

    public EverythingDto() {
    }

    public List<NewsDto> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsDto> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "EverythingDto{" +
                "articles=" + articles +
                '}';
    }
}
