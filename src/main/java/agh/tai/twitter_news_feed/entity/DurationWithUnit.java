package agh.tai.twitter_news_feed.entity;

import javax.persistence.Embeddable;
import java.util.concurrent.TimeUnit;

@Embeddable
public class DurationWithUnit {
    private long duration;
    private TimeUnit unit;

    public DurationWithUnit() {
    }

    public DurationWithUnit(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }
}
