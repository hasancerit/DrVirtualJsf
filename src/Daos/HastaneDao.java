package Daos;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Doktor;
import model.Hastane;

@ManagedBean(name = "hastaneDao")
@SessionScoped
public class HastaneDao extends Dao<Hastane>{
	
	 public HastaneDao(){
	        super(Hastane.class);
	 }

	public Hastane bolumBul(String hastaneAdi) {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        	Hastane hastane = super.find(Integer.parseInt(hastaneAdi), entityManagerObject);
        entityManagerObject.close();
        entityManagerFactoryObject.close();
        
        return hastane;
	}
	public List<Hastane> hastaneBul(String sorgu){
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        List<Hastane> hastaneler = null;
		try {
			hastaneler = super.findListByNamedQuery(sorgu, entityManagerObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
        entityManagerObject.close();
        entityManagerFactoryObject.close();
        
        return hastaneler;
	}
	
	public Hastane hastaneBulId(String Id) {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        
        Hastane d = super.find(Integer.parseInt(Id),entityManagerObject);
                
        entityManagerObject.close();
        entityManagerFactoryObject.close();
		return d;
	}
}