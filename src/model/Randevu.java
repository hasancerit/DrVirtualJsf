package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the randevu database table.
 * 
 */
@Entity
@NamedQuery(name="Randevu.findAll", query="SELECT r FROM Randevu r")
public class Randevu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRandevu;

	private String bolum;

	private String doktor;

	private String hasta;

	private String hastane;

	private String randevucol;

	private String tarih;

	public Randevu() {
	}

	public int getIdRandevu() {
		return this.idRandevu;
	}

	public void setIdRandevu(int idRandevu) {
		this.idRandevu = idRandevu;
	}

	public String getBolum() {
		return this.bolum;
	}

	public void setBolum(String bolum) {
		this.bolum = bolum;
	}

	public String getDoktor() {
		return this.doktor;
	}

	public void setDoktor(String doktor) {
		this.doktor = doktor;
	}

	public String getHasta() {
		return this.hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getHastane() {
		return this.hastane;
	}

	public void setHastane(String hastane) {
		this.hastane = hastane;
	}

	public String getRandevucol() {
		return this.randevucol;
	}

	public void setRandevucol(String randevucol) {
		this.randevucol = randevucol;
	}

	public String getTarih() {
		return this.tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

}