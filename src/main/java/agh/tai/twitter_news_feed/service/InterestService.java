package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.dto.TweetDto;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;

import java.util.List;
import java.util.Map;

public interface InterestService {

    Map<String, Long> getFavouriteInterests(SocialUserDetailsImpl userDetails);

    List<String> getAllUserInterestsName(User user);

    List<TweetDto> getLatestTweetsWithHashTag(SocialUserDetailsImpl userDetails, String hashTag);

    void addInterest(Interest interest);

    void removeInterest(Interest interest);

    void excludeInterest(Interest interest);

    List<Interest> findAllUserInterests(User user);

    //todo
    List<Interest> findAllUserExcludedInterests(User user);

}
