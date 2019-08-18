package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hasta database table.
 * 
 */
@Entity
@NamedQuery(name="Hasta.findAll", query="SELECT h FROM Hasta h")
public class Hasta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idhasta;

	private String randevular;

	private String sifre;

	private String tc;

	public Hasta() {
	}

	public int getIdhasta() {
		return this.idhasta;
	}

	public void setIdhasta(int idhasta) {
		this.idhasta = idhasta;
	}

	public String getRandevular() {
		return this.randevular;
	}

	public void setRandevular(String randevular) {
		this.randevular = randevular;
	}

	public String getSifre() {
		return this.sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getTc() {
		return this.tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

}