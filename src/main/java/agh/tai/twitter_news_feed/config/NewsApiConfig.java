package agh.tai.twitter_news_feed.config;

import agh.tai.twitter_news_feed.authentication.ApiCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

@Configuration
public class NewsApiConfig {

    private static final String NEWS_API_BASE_URL = "https://newsapi.org/v2";
    private static final String NEWS_API_KEY_HEADER_NAME = "X-Api-Key";
    private final ApiCredentials apiCredentials;

    @Autowired
    public NewsApiConfig(ApiCredentials apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    @Bean
    public RestTemplate NewsApiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(NEWS_API_BASE_URL));
        ClientHttpRequestInterceptor apiKeyHeaderInterceptor =
                (request, bytes, execution) -> {
                    request.getHeaders().add(NEWS_API_KEY_HEADER_NAME, apiCredentials.NEWS_API_KEY);
                    return execution.execute(request, bytes);
                };
        restTemplate.setInterceptors(Collections.singletonList(apiKeyHeaderInterceptor));
        return restTemplate;
    }

}
