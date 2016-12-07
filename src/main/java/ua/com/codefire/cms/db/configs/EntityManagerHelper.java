package ua.com.codefire.cms.db.configs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by User on 07.12.2016.
 */
public class EntityManagerHelper {
    private EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();

    public EntityManagerHelper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            // set your flush mode here
            threadLocal.set(em);
        }
        return em;
    }

    public void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }


    public void begin() {
        getEntityManager().getTransaction().begin();
    }

    public <T> void remove(T thingToRemove) {
        getEntityManager().remove(thingToRemove);
    }

    public <T> void persist(T thingToPersist) {
        try {
            getEntityManager().persist(thingToPersist);
        } catch(Exception e)
        {
            System.out.println("Something went wrong");
        }
    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();

    }

    public <T> T find(Class<T> a, long id) {
        return getEntityManager().find(a, id);
    }
}
