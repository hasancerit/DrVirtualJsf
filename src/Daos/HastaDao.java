package Daos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Doktor;
import model.Hasta;
import model.Hastane;

@ManagedBean(name = "hastaDao")
@SessionScoped
public class HastaDao extends Dao<Hasta>{
	
	 public HastaDao(){
	        super(Hasta.class);
	 }
	 
	 public Hasta hastaBul(String hastaId) {
			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
	        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
	        Hasta hasta = null;
			try {
				hasta = super.find(Integer.parseInt(hastaId), entityManagerObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
	        
	        return hasta;
	 }
	 
		public void updateHasta(Hasta h) {
			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
	        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
	        entityManagerObject.getTransaction().begin();
	        
	        super.updateMemberInfo(h,entityManagerObject);
	        
	        entityManagerObject.getTransaction().commit();
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
		}
}
