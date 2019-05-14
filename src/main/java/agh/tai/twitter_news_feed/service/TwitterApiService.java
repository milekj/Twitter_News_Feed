package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public interface TwitterApiService {

    List<Tweet> getTimeLineOperations(SocialUserDetailsImpl userDetails);

    SearchOperations getSearchOperations(SocialUserDetailsImpl userDetails);

}