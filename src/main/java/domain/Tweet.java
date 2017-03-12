package domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */
@Entity
@NamedQuery(name = "Tweet.all", query = "SELECT t FROM Tweet t")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tweet")
    private List<Heart> hearts;

    @ManyToMany
    private List<Mention> mentions;

    @ManyToOne
    private User owner;

    private String message;

    public Tweet(String mes){
        message = mes;
    }

    public Tweet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
