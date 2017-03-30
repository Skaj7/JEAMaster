package jsf.old;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.User;

/**
 * Created by Kaj Suiker on 21-3-2017.
 */
public class UserManagedBean implements Serializable {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM User AS o";

    private User myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public UserManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("FirstApp");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public User getEntity() {
        return myEntity;
    }

    public void setEntity(User entity) {
        myEntity = entity;
    }

    // add new User
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

        return "userList";
    }

    // save edited User
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
        return "userList";
    }

    // delete User
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User entity = getCurrentEntity();
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

        return "userList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<User> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<User>(entities));
    }

    public String startCreate() {
        myEntity = new User();
        return "createUser";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewUser";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editUser";
    }

    public User getCurrentEntity() {
        User entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public User getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        User entity = (User) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public User findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        User entity = entityManager.find(User.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<User> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (User entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<User> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<User> entities = (List<User>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
