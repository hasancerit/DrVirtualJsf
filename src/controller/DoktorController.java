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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Services.DoktorService;
import Services.HastaService;
import model.Doktor;
import model.Hasta;
import model.Randevu;

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
	
	
	@ManagedProperty(value = "#{hastaService}")
	private HastaService hastaService;
	
	public HastaService getHastaService() {
		return hastaService;
	}

	public void setHastaService(HastaService hastaService) {
		this.hastaService = hastaService;
	}

	public void updateDoktor(){
		randevuBilgileri.setHastaneAdi("");
		randevuBilgileri.getHastaneMap().clear();

		//Randevu Table'a ekle
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        entityManagerObject.getTransaction().begin();

        	Randevu r = new Randevu();
			r.setBolum(randevuBilgileri.getBolumAdi());
			r.setDoktor(randevuBilgileri.getDoktorAdi());
			r.setHasta(randevuBilgileri.getHastaId());
			r.setHastane(randevuBilgileri.getHastaneAdi());
			r.setTarih(randevuBilgileri.getTarih()+"&"+randevuBilgileri.getSaat());
        	
            entityManagerObject.persist(r);
        entityManagerObject.getTransaction().commit();
        entityManagerObject.close();
        entityManagerFactoryObject.close();         
        
     		//Hasta Güncelle
     		Hasta h = hastaService.hastaBul(randevuBilgileri.getHastaId());
     		h.setRandevular(h.getRandevular()+r.getIdRandevu()+",");
     		hastaService.hastaGuncelle(h);
     		
    		//Doktoru Güncelle
    		Doktor d = doktorService.doktorBul(randevuBilgileri.getDoktorAdi());
    		d.setRandevular(d.getRandevular()+randevuBilgileri.getTarih()+"&"+randevuBilgileri.getSaat()+",");
    		d.setRandevuIds(d.getRandevuIds()+r.getIdRandevu()+",");
    		doktorService.doktorGuncelle(d);
         
	}
	
	public void tarihSýfýrla(){
		randevuBilgileri.setTarih("");
	}
}
