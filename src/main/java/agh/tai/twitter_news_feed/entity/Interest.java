package agh.tai.twitter_news_feed.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "userid"}))
public class Interest {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private int intId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "userid", referencedColumnName = "userid"),
            @JoinColumn(name = "providerid", referencedColumnName = "providerid"),
            @JoinColumn(name = "provideruserid", referencedColumnName = "provideruserid")})
    private User user;

    @Column(nullable = false)
    private boolean excluded;

    @OneToMany(mappedBy = "interest", fetch = FetchType.LAZY)
    private List<News> news;

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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
