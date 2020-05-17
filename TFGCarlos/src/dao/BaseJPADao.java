package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BaseJPADao {
	/**
	 * Default no-arg constructor
	 */
	public BaseJPADao() {
	}
	
	/**
	 * Returns JPA EntityManager reference.
	 * @return 
	 */
	public EntityManager getEntityManager() {
		//return JPADaoFactory.createEntityManager();
		EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
		return emf.createEntityManager();
	}
}
