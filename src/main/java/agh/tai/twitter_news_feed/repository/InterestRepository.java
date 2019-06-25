package agh.tai.twitter_news_feed.repository;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    List<Interest> findAllByUser(User user);

    Interest findByNameAndUser(String name, User user);

    Optional<Interest> findByIntIdAndUserUserId(int interestId, String userId);

}
