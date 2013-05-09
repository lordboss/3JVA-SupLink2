package com.supinfo.suplink.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import net.sf.javainetlocator.InetAddressLocator;
import net.sf.javainetlocator.InetAddressLocatorException;

import com.supinfo.suplink.entity.ClickDate;
import com.supinfo.suplink.entity.Country;
import com.supinfo.suplink.entity.Referrer;
import com.supinfo.suplink.entity.Shortlink;
import com.supinfo.suplink.entity.Statistics;
import com.supinfo.suplink.entity.User;
import com.supinfo.suplink.function.GenerateId;

public class ShortlinkDao {
	private static EntityManagerFactory emf;
	
	public ShortlinkDao() {}
	
	public static List<Shortlink> getAllShortlinks(Long userid) {
		emf = Persistence.createEntityManagerFactory("PU");

		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT s FROM Shortlink AS s, User AS u WHERE s.user = u AND u.id = :id ORDER BY s.id DESC");
		query.setParameter("id", userid);
		
		@SuppressWarnings("unchecked")
		List<Shortlink> shortlinks = query.getResultList();
		
		em.close();
		
		emf.close();

		return shortlinks;		
	}
	
	public static Statistics getShortlink(String shorturl) {
		emf = Persistence.createEntityManagerFactory("PU");
	
		EntityManager em = emf.createEntityManager();
		
		Query querySelectShortlink = em.createQuery("SELECT s FROM Shortlink AS s WHERE shorturl = :shorturl");
		querySelectShortlink.setParameter("shorturl", shorturl);
		Shortlink shortlink = (Shortlink) querySelectShortlink.getSingleResult();
	
		Query querySelectClickDate = em.createQuery("SELECT c FROM ClickDate AS c WHERE c.shortlink = :shortlink");
		querySelectClickDate.setParameter("shortlink", shortlink);
		@SuppressWarnings("unchecked")
		List<ClickDate> clickdate = querySelectClickDate.getResultList();
	
		Query querySelectCountry = em.createQuery("SELECT c FROM Country AS c WHERE c.shortlink = :shortlink");
		querySelectCountry.setParameter("shortlink", shortlink);
		@SuppressWarnings("unchecked")
		List<Country> country = querySelectCountry.getResultList();
	
		Query querySelectReferrer = em.createQuery("SELECT r FROM Referrer AS r WHERE r.shortlink = :shortlink");
		querySelectReferrer.setParameter("shortlink", shortlink);
		@SuppressWarnings("unchecked")
		List<Referrer> referrer = querySelectReferrer.getResultList();
		
		Statistics statistics = new Statistics();
		statistics.setShortlink(shortlink);
		statistics.setClickdate(clickdate);
		statistics.setCountry(country);
		statistics.setReferrer(referrer);
		
		em.close();
		emf.close();
	
		return statistics;
	}

