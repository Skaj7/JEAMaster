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

import domain.Mention;

/**
 * Created by Kaj Suiker on 21-3-2017.
 */
public class MentionManagedBean {
    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM Mention AS o";

    private Mention myEntity;

    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public MentionManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("FirstApp");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }

    public Mention getEntity() {
        return myEntity;
    }

    public void setEntity(Mention entity) {
        myEntity = entity;
    }

    // add new Mention
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

        return "mentionList";
    }

    // save edited Mention
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
        return "mentionList";
    }

    // delete Mention
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Mention entity = getCurrentEntity();
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

        return "mentionList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Mention> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Mention>(entities));
    }

    public String startCreate() {
        myEntity = new Mention();
        return "createMention";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewMention";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editMention";
    }

    public Mention getCurrentEntity() {
        Mention entity = getEntityFromRequestParam();

        return entity == null ? myEntity : entity;
    }

    public Mention getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Mention entity = (Mention) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        myEntity = getCurrentEntity();
    }

    public Mention findEntity(long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Mention entity = entityManager.find(Mention.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Mention> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Mention entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Mention> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Mention> entities = (List<Mention>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
