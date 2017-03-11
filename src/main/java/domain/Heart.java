package domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Entity
public class Heart {

    @Id
    private long id;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Tweet tweet;

    private Timestamp date;

    public Heart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
