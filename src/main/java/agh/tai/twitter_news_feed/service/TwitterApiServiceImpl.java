package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static agh.tai.twitter_news_feed.constants.Constants.*;

@Service
public class TwitterApiServiceImpl implements TwitterApiService {

    @Override
    @Cacheable(CACHE_NAME)
    public List<Tweet> getTimeLineOperations(SocialUserDetailsImpl userDetails) {
        Twitter twitter = getTwitterApiForAuthenticatedUser(userDetails);
        TimelineOperations timelineOperations = twitter.timelineOperations();
        List<Tweet> homeTimeline = timelineOperations.getHomeTimeline(FILTERED_TWEETS_NUMBER);
        List<Tweet> favorites = timelineOperations.getFavorites(FILTERED_TWEETS_NUMBER);
        List<Tweet> mentions = timelineOperations.getMentions(FILTERED_TWEETS_NUMBER);
        List<Tweet> userTimeline = timelineOperations.getUserTimeline(FILTERED_TWEETS_NUMBER);
        homeTimeline.removeAll(userTimeline);

        return Stream.of(homeTimeline, favorites, mentions, userTimeline)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public SearchOperations getSearchOperations(SocialUserDetailsImpl userDetails) {
        return getTwitterApiForAuthenticatedUser(userDetails).searchOperations();
    }

    private Twitter getTwitterApiForAuthenticatedUser(SocialUserDetailsImpl userDetails) {
        return userDetails.getConnection().getApi();
    }

}