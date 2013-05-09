package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.dao.UserDao;
import com.supinfo.suplink.entity.User;
import com.supinfo.suplink.function.MD5;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	private EntityManagerFactory emf;
	
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
				RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
				rd.forward(req, res);
			}
			else{
				doPost(req, res);
			}	
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		User user = new User();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		Integer activated = 1;

		if (!(password.equals(confirmpassword))) {
			res.sendRedirect("register.html");
			return;
		}
		
		user.setEmail(email);
		user.setPassword(MD5.getHash(password));
		user.setActivated(activated);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
		req.setAttribute("mail", email);
		RequestDispatcher rd = req.getRequestDispatcher("/sent");
		rd.forward(req, res);
		
	}
	
	@Override
	public void destroy() {
		emf = null;
	}

}
