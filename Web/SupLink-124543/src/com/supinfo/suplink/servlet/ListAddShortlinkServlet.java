package com.supinfo.suplink.servlet;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.dao.ShortlinkDao;
import com.supinfo.suplink.dao.UserDao;
import com.supinfo.suplink.entity.Shortlink;
import com.supinfo.suplink.entity.User;

public class ListAddShortlinkServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
		User user = UserDao.getUserById((Long) req.getSession().getAttribute("userid"));
		if (user != null) {
			if(req.getParameterMap().isEmpty()){
				doGet(req, res);
			}
			else{
				doPost(req, res);
			}
		} else{
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Shortlink shortlink = new Shortlink();
		shortlink.setName(req.getParameter("name"));
		shortlink.setUrl(req.getParameter("url"));
		
		ShortlinkDao.addShortlink((Long) req.getSession().getAttribute("userid"), shortlink);
		
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		List<Shortlink> shortlinks = ShortlinkDao.getAllShortlinks((Long) req.getSession().getAttribute("userid"));
		
		req.setAttribute("shortlinks", shortlinks);
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	public void destroy() {
		emf = null;
	}

}
