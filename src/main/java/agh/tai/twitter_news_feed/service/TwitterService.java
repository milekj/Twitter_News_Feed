package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TwitterService implements ITwitterService {

    private static final int FAVOURITE_INTERESTS_NUMBER = 10;

    private Twitter getTwitterApiForAuthenticatedUser(SocialUserDetailsImpl userDetails) {
        return userDetails.getConnection().getApi();
    }

    @Override
    public Map<String, Long> getFavouriteInterests(SocialUserDetailsImpl userDetails) {
        Twitter twitter = getTwitterApiForAuthenticatedUser(userDetails);
        TimelineOperations timelineOperations = twitter.timelineOperations();
        List<Tweet> homeTimeline = timelineOperations.getHomeTimeline(100);
        return homeTimeline.stream()
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

}
