package jsf.old;

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

import domain.Heart;

/**
 * Created by Kaj Suiker on 21-3-2017.
 */
public class HeartManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Heart AS o";

    private Heart myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public HeartManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("FirstApp");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Heart getEntity() {
        return myEntity;
    }

    public void setEntity(Heart entity) {
        myEntity = entity;
    }

    // add new Heart
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

        return "heartList";
    }

    // save edited Heart
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
        return "heartList";
    }

    // delete Heart
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Heart entity = getCurrentEntity();
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

        return "heartList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Heart> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Heart>(entities));
    }

    public String startCreate() {
        myEntity = new Heart();
        return "createHeart";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewHeart";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editHeart";
    }

    public Heart getCurrentEntity() {
        Heart entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Heart getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Heart entity = (Heart) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Heart findEntity(long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Heart entity = entityManager.find(Heart.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Heart> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Heart entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Heart> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Heart> entities = (List<Heart>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
