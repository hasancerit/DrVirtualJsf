package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Daos.DoktorDao;
import model.Doktor;
import model.Hastane;

@ManagedBean(name = "bean", eager = true)
@RequestScoped
public class Bean {
	/*public void test() {
		System.out.println("calisti");
	}*/

	
	/*public String getSayWelcome(){
		   //check if null?
		   if("".equals(name) || name ==null){
			return "";
		   }else{
			return "Ajax message : Welcome " + name;
		   }
		}
*/
	//private int id;
/*
	
	private String hastaneadi = "Hastane Secin";
	private Map<String, Object> hastaneler = new LinkedHashMap<String, Object>();
	
	private String bolumAdi = "Bolum Secin";
	private Map<String, Object> bolumler = new LinkedHashMap<String, Object>();
	
	private String doktorAdi = "1";
	private Map<String, Object> doktorlar = new LinkedHashMap<String, Object>();
	
	private String saat = "1";
	private Map<String, Object> saatler = new LinkedHashMap<String, Object>();
	
	
	
	public Map<String, Object> getHastaneler() {
		return hastaneler;
	}

	public void setHastaneler(Map<String, Object> hastaneler) {
		this.hastaneler = hastaneler;
	}

	public Map<String, Object> getBolumler() {
		return bolumler;
	}

	public void setBolumler(Map<String, Object> bolumler) {
		this.bolumler = bolumler;
	}*/

	private String tc,sifre,name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String girisdene(){
		  Connection conn = null;
		  PreparedStatement preparedStatement = null;
		  		  
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtual?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey","root","1234");
			preparedStatement = conn.prepareStatement("select tc,sifre from hasta where tc=? and sifre=?");
			preparedStatement.setString(1, tc);
			preparedStatement.setString(2, sifre);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				return "hastanasayfa";
			}else {
				return "hastagiris";
			}
		  }catch (SQLException e) {
			e.printStackTrace();
			return "hastagiris";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "hastagiris";
		}finally {
			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*public String hastaneGetir() {
		EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
        
        TypedQuery<Hastane> personQuery=entityManagerObject.createQuery("SELECT h FROM Hastane h",Hastane.class);
        List<Hastane> hospitalList=personQuery.getResultList();
        
        for(Hastane h:hospitalList)
        {
        	hastaneler.put(h.getHastaneadi()+" Hastanesi",h.getHastaneadi());
        }
 
        entityManagerObject.close();
        entityManagerFactoryObject.close();

		return "randevual?faces-redirect=true";
	}
	*/
	/*public void bolumGetir() {
		  bolumler.clear();
		  doktorlar.clear();
		  saatler.clear();
		  
		  bolumAdi="";
		  doktorAdi="";
		  saat="";
		  
		  secildi=false;
		  
			EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
	        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
	        
	        Hastane h =entityManagerObject.find(Hastane.class,Integer.parseInt(hastaneadi));
	        if(h.getBolumler().contains("1")) {
				bolumler.put("Kulak","1");
	        }if(h.getBolumler().contains("2")) {
				bolumler.put("Burun","2");
	        }if(h.getBolumler().contains("3")) {
				bolumler.put("Seks","3");
	        }if(h.getBolumler().contains("4")) {
				bolumler.put("Ciðer","4");
	        }if(h.getBolumler().contains("5")) {
				bolumler.put("Dalak","5");
	        }
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
	}*/
	
	/*@ManagedProperty(value = "#{doktorDao}")
	DoktorDao doktorDao;
	
	public void doktorGetir() {		
			 try {
				  doktorlar.clear();
				  saatler.clear();
				  
				  saat="";
				  doktorAdi="";
				  
				  secildi = false;
			 }catch (Exception e1) {
				 return;
			 }
		        List<Doktor> doktorList=doktorDao.get("SELECT d FROM Doktor d "
		        		+ "WHERE d.hastane="+hastaneadi+" AND d.bolüm="+bolumAdi);
		        for(Doktor d:doktorList){
		        	doktorlar.put(d.getIsim(),d.getIddoktor());
		        }
	 }*/
	
	/*String saatlerS;*/
	//private boolean secildi = false;
	
	/*public void saatGetir() {
		 try {
			  saatler.clear();
			  saat = "";
			  secildi = false;
		 }catch (Exception e1) {
			 return;
		 }
			
		 EntityManagerFactory entityManagerFactoryObject=Persistence.createEntityManagerFactory("DrVirual");
	        EntityManager entityManagerObject=entityManagerFactoryObject.createEntityManager();
	        
	         Doktor d =entityManagerObject.find(Doktor.class,Integer.parseInt(doktorAdi));
	         saatlerS = d.getRandevular();
			
	         ArrayList<String> randevuList = null;
	         randevuList = parseSaat(saatlerS);

			for(int i = 8 ; i <= 18 ; i++) {
				boolean yaz = true;
				for (int j = 0; j < randevuList.size(); j++) {
					if((""+i).equalsIgnoreCase(randevuList.get(j))) {
						yaz = false;
						break;
					}
				}
				if(yaz) 
					saatler.put(i+":00",i+"");
			}
	         
	        entityManagerObject.close();
	        entityManagerFactoryObject.close();
}*/
	
	/*private ArrayList<String> parseSaat(String saatlerS){
		System.out.println("GELEN:"+saatlerS);
		ArrayList<String> saatler = new ArrayList<String>();
		String saat="";
		for(int i = 0 ; i < saatlerS.length() ; i++) {
			if(saatlerS.charAt(i) == ',') {
				saatler.add(saat);
				saat = "";
			}else if(i == saatlerS.length()-1) {
				saat += saatlerS.charAt(i);
				saatler.add(saat);
			}else {
				saat += saatlerS.charAt(i);
			}
		}
		return saatler;
	}*/
	
	/*public void randevuAl() {			
		  Connection conn = null;
		  PreparedStatement preparedStatement = null;
		  		  
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtual?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey","root","1234");
			preparedStatement = conn.prepareStatement("update doktor set randevular = ? where iddoktor = ?");
			preparedStatement.setString(1, saatlerS+","+saat);
			preparedStatement.setInt(2, Integer.parseInt(doktorAdi));
			preparedStatement.executeUpdate();
			
		  }catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally {
			try {
				bolumler.clear();
				doktorlar.clear();
				saatler.clear();
				conn.close();
				preparedStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}*/
	
	/*public void saatSec() {
		setSecildi(true);
	}
	
	public String info() {
		 return hastaneadi+" Hastanesinden, " +bolumAdi+" Bölümünden, " +doktorAdi+" Doktorundan, " +saat+" Saatine Randevu Alýyorsunuz.";
	}*//*
	
	
	public String getHastaneadi() {
		return hastaneadi;
	}
	public void setHastaneadi(String hastaneadi) {
		this.hastaneadi = hastaneadi;
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

	public Map<String, Object> getDoktorlar() {
		return doktorlar;
	}

	public void setDoktorlar(Map<String, Object> doktorlar) {
		this.doktorlar = doktorlar;
	}

	public String getSaat() {
		return saat;
	}

	public void setSaat(String saat) {
		this.saat = saat;
	}

	public Map<String, Object> getSaatler() {
		return saatler;
	}

	public void setSaatler(Map<String, Object> saatler) {
		this.saatler = saatler;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public String getSoyisim() {
		return soyisim;
	}

	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	private String isim,soyisim;
	
	public void ekle() {
		System.out.println(isim +" "+soyisim);
	}

	public boolean isSecildi() {
		return secildi;
	}

	public void setSecildi(boolean secildi) {
		this.secildi = secildi;
	}
	*/
	
}
