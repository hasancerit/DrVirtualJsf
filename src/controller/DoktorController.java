package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Services.DoktorService;
import model.Doktor;

@ManagedBean(name = "doktorController", eager = true)
@SessionScoped
public class DoktorController {
	
	@ManagedProperty(value = "#{doktorService}")
	private DoktorService doktorService;
	
	@ManagedProperty(value = "#{randevuBilgileri}")
	private RandevuBilgileri randevuBilgileri;
	
	
	
	public DoktorService getDoktorService() {
		return doktorService;
	}

	public void setDoktorService(DoktorService doktorService) {
		this.doktorService = doktorService;
	}

	public RandevuBilgileri getRandevuBilgileri() {
		return randevuBilgileri;
	}

	public void setRandevuBilgileri(RandevuBilgileri randevuBilgileri) {
		this.randevuBilgileri = randevuBilgileri;
	}

	public void doktorListeDoldur() {	
		System.out.println("DOKTOR DOLDUR ÇALIÞTI");
		randevuBilgileri.getDoktorMap().clear();
		randevuBilgileri.getSaatMap().clear();
		
		randevuBilgileri.setDoktorAdi("");
		randevuBilgileri.setSaat("");
		randevuBilgileri.setTarih("");
		randevuBilgileri.setSecildi(false);

		Map<String, String> doktorMap = new HashMap<String, String>();
		List<Doktor> doktorList = doktorService.doktorBul(randevuBilgileri.getHastaneAdi(),randevuBilgileri.getBolumAdi());
		for(Doktor d:doktorList) {
			doktorMap.put(d.getIsim(),""+d.getIddoktor());
		}
		randevuBilgileri.setDoktorMap(doktorMap);
	}
	
	public void saatListDoldur(){
		randevuBilgileri.getSaatMap().clear();
		
		randevuBilgileri.setSaat("");
		randevuBilgileri.setSecildi(false);
		
		//Hastanýn seçtiði tarihi aldýk
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    randevuBilgileri.setTarih(formatter.format(randevuBilgileri.getDate1()));
	    System.out.println(randevuBilgileri.getTarih());
	    
	    //Doktoru ve randevularýný çektik
		Doktor doktor = doktorService.doktorBul(randevuBilgileri.getDoktorAdi());
		ArrayList<String> randevuList = parseSaat(doktor.getRandevular());
		System.out.println("ÇIKAN: " +randevuList.toString());
		
		//Randevu  tarihinin dolu saatlerini çektik
		ArrayList<String> doluSaatler = new ArrayList<String>();
		for(String s:randevuList) {
			System.out.println("COMPARE:"+randevuBilgileri.getTarih()+"    "+s.substring(0,10));
			if(randevuBilgileri.getTarih().equals(s.substring(0,10))) {
				doluSaatler.add(s.substring(11));
				System.out.println("Eklendi:"+s.substring(11));
			}
				
		}
		
		//Uygun saatleri atadýk
		Map<String, String> saatMap = new LinkedHashMap<String, String>();
		for (int i = 8; i <= 18; i++) {
			boolean dolu = false;
			for (String doluS : doluSaatler) {
				if(doluS.equals(""+i)) {
					dolu = true;
					break;
				}
			}
			if(!dolu) {
				saatMap.put(""+i, ""+i);
			}
		}
		randevuBilgileri.setSaatMap(saatMap);
	}
	
	private ArrayList<String> parseSaat(String saatlerS){
		System.out.println("GELEN:"+saatlerS);
		ArrayList<String> saatler = new ArrayList<String>();
		String saat="";
		for(int i = 0 ; i < saatlerS.length() ; i++) {
			if(saatlerS.charAt(i) == ',') {
				saatler.add(saat);
				saat = "";
			}/*else if(i == saatlerS.length()-1) {
				saat += saatlerS.charAt(i);
				saatler.add(saat);
			}*/else {
				saat += saatlerS.charAt(i);
			}
		}
		return saatler;
	}
	
	public void updateDoktor(){
		randevuBilgileri.setHastaneAdi("");
		randevuBilgileri.getHastaneMap().clear();

		System.out.println(randevuBilgileri.getDoktorAdi());
		Doktor d = doktorService.doktorBul(randevuBilgileri.getDoktorAdi());
		System.out.println((d.getRandevular()+randevuBilgileri.getTarih()+"&"+randevuBilgileri.getSaat()+","));
		d.setRandevular(d.getRandevular()+randevuBilgileri.getTarih()+"&"+randevuBilgileri.getSaat()+",");
		doktorService.doktorGuncelle(d);
	}
	
	public void tarihSýfýrla(){
		randevuBilgileri.setTarih("");
	}
}
