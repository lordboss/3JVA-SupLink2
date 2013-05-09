package com.supinfo.suplink.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.suplink.function.Mail;

public class SendMailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String recipientAddress = (String) req.getAttribute("mail");
		Integer mailmess = Mail.SendMail(recipientAddress);
		
		String mailmsg = null;
		if (mailmess == 0) {
			mailmsg = "(It seems you haven't a mail localhost server. Maybe you will not receive any message.)";
		}
		req.setAttribute("mail", recipientAddress);
		req.setAttribute("mailmsg", mailmsg);
		RequestDispatcher rd = req.getRequestDispatcher("/sent.jsp");
		rd.forward(req, res);
	}
}
