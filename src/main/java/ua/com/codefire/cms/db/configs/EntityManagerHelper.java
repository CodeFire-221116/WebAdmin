package ua.com.codefire.cms.db.configs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by User on 07.12.2016.
 */

/**
 * Class, used in order to provide EntityManager and main functionality to communicate with it.
 * Used in repositories in order ro communicate with DataBase.
 * EntityManagers are retrieved from ThreadLocal in order to make them thread-safe.
 */
public class EntityManagerHelper {
    private EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
    private EntityManager currEntityManager;

    public EntityManagerHelper(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Method to get EntityManager. Tries to get it from ThreadLocal. Returns it in case of success.
     * Creates a new instance of entityManager in case otherwise, putts it to ThreadLocal and returns for usage.
     * @return Thread safe Entity Manager instance ready to communicate with DataBase
     */
    public EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            // set your flush mode here
            threadLocal.set(em);
        }

        currEntityManager = em;
        return em;
    }

    /**
     * Closes busy EntityManager
     */
    public void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

    /**
     * Closes Entity manager Factory in order to end session with DataBase
     */
    public void closeEntityManagerFactory() {
        emf.close();
    }


    public void begin() {
        currEntityManager = getEntityManager();
        currEntityManager.getTransaction().begin();
    }

    public <T> void remove(T thingToRemove) {
        if(currEntityManager != null) {
            currEntityManager.remove(thingToRemove);
        }
    }

    public <T> void persist(T thingToPersist) {
        try {
            currEntityManager.persist(thingToPersist);
        } catch(Exception e)
        {
            System.out.println("Something went wrong");
        }
    }

    public void rollback() {
        if(currEntityManager != null) {
            currEntityManager.getTransaction().rollback();
        }
    }

    public void commit() {
        if(currEntityManager != null) {
            currEntityManager.getTransaction().commit();
        }
    }

    public <T> T find(Class<T> a, long id) {
        return getEntityManager().find(a, id);
    }
}
