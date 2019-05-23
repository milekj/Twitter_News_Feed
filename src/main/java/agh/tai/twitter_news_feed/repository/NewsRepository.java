package agh.tai.twitter_news_feed.repository;

import agh.tai.twitter_news_feed.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {
}
