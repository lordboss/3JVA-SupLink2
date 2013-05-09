package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.dao.ShortlinkDao;
import com.supinfo.suplink.entity.Shortlink;

public class ShortlinkServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");
        String[] uriList = uri.split("/");
        String thisUri = (String) uriList[uriList.length-1];

        try {
			        	
        	Shortlink shortlink = ShortlinkDao.goShortlink(thisUri, req);
			
			res.sendRedirect(shortlink.getUrl());
		
        } catch (NoResultException e) {
			res.sendRedirect(req.getContextPath() + "/index");
		}
		
	}
	
	@Override
	public void destroy() {
		emf.close();
	}

}