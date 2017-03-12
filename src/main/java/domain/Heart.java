package domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Entity
public class Heart {

    @Id
    private long id;

    @ManyToOne
    private User ownerHeart;

    @ManyToOne
    private Tweet tweetHeart;

    public Date createdAt;
    public Date updatedAt;

    public Heart() {
    }

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwnerHeart() {
        return ownerHeart;
    }

    public void setOwnerHeart(User ownerHeart) {
        this.ownerHeart = ownerHeart;
    }

    public Tweet getTweetHeart() {
        return tweetHeart;
    }

    public void setTweetHeart(Tweet tweetHeart) {
        this.tweetHeart = tweetHeart;
    }
}
