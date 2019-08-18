package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "randevuBilgileri")
@SessionScoped
public class RandevuBilgileri {
	private String hastaneAdi="";
	private String bolumAdi;
	private String doktorAdi;
	private String saat;
	private boolean secildi = false;
	private Date date1;
	private String tarih;
	
	private Map<String,String> doktorMap = new HashMap<String, String>();
	private Map<String,String> saatMap = new HashMap<String, String>();
	private Map<String,String> bolumMap = new HashMap<String, String>();
	private Map<String,String> hastaneMap = new HashMap<String, String>();

	public String getHastaneAdi() {
		return hastaneAdi;
	}
	public void setHastaneAdi(String hastaneAdi) {
		this.hastaneAdi = hastaneAdi;
	}
	public String getBolumAdi() {
		return bolumAdi;
	}
	public void setBolumAdi(String bolumAdi) {
		this.bolumAdi = bolumAdi;
	}
	public String getDoktorAdi() {
		return doktorAdi;
	}
	public void setDoktorAdi(String doktorAdi) {
		this.doktorAdi = doktorAdi;
	}
	public String getSaat() {
		return saat;
	}
	public void setSaat(String saat) {
		this.saat = saat;
	}
	public boolean isSecildi() {
		return secildi;
	}
	public void setSecildi(boolean secildi) {
		this.secildi = secildi;
	}
	public Map<String,String> getDoktorMap() {
		return doktorMap;
	}
	public void setDoktorMap(Map<String,String> doktorMap) {
		this.doktorMap = doktorMap;
	}
	public Map<String,String> getSaatMap() {
		return saatMap;
	}
	public void setSaatMap(Map<String,String> saatMap) {
		this.saatMap = saatMap;
	}
	public Map<String,String> getBolumMap() {
		return bolumMap;
	}
	public void setBolumMap(Map<String,String> bolumMap) {
		this.bolumMap = bolumMap;
	}
	
	public void saatSec() {
		setSecildi(true);
	}
	
	public String info() {
		 return hastaneAdi+" Hastanesinden, " +bolumAdi+" Bölümünden, " +doktorAdi+" Doktorundan, " +saat+" Saatine Randevu Alýyorsunuz.";
	}
	public Map<String,String> getHastaneMap() {
		return hastaneMap;
	}
	public void setHastaneMap(Map<String,String> hastaneMap) {
		this.hastaneMap = hastaneMap;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
}
