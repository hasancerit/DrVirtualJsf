package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Chat;

@ManagedBean(name = "chatController", eager = true)
@SessionScoped
public class ChatController {
	public String konusAction(){
		System.out.println("KONUS ACT�OOOOOOON");
		return "chat?faces-redirect=true";
	}
	
	private List<String> konusulanKisiler = new ArrayList<String>();

	
	public void konusulanKisiEkle(String id){
		konusulanKisiler.add(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Chat> mesajlariAl(String mId,String �Id){
		List<Chat> mesajlar = new ArrayList<Chat>();
			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
			EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
			Query q = entityManagerObject.createQuery("SELECT c FROM Chat c where c.g�nderenId = "+mId +" and c.gidenId="+�Id+" OR c.g�nderenId = "+�Id +" and c.gidenId="+mId);
			mesajlar = q.getResultList();
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
		return mesajlar;
	}

	public List<String> getKonusulanKisiler() {
		return konusulanKisiler;
	}

	public void setKonusulanKisiler(List<String> konusulanKisiler) {
		this.konusulanKisiler = konusulanKisiler;
	}	
}
