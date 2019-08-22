package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chat database table.
 * 
 */
@Entity
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
@EntityListeners(ChatChangeListener.class)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idchat;

	private String gidenId;

	private String g�nderenId;

	private String mesaj;

	private String tarih;

	public Chat() {
	}

	public int getIdchat() {
		return this.idchat;
	}

	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}

	public String getGidenId() {
		return this.gidenId;
	}

	public void setGidenId(String gidenId) {
		this.gidenId = gidenId;
	}

	public String getG�nderenId() {
		return this.g�nderenId;
	}

	public void setG�nderenId(String g�nderenId) {
		this.g�nderenId = g�nderenId;
	}

	public String getMesaj() {
		return this.mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	public String getTarih() {
		return this.tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

}