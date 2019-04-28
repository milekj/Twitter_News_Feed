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

    private String userId;

    public Interest() {
    }

    public Interest(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public Interest(int intId, String userId) {
        this.intId = intId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
