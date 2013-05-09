package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.entity.ClickDate;
import com.supinfo.suplink.entity.Country;
import com.supinfo.suplink.entity.Referrer;
import com.supinfo.suplink.entity.Shortlink;
import com.supinfo.suplink.entity.User;

public class InitDbServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
		User user = new User();
		user.setActivated(1);
		user.setEmail("azerty@supinfo.com");
		user.setPassword("de9c3cbac5e22a64b3746708837ddd16");
		
		Shortlink shortlink = new Shortlink();
		shortlink.setCreationdate("08-02-2013");
		shortlink.setEnabled(1);
		shortlink.setName("Supinfo");
		shortlink.setNbclicks(25);
		shortlink.setShorturl("64a6");
		shortlink.setUrl("http://supinfo.com");
		shortlink.setUser(user);
		
		Referrer referrer = new Referrer();
		referrer.setNbclicks((long) 12);
		referrer.setReferrer("Direct");
		referrer.setShortlink(shortlink);
		
		Referrer referrer2 = new Referrer();
		referrer2.setNbclicks((long) 7);
		referrer2.setReferrer("www.facebook.com");
		referrer2.setShortlink(shortlink);

		Referrer referrer3 = new Referrer();
		referrer3.setNbclicks((long) 6);
		referrer3.setReferrer("twitter.com");
		referrer3.setShortlink(shortlink);

		Country country = new Country();
		country.setCountry("Canada");
		country.setNbclicks((long) 17);
		country.setShortlink(shortlink);
		
		Country country2 = new Country();
		country2.setCountry("France");
		country2.setNbclicks((long) 8);
		country2.setShortlink(shortlink);

		ClickDate clickdate = new ClickDate();
		clickdate.setDate("08-02-2013");
		clickdate.setNbclicks((long) 2);
		clickdate.setShortlink(shortlink);
		
		ClickDate clickdate2 = new ClickDate();
		clickdate2.setDate("09-02-2013");
		clickdate2.setNbclicks((long) 5);
		clickdate2.setShortlink(shortlink);
		
		ClickDate clickdate3 = new ClickDate();
		clickdate3.setDate("10-02-2013");
		clickdate3.setNbclicks((long) 18);
		clickdate3.setShortlink(shortlink);
		
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.persist(shortlink);
		em.persist(referrer);
		em.persist(referrer2);
		em.persist(referrer3);
		em.persist(country);
		em.persist(country2);
		em.persist(clickdate);
		em.persist(clickdate2);
		em.persist(clickdate3);
		em.getTransaction().commit();
		em.close();
		
		res.sendRedirect(req.getContextPath() + "/index");
	}
	
	@Override
	public void destroy() {
		emf.close();
	}

}