package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	 
	  public static final boolean DEBUG = true;
	  private static final String UNIDAD_PERSISTENCIA = "TFGCarlos";
	  
	  private static final PersistenceManager singleton = new PersistenceManager();
	  
	  private EntityManagerFactory emf;
	  
	  public static PersistenceManager getInstance() {
	    
	    return singleton;
	  }
	  
	  private PersistenceManager() {
	  }
	 
	  public EntityManagerFactory getEntityManagerFactory() {
	    
	    if (emf == null)
	      createEntityManagerFactory();
	    return emf;
	  }
	  
	  public void closeEntityManagerFactory() {
	    
	    if (emf != null) {
	      emf.close();
	      emf = null;
	      if (DEBUG)
	        System.out.println(
	            "n*** Persistencia Finalizada " + new java.util.Date());
	    }
	  }
	  
	  private void createEntityManagerFactory() {
	    
	    this.emf = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
	    if (DEBUG)
	        System.out.println(
	            "n*** Persistencia Inicializada " + new java.util.Date());
	  }
	}
