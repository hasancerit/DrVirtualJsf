package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Services.HastaService;

@ManagedBean(name = "hastaController", eager = true)
@SessionScoped
public class HastaController {
	@ManagedProperty(value = "#{hastaService}")
	private HastaService hastaService;
	
	@ManagedProperty(value = "#{randevuBilgileri}")
	private RandevuBilgileri randevuBilgileri;
	
	public void randevularýAl() {
		String randevular = hastaService.randevu(randevuBilgileri.getHastaId());
	}

	public HastaService getHastaService() {
		return hastaService;
	}

	public void setHastaService(HastaService hastaService) {
		this.hastaService = hastaService;
	}

	public RandevuBilgileri getRandevuBilgileri() {
		return randevuBilgileri;
	}

	public void setRandevuBilgileri(RandevuBilgileri randevuBilgileri) {
		this.randevuBilgileri = randevuBilgileri;
	}
	
	
	
}
