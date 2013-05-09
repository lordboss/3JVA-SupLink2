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

import com.supinfo.suplink.dao.ShortlinkDao;
import com.supinfo.suplink.entity.Statistics;

public class ShortlinkDetailsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("PU");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
        String uri = (String) req.getRequestURL().toString();
        String[] uriList = uri.split("/");
        String thisUri = (String) uriList[uriList.length-1];
        
        try {
			
        	Statistics statistics = ShortlinkDao.getShortlink(thisUri);
        	
			req.setAttribute("shortlink", statistics.getShortlink());
			req.setAttribute("clickdate", statistics.getClickdate());
			req.setAttribute("country", statistics.getCountry());
			req.setAttribute("referrer", statistics.getReferrer());
			RequestDispatcher rd = req.getRequestDispatcher("/link.jsp");
			rd.forward(req, res);
		
        } catch (NoResultException e) {
			res.sendRedirect(req.getContextPath() + "/index");
		}
		
	}
	
	@Override
	public void destroy() {
		emf.close();
	}

}