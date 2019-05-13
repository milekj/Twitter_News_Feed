//problems with beans injection

//package agh.tai.twitter_news_feed.service;
//
//import agh.tai.twitter_news_feed.entity.Interest;
//import agh.tai.twitter_news_feed.entity.User;
//import agh.tai.twitter_news_feed.repository.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@Rollback
//public class InterestServiceImplTest {
//
//    private User user;
//
//    private Interest interest1;
//
//    private Interest interest2;
//
//    @Autowired
//    private InterestService interestService;
//
//    @Before
//    public void setup() {
//        user = new User();
//        user.setDisplayName("test");
//        interest1 = new Interest("interestTest1", user);
//        interest2 = new Interest("interestTest2", user);
//        interestService.addInterest(interest1);
//        interestService.addInterest(interest2);
//    }
//
//    @Test
//    public void getAllUserInterestsName() {
//        List<String> allUserInterestsName = interestService.getAllUserInterestsName(user);
//        Assertions.assertThat(allUserInterestsName.get(0)).isEqualTo(interest1.getName());
//        Assertions.assertThat(allUserInterestsName.get(1)).isEqualTo(interest2.getName());
//
//    }
//
//    @Test
//    public void addInterest() {
//    }
//
//    @Test
//    public void removeInterest() {
//    }
//}