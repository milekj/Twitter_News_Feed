package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface NewsService {
    @Transactional
    void updateNews(User user);
}
