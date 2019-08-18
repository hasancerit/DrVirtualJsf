package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import Services.HastaneService;
import model.Hastane;

@ManagedBean(name = "hastaneController", eager = true)
@SessionScoped
public class HastaneController {
	
	@ManagedProperty(value = "#{hastaneService}")
	private HastaneService hastaneService;
	
	@ManagedProperty(value = "#{randevuBilgileri}")
	private RandevuBilgileri randevuBilgileri;
	
	
	
	public HastaneService getHastaneService() {
		return hastaneService;
	}

	public void setHastaneService(HastaneService hastaneService) {
		this.hastaneService = hastaneService;
	}

	public RandevuBilgileri getRandevuBilgileri() {
		return randevuBilgileri;
	}

	public void setRandevuBilgileri(RandevuBilgileri randevuBilgileri) {
		this.randevuBilgileri = randevuBilgileri;
	}

	public void bolumListeDoldur() {
		System.out.println("bolum doldur calisti");
		randevuBilgileri.getBolumMap().clear();
		randevuBilgileri.getDoktorMap().clear();
		randevuBilgileri.getSaatMap().clear();
		
		randevuBilgileri.setBolumAdi("");
		randevuBilgileri.setDoktorAdi("");
		randevuBilgileri.setSaat("");
		randevuBilgileri.setTarih("");
		randevuBilgileri.setSecildi(false);
		
		Hastane h = hastaneService.bolumBul(randevuBilgileri.getHastaneAdi());
		ArrayList<String> bolumler = parseBolum(h.getBolumler());
		System.out.println("BOLUMLEEER:"+bolumler.toString());
		Map<String,String> bolumMap = new HashMap<String, String>();
		for(String bolum:bolumler) {
			bolumMap.put(bolum,bolum);
		}
		randevuBilgileri.setBolumMap(bolumMap);
		System.out.println("HASTANE ADÝ:"+randevuBilgileri.getHastaneAdi());
	}

	public ArrayList<String> parseBolum(String bolumler) {
		System.out.println("GELEN:"+bolumler+" Son Ýndex:" + (bolumler.length()-1));
		ArrayList<String> bolumList = new ArrayList<String>();
		String bolum = "";
		for(int  i = 0  ; i < bolumler.length() ; i++) {
			if(bolumler.charAt(i) == ',') {
				System.out.println("Eklendi:"+bolum);
				bolumList.add(bolum);
				bolum = "";
			}else if(i == (bolumler.length()-1)) {
				bolum += bolumler.charAt(i);
				bolumList.add(bolum);
			}else {
				bolum += bolumler.charAt(i);
			}
		}
		return bolumList;
	}

	public void hastaneDoldur(){
		System.out.println("HASTANE DOLDUR");
		randevuBilgileri.getHastaneMap().clear();
		randevuBilgileri.getBolumMap().clear();
		randevuBilgileri.getDoktorMap().clear();
		randevuBilgileri.getSaatMap().clear();
		
		randevuBilgileri.setHastaneAdi("");
		randevuBilgileri.setBolumAdi("");
		randevuBilgileri.setDoktorAdi("");
		randevuBilgileri.setSaat("");
		randevuBilgileri.setSecildi(false);
		
		List<Hastane> hastaneler =  hastaneService.hastaneBul();
		Map<String,String> hastaneMap = new HashMap<String, String>();
		for(Hastane h:hastaneler) {
			hastaneMap.put(h.getHastaneadi(),""+h.getIdhastane());
		}
		randevuBilgileri.setHastaneMap(hastaneMap);
	}
	
	public Map<String,String> hastaneDoldur2(){
		System.out.println("HASTANE DOLDUR2");
		randevuBilgileri.getHastaneMap().clear();
		randevuBilgileri.getBolumMap().clear();
		randevuBilgileri.getDoktorMap().clear();
		randevuBilgileri.getSaatMap().clear();
		
		//randevuBilgileri.setHastaneAdi("");
		randevuBilgileri.setBolumAdi("");
		randevuBilgileri.setDoktorAdi("");
		randevuBilgileri.setSaat("");
		randevuBilgileri.setTarih("");
		randevuBilgileri.setSecildi(false);
		
		List<Hastane> hastaneler =  hastaneService.hastaneBul();
		Map<String,String> hastaneMap = new HashMap<String, String>();
		for(Hastane h:hastaneler) {
			hastaneMap.put(h.getHastaneadi(),""+h.getIdhastane());
		}
		System.out.println("HASTANE DOLDUR 2 HASTANE ADÝ:"+randevuBilgileri.getHastaneAdi());
		return hastaneMap;
	}
}
