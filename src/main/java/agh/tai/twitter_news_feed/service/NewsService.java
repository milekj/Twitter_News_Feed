package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.News;
import agh.tai.twitter_news_feed.entity.User;

import java.util.List;
import java.util.Map;

public interface NewsService {
    void updateNews(User user, int newsPerInterestNumber);

    Map<Interest, List<News>> getNewsPerInterest(User user, int newsNumber);

    void updateNewsIfNecessary(User user, int newsNumber);
}
