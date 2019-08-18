package Daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class Dao<T> {
		private Class<T> entityClass;

	    public Dao(Class<T> entityClass) {
	        this.entityClass = entityClass;
	    }

	    public void create(T entity,EntityManager em) {
	            em.persist(entity);
	    }
	    
	    
	    public T find(Object id,EntityManager em) {
	        return em.find(entityClass, id);
	    }
	    
		@SuppressWarnings("unchecked")
		public List<T> findListByNamedQuery(String namedQuery,EntityManager em) throws Exception {
	        Query q = em.createNamedQuery(namedQuery);
	        return q.getResultList();
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<T> sorgu(String sorgu,Map<String, String>params,EntityManager em){
	    	  Query q = em.createNamedQuery(sorgu);
	          for (Map.Entry<String, String> entry : params.entrySet()) {
	              q.setParameter(entry.getKey(), entry.getValue());
	          }
	  		
	          return q.getResultList();
	      }
	    
	    public void updateMemberInfo(T entity,EntityManager em){
	        em.merge(entity);
	    }
	    
}
