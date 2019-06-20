package agh.tai.twitter_news_feed.service;

import agh.tai.twitter_news_feed.authentication.SocialUserDetailsImpl;
import agh.tai.twitter_news_feed.entity.User;
import agh.tai.twitter_news_feed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

    private final UserRepository userRepository;
    private final UsersConnectionRepository usersConnectionRepository;

    @Autowired
    public SocialUserDetailsServiceImpl(UserRepository userRepository,
                                        UsersConnectionRepository usersConnectionRepository) {
        this.userRepository = userRepository;
        this.usersConnectionRepository = usersConnectionRepository;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(userId);
        Connection<Twitter> connection = connectionRepository.findConnections(Twitter.class).get(0);
        User user = userRepository
                .findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        return new SocialUserDetailsImpl(user, connection);
    }

}
