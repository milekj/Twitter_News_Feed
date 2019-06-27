package agh.tai.twitter_news_feed.repository;

import agh.tai.twitter_news_feed.entity.DurationWithUnit;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class InterestRepositoryTest {

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    TestEntityManager entityManager;

    private User user;

    private Interest interest;

    @Before
    public void setup() {
        user = createUser();
        interest = createInterest(user, "Interest1");
    }

    @Test
    public void findAllByUser() {
        List<Interest> allByUser = interestRepository.findAllByUserOrderByName(user);
        Assertions.assertThat(allByUser).isNotEmpty();
    }

    @Test
    public void findByNameAndUser() {
        Interest interestJpa = interestRepository.findByNameAndUser("Interest1", user);
        Assertions.assertThat(interestJpa).isEqualTo(interest);
    }

    @Test
    public void findByIntIdAndUserUserId() {
        Interest interest1 = interestRepository.findByNameAndUser("Interest1", user);
        int intId = interest1.getIntId();
        Optional<Interest> testUserId = interestRepository.findByIntIdAndUserUserId(intId, "testUserId");
        Assertions.assertThat(testUserId.get()).isNotNull();
    }

    private User createUser() {
        User user = new User();
        user.setUserId("testUserId");
        user.setProviderId("testProviderId");
        user.setProviderUserId("testProviderUserId");
        user.setAccessToken("testAccessToken");
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }

    private Interest createInterest(User user, String name) {
        Interest interest = new Interest(name, user, null);
        interest.setUpdateFrequency(new DurationWithUnit());
        entityManager.persist(interest);
        entityManager.flush();
        return interest;
    }

}