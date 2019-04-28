package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;

import java.util.Map;

public interface ITwitterService {

    Map<String, Long> getFavouriteInterests(SocialUserDetailsImpl userDetails);

}
