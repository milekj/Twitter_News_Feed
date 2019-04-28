package agh.tai.twitter_news_feed.entity.utils;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {
    private String userId;
    private String providerId;
    private String providerUserId;

    public UserId() {
    }

    public UserId(String userId, String providerId, String providerUserId) {
        this.userId = userId;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId that = (UserId) o;
        return userId.equals(that.userId) &&
                providerId.equals(that.providerId) &&
                providerUserId.equals(that.providerUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, providerId, providerUserId);
    }
}
