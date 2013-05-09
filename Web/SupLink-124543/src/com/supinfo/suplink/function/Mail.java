package com.supinfo.suplink.function;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;
 
import javax.mail.internet.AddressException;
import javax.mail.NoSuchProviderException;
import javax.mail.MessagingException;


public class Mail {
	public static int SendMail(String recipientAddress){
		try {
		    Properties		props	    = new Properties();
		    props.setProperty("mail.from", "124543@supinfo.com");
		    Session		session	    = Session.getInstance(props);
	 
		    Message		message	    = new MimeMessage(session);
		    InternetAddress	recipient   = new InternetAddress(recipientAddress);
		    message.setRecipient(Message.RecipientType.TO, recipient);
		    message.setSubject("Welcome on Suplink.com");
		    message.setText("You registered a new Suplink.com account ! Welcome !");
	 
		    Transport.send(message);
		}
		catch(NoSuchProviderException e) {
		    System.err.println("Pas de transport disponible pour ce protocole");
		    System.err.println(e);
			return 0;
		}
		catch(AddressException e) {
		    System.err.println("Adresse invalide");
		    System.err.println(e);
			return 0;
		}
		catch(MessagingException e) {
		    System.err.println("Erreur dans le message");
		    System.err.println(e);
			return 0;
		}
		return 1;
    }
}