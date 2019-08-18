package Services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import Daos.DoktorDao;
import model.Doktor;

@ManagedBean(name = "doktorService", eager = true)
@SessionScoped
public class DoktorService {
	
    @ManagedProperty(value = "#{doktorDao}")
	private DoktorDao doktorDao;
    
    
    
    public DoktorDao getDoktorDao() {
		return doktorDao;
	}

	public void setDoktorDao(DoktorDao doktorDao) {
		this.doktorDao = doktorDao;
	}

	public List<Doktor> doktorBul(String hastane,String bolum){
    	Map<String, String> params = new LinkedHashMap<String, String>();
    	params.put("hastane", hastane);
    	params.put("bolüm", bolum);
    	
    	return doktorDao.doktorBul("Doktor.findByBolumAndHastane", params);
    }
    
    public Doktor doktorBul(String Id){
    	return doktorDao.doktorBul(Id);
    }
    
    public void doktorGuncelle(Doktor d){
    	doktorDao.updateDoktor(d);
    }
}
