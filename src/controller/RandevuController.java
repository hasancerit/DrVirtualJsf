package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.swing.text.StyledEditorKit.BoldAction;

import Daos.HastaneDao;
import Services.DoktorService;
import Services.HastaService;
import Services.HastaneService;
import Services.RandevuService;
import model.Hasta;
import model.Randevu;
import model.RandevuAyrıntı;

@ManagedBean(name = "randevuController", eager = true)
@RequestScoped
public class RandevuController {
	
	@ManagedProperty(value = "#{randevuService}")
	private RandevuService randevuService;
	
	@ManagedProperty(value = "#{randevuBilgileri}")
	private RandevuBilgileri randevuBilgileri;
	
	@ManagedProperty(value = "#{hastaService}")
	private HastaService hastaService;
	
	@ManagedProperty(value = "#{hastaneService}")
	private HastaneService hastaneService;
	
	@ManagedProperty(value = "#{doktorService}")
	private DoktorService doktorService;
	

	public RandevuBilgileri getRandevuBilgileri() {
		return randevuBilgileri;
	}

	public void setRandevuBilgileri(RandevuBilgileri randevuBilgileri) {
		this.randevuBilgileri = randevuBilgileri;
	}

	public RandevuService getRandevuService() {
		return randevuService;
	}

	public void setRandevuService(RandevuService randevuService) {
		this.randevuService = randevuService;
	}

	
	private List<Randevu> randevuList = new ArrayList<Randevu>();

	public HastaneService getHastaneService() {
		return hastaneService;
	}

	public void setHastaneService(HastaneService hastaneService) {
		this.hastaneService = hastaneService;
	}

	public DoktorService getDoktorService() {
		return doktorService;
	}

	public void setDoktorService(DoktorService doktorService) {
		this.doktorService = doktorService;
	}

	public List<RandevuAyrıntı> hastaRandevuListDoldur() {
		List<RandevuAyrıntı> randevuListAyrıntı = new ArrayList<RandevuAyrıntı>();

		Hasta h = hastaService.hastaBul(randevuBilgileri.getHastaId());
		String randeuvlarS = h.getRandevular();
		ArrayList<String> randevuIds = parseRandevu(randeuvlarS);
		
		for(String randevu : randevuIds) {
			Randevu temp = randevuService.randevuBul(randevu);
			randevuList.add(temp);
		}
		
		for(Randevu randevu : randevuList) {
			RandevuAyrıntı temp = new RandevuAyrıntı();
			temp.setBolumIsim(randevu.getBolum());
			temp.setDoktorIsim(doktorService.doktorBul(randevu.getDoktor()).getIsim());
			temp.setHastaneIsim(hastaneService.hastaneBul(randevu.getHasta()).getHastaneadi());
			temp.setSaat(randevu.getTarih());
			temp.setDoktorId(randevu.getDoktor());
			randevuListAyrıntı.add(temp);
			System.out.println("EKLENDİ");
		}
		
		return randevuListAyrıntı;
	}

	public List<Randevu> getRandevuList() {
		return randevuList;
	}

	public void setRandevuList(List<Randevu> randevuList) {
		this.randevuList = randevuList;
	}
	

	public HastaService getHastaService() {
		return hastaService;
	}

	public void setHastaService(HastaService hastaService) {
		this.hastaService = hastaService;
	}
	
	private ArrayList<String> parseRandevu(String randevularS){
		ArrayList<String> saatler = new ArrayList<String>();
		String saat="";
		for(int i = 0 ; i < randevularS.length() ; i++) {
			if(randevularS.charAt(i) == ',') {
				saatler.add(saat);
				saat = "";
			}else {
				saat += randevularS.charAt(i);
			}
		}
		return saatler;
	}
}
