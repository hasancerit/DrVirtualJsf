package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the doktor database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Doktor.findAll", query = "SELECT d FROM Doktor d"),
    @NamedQuery(name = "Doktor.findByBolumAndHastane", query = "SELECT d FROM Doktor d WHERE d.hastane = :hastane and d.bolüm = :bolüm"),

    })
public class Doktor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int iddoktor;

	private String bolüm;

	private String hastane;

	private String isim;

	private String randevuIds;

	private String randevular;

	public Doktor() {
	}

	public int getIddoktor() {
		return this.iddoktor;
	}

	public void setIddoktor(int iddoktor) {
		this.iddoktor = iddoktor;
	}

	public String getBolüm() {
		return this.bolüm;
	}

	public void setBolüm(String bolüm) {
		this.bolüm = bolüm;
	}

	public String getHastane() {
		return this.hastane;
	}

	public void setHastane(String hastane) {
		this.hastane = hastane;
	}

	public String getIsim() {
		return this.isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getRandevuIds() {
		return this.randevuIds;
	}

	public void setRandevuIds(String randevuIds) {
		this.randevuIds = randevuIds;
	}

	public String getRandevular() {
		return this.randevular;
	}

	public void setRandevular(String randevular) {
		this.randevular = randevular;
	}

}