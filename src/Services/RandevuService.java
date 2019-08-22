package Services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.websocket.WebSocketContainer;

import Daos.HastaDao;
import Daos.RandevuDao;
import model.Randevu;

@ManagedBean(name = "randevuService")
@SessionScoped
public class RandevuService {
   @ManagedProperty(value = "#{randevuDao}")
   private RandevuDao randevuDao;

	public RandevuDao getRandevuDao() {
		return randevuDao;
	}

	public void setRandevuDao(RandevuDao randevuDao) {
		this.randevuDao = randevuDao;
	}
	    
	public Randevu randevuBul(String randevuId){
		Randevu randevu = randevuDao.randevuBul(randevuId);
    	return randevu;
    }
}
