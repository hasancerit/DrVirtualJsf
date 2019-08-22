package Services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Daos.DoktorDao;
import Daos.HastaDao;
import model.Doktor;
import model.Hasta;
import model.Hastane;

@ManagedBean(name = "hastaService", eager = true)
@SessionScoped
public class HastaService {
	
    @ManagedProperty(value = "#{hastaDao}")
	private HastaDao hastaDao;
    
    public HastaDao getHastaDao() {
		return hastaDao;
	}

	public void setHastaDao(HastaDao hastaDao) {
		this.hastaDao = hastaDao;
	}

	public String randevu(String hastaId) {
    	Hasta hasta = hastaDao.hastaBul(hastaId);
    	String randevular = hasta.getRandevular();
    	return randevular;
    }
    
    public Hasta hastaBul(String hastaId) {
    	return hastaDao.hastaBul(hastaId);
    }
    
    public void hastaGuncelle(Hasta h){
    	hastaDao.updateHasta(h);
    }
    

    
}
