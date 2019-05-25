package agh.tai.twitter_news_feed.repository;

import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findAllByInterestOrderByPublishedAtDesc(Interest interest, Pageable pageable);
}
