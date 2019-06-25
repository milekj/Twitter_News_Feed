package agh.tai.twitter_news_feed.repository;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {

    List<News> findAllByInterestAndInterestExcludedFalseOrderByPublishedAtDesc(Interest interest, Pageable pageable);

}
