package agh.tai.twitter_news_feed.dao;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;

import java.util.List;

public interface IInterestDao {

    List<String> getAllUserInterestsName(User user);

    void addInterest(Interest interest);

    void removeInterest(Interest interest);

}
