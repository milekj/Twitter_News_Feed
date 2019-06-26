package agh.tai.twitter_news_feed.entity;

import javax.persistence.Embeddable;
import java.time.temporal.ChronoUnit;

@Embeddable
public class DurationWithUnit {
    private long duration;
    private ChronoUnit unit;

    public DurationWithUnit() {
    }

    public DurationWithUnit(long duration, ChronoUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

}
