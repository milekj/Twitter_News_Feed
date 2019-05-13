package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.dao.IInterestDao;
import agh.tai.twitter_news_feed.dto.TweetDto;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InterestService implements IInterestService {

    private static final int FAVOURITE_INTERESTS_NUMBER = 10;

    private static final int TWEETS_WITH_HASH_TAG = 5;

    private static final int FILTERED_TWEETS_NUMBER = 200;

    private IInterestDao interestDao;

    @Autowired
    public InterestService(IInterestDao interestDao) {
        this.interestDao = interestDao;
    }

    private Twitter getTwitterApiForAuthenticatedUser(SocialUserDetailsImpl userDetails) {
        return userDetails.getConnection().getApi();
    }

    @Override
    public Map<String, Long> getFavouriteInterests(SocialUserDetailsImpl userDetails) {
        Twitter twitter = getTwitterApiForAuthenticatedUser(userDetails);
        TimelineOperations timelineOperations = twitter.timelineOperations();
        List<Tweet> homeTimeline = timelineOperations.getUserTimeline(FILTERED_TWEETS_NUMBER);
        return homeTimeline.stream()
                //.filter(this::shouldTweetBeFiltered)
                .map(Tweet::getEntities)
                .map(Entities::getHashTags)
                .flatMap(Collection::stream)
                .map(HashTagEntity::getText)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .limit(FAVOURITE_INTERESTS_NUMBER)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private boolean shouldTweetBeFiltered(Tweet tweet) {
        return tweet.isFavorited() || tweet.isRetweeted();
    }

    @Override
    public List<String> getAllUserInterestsName(User user) {
        return interestDao.getAllUserInterestsName(user);
    }

    @Override
    public List<TweetDto> getLatestTweetsWithHashTag(SocialUserDetailsImpl userDetails, String hashTag) {
        Twitter twitter = getTwitterApiForAuthenticatedUser(userDetails);
        SearchOperations searchOperations = twitter.searchOperations();
        SearchResults search = searchOperations.search("#" + hashTag);
        return search.getTweets().stream()
                .limit(TWEETS_WITH_HASH_TAG)
                .map(tweet -> new TweetDto(tweet.getFromUser(), tweet.getText(), tweet.getCreatedAt(),
                        tweet.getProfileImageUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public void addInterest(Interest interest) {
        interestDao.addInterest(interest);
    }

    @Override
    public void removeInterest(Interest interest) {
        interestDao.removeInterest(interest);
    }
}
