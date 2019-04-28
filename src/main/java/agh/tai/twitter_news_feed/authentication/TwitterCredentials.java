package agh.tai.twitter_news_feed.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:credentials.properties")
@Component
public class TwitterCredentials {

    @Value("${twitter.consumerKey}")
    public String CONSUMER_KEY;

    @Value("${twitter.consumerSecret}")
    public String CONSUMER_SECRET;
}
