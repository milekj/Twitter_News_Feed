package agh.tai.twitter_news_feed.entity;

import agh.tai.twitter_news_feed.entity.identity.UserId;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "userconnection",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "providerId", "rank"})}
)
@IdClass(UserId.class)
public class User {
    @Id
    @Column(nullable = false)
    private String userId;

    @Id
    @Column(nullable = false)
    private String providerId;

    @Id
    private String providerUserId;

    private int rank;

    private String displayName;

    @Column(length = 512)
    private String profileUrl;

    @Column(length = 512)
    private String imageUrl;

    @Column(length = 512, nullable = false)
    private String accessToken;

    @Column(length = 512)
    private String secret;

    @Column(length = 512)
    private String refreshToken;

    private Long expireTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interests;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

}
