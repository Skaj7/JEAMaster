package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Stateless
public class UserDAO {
    @PersistenceContext(unitName="FirstApp")
    EntityManager em;

    public UserDAO(){
    }

    public void Save(User user){
        em.persist(user);
    }

    public boolean Delete(User user){
        em.remove(user);
        return true;
    }

    public void Edit(User user){
        em.merge(user);
    }

    public User Find(long id){
        return em.find(User.class, id);
    }

    public List<User> Following(User user){
        List<User> following = new ArrayList<>();
        return following;
    }

    public List<User> Followers(User user){
        List<User> followers = new ArrayList<>();
        return followers;
    }
}
