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
		System.out.println("KONUS ACTÝOOOOOOON");
		return "chat?faces-redirect=true";
	}
	
	private List<String> konusulanKisiler = new ArrayList<String>();

	
	public void konusulanKisiEkle(String id){
		konusulanKisiler.add(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Chat> mesajlariAl(String mId,String ýId){
		List<Chat> mesajlar = new ArrayList<Chat>();
			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
			EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
			Query q = entityManagerObject.createQuery("SELECT c FROM Chat c where c.gönderenId = "+mId +" and c.gidenId="+ýId+" OR c.gönderenId = "+ýId +" and c.gidenId="+mId);
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
