package Daos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Hastane;
import model.Randevu;


@ManagedBean(name = "randevuDao")
@SessionScoped
public class RandevuDao extends Dao<Randevu>{
	
	 public RandevuDao(){
	        super(Randevu.class);
	 }
	 
		public Randevu randevuBul(String randevUID) {
			System.out.println("fsdfsadfsadfasdfasdfasdfasfas");

			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
	        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
			System.out.println("Randevu Id iç:"+randevUID);

	        	Randevu randevu = super.find(Integer.parseInt(randevUID), entityManagerObject);
	        	System.out.println("teeest"+randevu.getDoktor());
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
	        
	        return randevu;
		}
}
