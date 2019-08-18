package Daos;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Doktor;

@ManagedBean(name = "doktorDao")
@SessionScoped
public class DoktorDao extends Dao<Doktor>{
	
	 public DoktorDao(){
	        super(Doktor.class);
	 }
	
	public List<Doktor> doktorBul(String sorgu,Map<String, String> params) {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        
        List<Doktor> doktorlar = super.sorgu(sorgu, params, entityManagerObject);
                
        entityManagerObject.close();
        entityManagerFactoryObject.close();
        return doktorlar;
	}
	
	public Doktor doktorBul(String Id) {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        
        Doktor d = super.find(Integer.parseInt(Id),entityManagerObject);
                
        entityManagerObject.close();
        entityManagerFactoryObject.close();
		return d;
	}
	
	public void updateDoktor(Doktor d) {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        entityManagerObject.getTransaction().begin();
        
        super.updateMemberInfo(d,entityManagerObject);
        
        entityManagerObject.getTransaction().commit();
        entityManagerObject.close();
        entityManagerFactoryObject.close();
	}
}