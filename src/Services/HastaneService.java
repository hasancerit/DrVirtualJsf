package Services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Daos.DoktorDao;
import Daos.HastaneDao;
import model.Doktor;
import model.Hastane;

@ManagedBean(name = "hastaneService")
@SessionScoped
public class HastaneService {
	
    @ManagedProperty(value = "#{hastaneDao}")
    private HastaneDao hastaneDao;
    
    
    
    public HastaneDao getHastaneDao() {
		return hastaneDao;
	}

	public void setHastaneDao(HastaneDao hastaneDao) {
		this.hastaneDao = hastaneDao;
	}

	public Hastane bolumBul(String hastaneAdi){
    	return hastaneDao.bolumBul(hastaneAdi);
    }
    
    public List<Hastane> hastaneBul(){
    	return hastaneDao.hastaneBul("Hastane.findAll");
    }
}
