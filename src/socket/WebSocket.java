package socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import controller.Bean;
import model.Chat;

@ServerEndpoint("/ws/{mId}/{ýId}")
public class WebSocket {
	private Session session;
	public static List<Session> sessions  = new ArrayList<Session>();
	private String mId;
	private String ýId;
	
	@OnOpen
	public void connect(@PathParam("mId") String mId,@PathParam("ýId") String ýId,Session session) {
		System.out.println("BAGLANTÝ KURULDU");
		this.mId = mId;
		this.ýId = ýId;
		this.session = session;
		sessions.add(session);
	}

	@OnClose
	public void close() {
		System.out.println("BAGLANTÝ KAPANDI");
		this.session = null;
	}

	@OnMessage
	public void message(Session session, String msg) {
		System.out.println("Client'ten Gelen Mesaj= " + msg);
		
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        entityManagerObject.getTransaction().begin();
        	Chat entity = new Chat();
        	entity.setGidenId(ýId);
        	entity.setGönderenId(mId);
        	entity.setMesaj(msg);
        		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        		Date date = new Date();
        	entity.setTarih(dateFormat.format(date));
        	entityManagerObject.persist(entity);
        	System.out.println("PERSÝST");
        entityManagerObject.getTransaction().commit();
        entityManagerObject.close();
        entityManagerFactoryObject.close();
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getýId() {
		return ýId;
	}

	public void setýId(String iId) {
		ýId = iId;
	}
	
	public static void mesajGonder(String gonderen,String gönderilen,String mesaj) {
		for(Session s:sessions) {
			if(s.isOpen()) {
				if(s.getPathParameters().get("mId").equals(gönderilen) && s.getPathParameters().get("ýId").equals(gonderen)) {
					s.getAsyncRemote().sendText(mesaj);
				}
			}
		}
	}
}
