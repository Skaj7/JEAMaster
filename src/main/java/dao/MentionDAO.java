package dao;

import domain.Mention;
import domain.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Stateless
public class MentionDAO {
    @PersistenceContext(unitName="FirstApp")
    EntityManager em;

    public MentionDAO(){
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void Save(Mention mention){
        em.persist(mention);
    }

    public boolean Delete(Mention mention){
        em.remove(mention);
        return true;
    }

    public void Edit(Mention mention){
        em.merge(mention);
    }

    public Mention Find(long id){
        return em.find(Mention.class, id);
    }
}
