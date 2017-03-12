package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */

@Stateless
public class TweetDAO {

    @PersistenceContext(unitName="FirstApp")
    EntityManager em;

    public TweetDAO(){
    }

    public void Save(Tweet tweet){
        em.persist(tweet);
    }

    public boolean Delete(Tweet tweet){
        em.remove(tweet);
        return true;
    }

    public void Edit(Tweet tweet){
        em.merge(tweet);
    }

    public Tweet Find(long id){
        return em.find(Tweet.class, id);
    }

    public List<Tweet> GetLatetsTweets(List<User> following){
        return em.createNamedQuery("Tweet.all").setMaxResults(10).getResultList();
    }

    public int TweetCount(User user){
        int count = 0;
        return count;
    }
}
