import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */

@Stateless
public class TweetDAO {
    //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeeUnit");
    @PersistenceContext(unitName="FirstApp")
    EntityManager em;// = entityManagerFactory.createEntityManager();

    public TweetDAO(){
    }

    public void persist(){
        Tweet tweet = new Tweet("neeeee");
        em.persist(tweet);
    }
}
