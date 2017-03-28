package jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Tweet;

/**
 * Created by Kaj Suiker on 21-3-2017.
 */
public class TweetManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Tweet AS o";

    private Tweet myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public TweetManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("FirstApp");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Tweet getEntity() {
        return myEntity;
    }

    public void setEntity(Tweet entity) {
        myEntity = entity;
    }

    // add new Tweet
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "tweetList";
    }

    // save edited Tweet
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            myEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "tweetList";
    }

    // delete Tweet
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Tweet entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "tweetList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Tweet> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Tweet>(entities));
    }

    public String startCreate() {
        myEntity = new Tweet();
        return "createTweet";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewTweet";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editTweet";
    }

    public Tweet getCurrentEntity() {
        Tweet entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Tweet getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Tweet entity = (Tweet) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Tweet findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Tweet entity = entityManager.find(Tweet.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Tweet> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Tweet entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Tweet> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Tweet> entities = (List<Tweet>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
