package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void Delete(User user){
        em.remove(user);
    }

    public void Edit(User user){
        em.merge(user);
    }

    public User Find(long userId){
        return em.find(User.class, userId);
    }

    public List<User> Followers(User user){
        return em.createNamedQuery("User.followers").setParameter("user", user).getResultList();
    }

    public void Save(User user){
        em.persist(user);
        em.flush();
    }

    public boolean Find(String email) {
        List<User> users = em.createNamedQuery("User.email").setParameter("email", email).getResultList();
        if (users.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public User login(String username, String password) {
        User user = (User)em.createNamedQuery("User.login").setParameter("username", username).setParameter("password", password).getSingleResult();
        return user;
    }
}
