package agh.tai.twitter_news_feed.dao;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InterestDao implements IInterestDao {

    private UserRepository userRepository;

    private InterestRepository interestRepository;

    @Autowired
    public InterestDao(UserRepository userRepository,
                       InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
    }

    private List<Interest> getAllUserInterests(User user) {
        return interestRepository.findAllByUserId(user.getUserId());
    }

    @Override
    public List<String> getAllUserInterestsName(User user) {
        return getAllUserInterests(user).stream()
                .map(Interest::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void addInterest(Interest interest) {
        interestRepository.save(interest);
    }

    @Override
    public void removeInterest(Interest interest) {
        interestRepository.deleteByNameAndUserId(interest.getName(), interest.getUserId());
    }

}
