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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User ownerHeart;

    @ManyToOne
    private Tweet tweetHeart;

    private Date createdAt;
    private Date updatedAt;

    public Heart() {
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
