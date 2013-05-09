package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.dao.ShortlinkDao;
import com.supinfo.suplink.entity.Shortlink;

public class ModStatusShortlinkServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Shortlink shortlink = new Shortlink();
		shortlink.setEnabled(Integer.valueOf(req.getParameter("shortlinkaction")));
		
		ShortlinkDao.modShortlink((Long) req.getSession().getAttribute("userid"), Long.valueOf(req.getParameter("shortlinkid")), shortlink);
		
		resp.sendRedirect(req.getContextPath() + "/index");
	}

	@Override
	public void destroy() {
		emf.close();
	}
}
