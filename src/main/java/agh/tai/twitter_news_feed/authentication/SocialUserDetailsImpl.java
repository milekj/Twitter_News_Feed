package agh.tai.twitter_news_feed.authentication;

import agh.tai.twitter_news_feed.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.connect.Connection;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.twitter.api.Twitter;

import java.util.Collection;
import java.util.Collections;

public class SocialUserDetailsImpl implements SocialUserDetails {
    private User user;
    private Connection<Twitter> connection;

    public SocialUserDetailsImpl(User user, Connection<Twitter> connection) {
        this.user = user;
        this.connection = connection;
    }

    public Connection<Twitter> getConnection() {
        return connection;
    }

    @Override
    public String getUserId() {
        return user.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getDisplayName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
