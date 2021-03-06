package agh.tai.twitter_news_feed.config;

import agh.tai.twitter_news_feed.authentication.ApiCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {
    private DataSource dataSource;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private final ApiCredentials apiCredentials;

    @Autowired
    public SocialConfig(ApiCredentials apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer factoryConfigurer, Environment environment) {
        TwitterConnectionFactory twitterFactory = new TwitterConnectionFactory(apiCredentials.CONSUMER_KEY, apiCredentials.CONSUMER_SECRET);
        factoryConfigurer.addConnectionFactory(twitterFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils() {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setConnectionFactoryLocator(ConnectionFactoryLocator connectionFactoryLocator) {
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

}
