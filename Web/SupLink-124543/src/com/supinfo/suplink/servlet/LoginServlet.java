package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.dao.UserDao;
import com.supinfo.suplink.entity.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		User user = UserDao.getUserById((Long) req.getSession().getAttribute("userid"));
		if (user != null) {
			res.sendRedirect(req.getContextPath() + "/index");
		} else {
			if(req.getParameterMap().isEmpty()){
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, res);
			}
			else{
				doPost(req, res);
			}	
		}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
						
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			
			User thisUser = UserDao.getUserByAuth(user);
			if (thisUser != null){
				req.getSession().setAttribute("userid", thisUser.getId());
			}
			res.sendRedirect(req.getContextPath() + "/index");
		
		} catch (NoResultException e) {
			res.sendRedirect(req.getContextPath() + "/index");
		}
		
	}
	
	@Override
	public void destroy() {
		emf = null;
	}

}
