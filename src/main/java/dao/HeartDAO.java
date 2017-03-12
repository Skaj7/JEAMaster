package dao;

import domain.Heart;
import domain.Mention;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kaj Suiker on 11-3-2017.
 */
@Stateless
public class HeartDAO {
    @PersistenceContext(unitName="FirstApp")
    EntityManager em;

    public HeartDAO(){
    }

    public void Save(Heart heart){
        em.persist(heart);
    }

    public boolean Delete(Heart heart){
        em.remove(heart);
        return true;
    }

    public void Edit(Heart heart){
        em.merge(heart);
    }

    public Heart Find(long id){
        return em.find(Heart.class, id);
    }

    public String GetDateTime(Heart heart){
        String dateTime = DateTime.CURRENT_TIME;
        return dateTime;
    }
}
