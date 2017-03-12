package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    public void setEm(EntityManager em) {
        this.em = em;
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

    public List<Tweet> GetLatestTweets(List<User> following){
        List<Tweet> ass = em.createNamedQuery("Tweet.all").setParameter("following", following).getResultList();
        return ass;
    }
}
