package agh.tai.twitter_news_feed.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "userId"}))
public class Interest {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private int intId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "userId"), @JoinColumn(name = "providerId"), @JoinColumn(name = "providerUserId")})
    private User user;

    @Column(nullable = false)
    private boolean excluded;

    public Interest() {
    }

    public Interest(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isIncluded() {
        return !excluded;
    }
}