	public static Shortlink goShortlink(String shorturl, HttpServletRequest req){

		emf = Persistence.createEntityManagerFactory("PU");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query querySelect = em.createQuery("SELECT s FROM Shortlink AS s WHERE shorturl = :shorturl AND enabled = 1");
		querySelect.setParameter("shorturl", shorturl);
		Shortlink shortlink = (Shortlink) querySelect.getSingleResult();
		shortlink.setNbclicks(shortlink.getNbclicks()+1);
		

		ClickDate clickdate = new ClickDate();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		try {
			Query querySelectClickDate = em.createQuery("SELECT c FROM ClickDate AS c WHERE c.date = :date AND c.shortlink = :shortlink");
			querySelectClickDate.setParameter("date", sdf.format(date));
			querySelectClickDate.setParameter("shortlink", shortlink);
			clickdate = (ClickDate) querySelectClickDate.getSingleResult();
			clickdate.setNbclicks(clickdate.getNbclicks()+1);
		} catch (NoResultException e) {
			clickdate.setDate(sdf.format(date));
			clickdate.setNbclicks((long) 1);
			clickdate.setShortlink(shortlink);
		}
		
		Country country = new Country();
		
		String cntry;
		try {
			cntry = InetAddressLocator.getLocale(req.getRemoteAddr()).getDisplayCountry();
		} catch (InetAddressLocatorException e1) {
			cntry = "LocalLand";
			e1.printStackTrace();
		}
		
		if (cntry == "**") {
			cntry = "LocalLand";
		}
		
		try {
			Query querySelectCountry = em.createQuery("SELECT c FROM Country AS c WHERE c.country = :country AND c.shortlink = :shortlink");
			querySelectCountry.setParameter("country", cntry);
			querySelectCountry.setParameter("shortlink", shortlink);
			country = (Country) querySelectCountry.getSingleResult();
			country.setNbclicks(country.getNbclicks()+1);
		}
		catch (NoResultException e) {
			country.setCountry(cntry);
			country.setNbclicks((long) 1);
			country.setShortlink(shortlink);
		}
		
		
		Referrer referrer = new Referrer();
		
		String ref = null;
		if (req.getHeader("referer") == null) {
			ref = "Direct";
		}
		else{
			ref = req.getHeader("referer");
	        String[] refList = ref.split("/");
	        ref = (String) refList[2];
		}

        if (ref == "localhost:8080"){
			ref = "Direct";
        }

		try {
			Query querySelectReferrer = em.createQuery("SELECT r FROM Referrer AS r WHERE r.referrer = :referrer AND r.shortlink = :shortlink");
			querySelectReferrer.setParameter("referrer", ref);
			querySelectReferrer.setParameter("shortlink", shortlink);
			referrer = (Referrer) querySelectReferrer.getSingleResult();
			referrer.setNbclicks(referrer.getNbclicks()+1);
		} catch (NoResultException e) {
			referrer.setReferrer(ref);
			referrer.setNbclicks((long) 1);
			referrer.setShortlink(shortlink);
		}
		
		
		em.persist(shortlink);
		em.persist(clickdate);
		em.persist(referrer);
		em.persist(country);
		em.getTransaction().commit();
		em.close();
		
		emf.close();

		return shortlink;
	}
	
	public static Shortlink addShortlink(Long userid, Shortlink shortlink){
		
		emf = Persistence.createEntityManagerFactory("PU");

		shortlink.setShorturl(GenerateId.getId(String.valueOf(System.currentTimeMillis())));
		shortlink.setNbclicks(0);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		shortlink.setCreationdate(sdf.format(date));
		
		User user = UserDao.getUserById((Long) userid);
		
		shortlink.setUser(user);
		shortlink.setEnabled(1);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(shortlink);
		em.getTransaction().commit();
		em.close();
		
		emf.close();

		return shortlink;
	}
	
	public static void modShortlink(Long userid, Long shortlinkid, Shortlink shortlink){
		emf = Persistence.createEntityManagerFactory("PU");

		User user = UserDao.getUserById((Long) userid);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("UPDATE Shortlink AS s SET s.enabled = :enabled WHERE id = :id AND user_id = :user");		
		query.setParameter("enabled", shortlink.getEnabled());
		query.setParameter("id", shortlinkid);
		query.setParameter("user", user.getId());
		query.executeUpdate();

		em.getTransaction().commit();
		em.close();
		
		emf.close();
		
		return;
	}
	
	public static void delShortlink(Long userid, Long shortlinkid){
		emf = Persistence.createEntityManagerFactory("PU");

		User user = new User();
		user = UserDao.getUserById((Long) userid);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query1 = em.createQuery("DELETE FROM ClickDate AS s WHERE shortlink_id = :id");
		query1.setParameter("id", Long.valueOf(shortlinkid));
		query1.executeUpdate();
		
		Query query2 = em.createQuery("DELETE FROM Country AS c WHERE shortlink_id = :id");
		query2.setParameter("id", Long.valueOf(shortlinkid));
		query2.executeUpdate();
		
		Query query3 = em.createQuery("DELETE FROM Referrer AS r WHERE shortlink_id = :id");
		query3.setParameter("id", Long.valueOf(shortlinkid));
		query3.executeUpdate();
		
		Query query4 = em.createQuery("DELETE FROM Shortlink AS s WHERE id = :id AND user_id = :user");
		query4.setParameter("id", Long.valueOf(shortlinkid));
		query4.setParameter("user", user.getId());
		query4.executeUpdate();
		em.getTransaction().commit();
		em.close();
		
		emf.close();
		
		return;
	}
}
