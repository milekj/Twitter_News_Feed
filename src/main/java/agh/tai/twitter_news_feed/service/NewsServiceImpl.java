package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.dao.NewsRepository;
import agh.tai.twitter_news_feed.dto.news_api.EverythingDto;
import agh.tai.twitter_news_feed.dto.news_api.NewsDto;
import agh.tai.twitter_news_feed.entity.Interest;
import agh.tai.twitter_news_feed.entity.News;
import agh.tai.twitter_news_feed.entity.User;
import agh.tai.twitter_news_feed.repository.InterestRepository;
import agh.tai.twitter_news_feed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private final static int MAX_NEWS_NUMBER = 100;
    private final static int DOWNLOADED_NEWS_NUMBER = 10;
    private NewsRepository newsRepository;
    private InterestRepository interestRepository;
    private RestTemplate newApiRestTemplate;
    private UserRepository userRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, InterestRepository interestRepository, RestTemplate newApiRestTemplate, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.interestRepository = interestRepository;
        this.newApiRestTemplate = newApiRestTemplate;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void updateNews(User user) {
        List<Interest> interests = interestRepository.findAllByUser(user);
        interests.forEach(i -> updateSingleNews(i, DOWNLOADED_NEWS_NUMBER));
    }

    private void updateSingleNews(Interest interest, int newsNumber) {
        System.out.println(interest.getIntId());
        List<News> news = getNews(interest, newsNumber);
        news.forEach(n -> {
            if (!newsRepository.existsById(n.getUrl()))
                newsRepository.save(n);
        });
    }

    private List<News> getNews(Interest interest, int newsNumber) {
        EverythingDto everythingDto = downloadFromApi(interest.getName(), newsNumber);
        if (everythingDto == null)
            return Collections.emptyList();
        return everythingDto.getArticles()
                .stream()
                .map(NewsDto::toNews)
                .peek(n -> n.setInterest(interest))
                .collect(Collectors.toList());
    }

    private EverythingDto downloadFromApi(String interestName, int newsNumber) {
        newsNumber = Integer.min(newsNumber, MAX_NEWS_NUMBER);
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("q", "\"" + interestName + "\"");
        urlParams.put("sortBy", "publishedAt");
        urlParams.put("language", "en");
        urlParams.put("pageSize", newsNumber);
        return newApiRestTemplate
                .getForObject("/everything/?sortBy={sortBy}&q={q}&language={language}&pageSize={pageSize}",
                        EverythingDto.class,
                        urlParams);
    }




}
