package agh.tai.twitter_news_feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
    private UserRepository userRepository;
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(userId);
        Connection<Twitter> connection = connectionRepository.findConnections(Twitter.class).get(0);
        User user = userRepository
                .findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        return new SocialUserDetailsImpl(user, connection);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }
}
