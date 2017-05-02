package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Tweet.all", query = "SELECT t FROM Tweet t WHERE t.ownerTweet IN :following order by t.createdAt desc"),
        @NamedQuery(name = "Tweet.user", query = "SELECT t FROM Tweet t WHERE t.ownerTweet = :userId order by t.createdAt desc")
})
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tweetHeart")
    private List<Heart> hearts;

    @ManyToMany
    @JoinTable(
            name="TWEET_MEN",
            joinColumns=@JoinColumn(name="TWEET_ID"),
            inverseJoinColumns=@JoinColumn(name="MEN_ID"))
    private List<Mention> mentions;

    @ManyToOne
    private User ownerTweet;

    private String message;
    private Date createdAt;
    private Date updatedAt;

    public Tweet(String mes){
        message = mes;
    }

    public Tweet() {
    }

    @PrePersist
    public void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(List<Heart> hearts) {
        this.hearts = hearts;
    }

    public List<Mention> getMentions() {
        return mentions;
    }

    public void setMentions(List<Mention> mentions) {
        this.mentions = mentions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getOwnerTweet() {
        return ownerTweet;
    }

    public void setOwnerTweet(User ownerTweet) {
        this.ownerTweet = ownerTweet;
    }
}
