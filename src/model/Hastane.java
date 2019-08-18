package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hastane database table.
 * 
 */
@Entity
@NamedQuery(name="Hastane.findAll", query="SELECT h FROM Hastane h")
public class Hastane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idhastane;

	private String bolumler;

	private String hastaneadi;

	public Hastane() {
	}

	public int getIdhastane() {
		return this.idhastane;
	}

	public void setIdhastane(int idhastane) {
		this.idhastane = idhastane;
	}

	public String getBolumler() {
		return this.bolumler;
	}

	public void setBolumler(String bolumler) {
		this.bolumler = bolumler;
	}

	public String getHastaneadi() {
		return this.hastaneadi;
	}

	public void setHastaneadi(String hastaneadi) {
		this.hastaneadi = hastaneadi;
	}

}