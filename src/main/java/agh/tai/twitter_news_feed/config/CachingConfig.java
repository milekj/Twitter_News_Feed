package agh.tai.twitter_news_feed.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static agh.tai.twitter_news_feed.constants.Constants.CACHE_NAME;
import static agh.tai.twitter_news_feed.constants.Constants.TIME_LINE_OPERATIONS_TIME_TO_LIVE;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache timeLineOperationsCache = buildCache(CACHE_NAME, ticker, TIME_LINE_OPERATIONS_TIME_TO_LIVE);
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Collections.singleton(timeLineOperationsCache));
        return simpleCacheManager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, int secondsToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(secondsToExpire, TimeUnit.SECONDS)
                .ticker(ticker)
                .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

}