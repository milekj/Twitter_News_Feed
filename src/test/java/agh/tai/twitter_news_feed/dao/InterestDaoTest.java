package agh.tai.twitter_news_feed.dao;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
public class InterestDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InterestRepository interestRepository;

    private User user;
    private Interest interest;

    @Before
    public void setup() {
        user = new User();
        user.setDisplayName("test");
        interest = new Interest("interestTest", user.getUserId());
        userRepository.save(user);
    }

    @Test
    public void getAllUserInterestsName() {
        Optional<User> byUserId = userRepository.findByUserId(user.getUserId());
        Assertions.assertThat(byUserId).isNotEmpty();
        Assertions.assertThat(byUserId.get()).isEqualTo(user);

        List<Interest> allByUser = interestRepository.findAllByUserId(user.getUserId());
        Assertions.assertThat(allByUser).isNotEmpty().containsOnly(interest);
    }

    @Test
    public void addInterest() {
    }

    @Test
    public void removeInterest() {
    }
}