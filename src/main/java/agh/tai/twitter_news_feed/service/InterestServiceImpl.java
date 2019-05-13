package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.repository.InterestRepository;
import agh.tai.twitter_news_feed.dto.TweetDto;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InterestServiceImpl implements InterestService {

    private static final int FAVOURITE_INTERESTS_NUMBER = 10;

    private static final int TWEETS_WITH_HASH_TAG = 5;

    private static final int FILTERED_TWEETS_NUMBER = 100;

    private InterestRepository interestRepository;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    private Twitter getTwitterApiForAuthenticatedUser(SocialUserDetailsImpl userDetails) {
        return userDetails.getConnection().getApi();
    }

    @Override
    public Map<String, Long> getFavouriteInterests(SocialUserDetailsImpl userDetails) {
        Twitter twitter = getTwitterApiForAuthenticatedUser(userDetails);
        TimelineOperations timelineOperations = twitter.timelineOperations();
//        List<Tweet> homeTimeline = timelineOperations.getHomeTimeline(FILTERED_TWEETS_NUMBER);
        List<Tweet> favorites = timelineOperations.getFavorites(FILTERED_TWEETS_NUMBER);
//        List<Tweet> mentions = timelineOperations.getMentions(FILTERED_TWEETS_NUMBER);
//        List<Tweet> userTimeline = timelineOperations.getUserTimeline(FILTERED_TWEETS_NUMBER);
//        return Stream.of(homeTimeline, favorites, mentions, userTimeline)
        //todo
        return Stream.of(favorites)
                .flatMap(Collection::stream)
                .filter(this::shouldTweetBeFiltered)
                .map(Tweet::getEntities)
                .map(Entities::getHashTags)
                .flatMap(Collection::stream)
                .map(HashTagEntity::getText)
                .filter(interestName -> canBeANewInterest(interestName, userDetails.getUser()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .limit(FAVOURITE_INTERESTS_NUMBER)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private boolean shouldTweetBeFiltered(Tweet tweet) {
        return tweet.isFavorited() || tweet.isRetweeted();
    }

    private boolean canBeANewInterest(String interestName, User user) {
        return interestRepository.findAllByUser(user).stream()
                .map(Interest::getName)
                .noneMatch(name -> name.equalsIgnoreCase(interestName));
    }

    @Override
    public List<String> getAllUserInterestsName(User user) {
        return interestRepository.findAllByUser(user).stream()
                .filter(Interest::isIncluded)
                .map(Interest::getName)
                .collect(Collectors.toList());
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
        interestRepository.save(interest);
    }

    @Override
    public void removeInterest(Interest interest) {
        Interest foundInterest = interestRepository.findByNameAndUser(interest.getName(), interest.getUser());
        interestRepository.delete(foundInterest);
    }

    @Override
    public void excludeInterest(Interest interest) {
        Interest foundInterest = interestRepository.findByNameAndUser(interest.getName(), interest.getUser());
        if (Objects.isNull(foundInterest)) {
            foundInterest = interest;
        }
        foundInterest.setExcluded(true);
        interestRepository.save(foundInterest);
    }

    @Override
    public List<Interest> findAllUserExcludedInterests(User user) {
        return interestRepository.findAllByUser(user).stream()
                .filter(Interest::isExcluded)
                .collect(Collectors.toList());
    }
}
