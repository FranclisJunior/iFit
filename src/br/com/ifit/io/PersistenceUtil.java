package br.com.ifit.io;

import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class PersistenceUtil {

    private static final String PERSISTENCE_FILE = "settings";
    private static final String PERSISTENCE_UNIT_NAME = "unidade_persistencia";
    private static String nomeDaUnidadeDePersistencia;
    private static EntityManagerFactory entityManagerFactory = null;
    private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();

    public PersistenceUtil() {
    }

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PERSISTENCE_FILE);
        nomeDaUnidadeDePersistencia = resourceBundle.getString(PERSISTENCE_UNIT_NAME);
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(nomeDaUnidadeDePersistencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        try {
            EntityManager entityManager = threadEntityManager.get();
            if (entityManager == null || !entityManager.isOpen()) {
                entityManager = entityManagerFactory.createEntityManager();
                PersistenceUtil.threadEntityManager.set(entityManager);
            }
            return entityManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeEntityManager() {
        EntityManager em = threadEntityManager.get();
        if (em != null) {
            EntityTransaction transaction = em.getTransaction();
            if (transaction.isActive()) {
                transaction.commit();
            }
            em.close();
            threadEntityManager.set(null);
        }
    }

    public static void closeEntityManagerFactory() {
        closeEntityManager();
        entityManagerFactory.close();
    }

}