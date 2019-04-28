package agh.tai.twitter_news_feed.dao;

import agh.tai.twitter_news_feed.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

    List<Interest> findAllByUserId(String userId);

    @Transactional
    void deleteByNameAndUserId(String name, String userId);

}
