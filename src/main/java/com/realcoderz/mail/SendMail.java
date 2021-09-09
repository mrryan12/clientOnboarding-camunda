package com.realcoderz.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.context.Context;


public class SendMail {
	private static final String HOST = "smtp.gmail.com";
	private static final String USER = "arvind7238realcoderz@gmail.com";
	private static final String PWD = "Realcoderz7238@";
	private final static Logger LOGGER = Logger.getLogger(SendMail.class.getName());


	public static void sendEmail(DelegateExecution delegateTask,String email,String interview) {
		if(email!=null) {
			String taskId = delegateTask.getId();
			IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USER,PWD);
				}
			});


			try {

				Transport transport = session.getTransport();
				InternetAddress addressFrom = new InternetAddress(USER);

				MimeMessage message = new MimeMessage(session);
				message.setSender(addressFrom);
				message.setSubject("Task assigned: " + interview);
				if(interview.equalsIgnoreCase("interview"))
					message.setContent("Please complete: http://localhost:8083/Recruiter.html?taskId=" + taskId+ "&callbackUrl=http://localhost:8083/camunda/app/tasklist/default/#/","text/plain");
				else if(interview.equalsIgnoreCase("jobprofile"))	
					message.setContent("Please complete: http://localhost:8083/RecruiterManager-Dashboard.html?taskId=" + taskId+ "&callbackUrl=http://localhost:8083/camunda/app/tasklist/default/#/","text/plain");
				else
					System.out.println("task is not found");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				transport.connect();
				Transport.send(message);
				transport.close();
				LOGGER.info("Task Assignment Email successfully sent to user '" + email + "' with address '" + email + "'.");

			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "Could not send email to email", e);
			}
			//} else {
			//	LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
			//}
		} else {
			LOGGER.warning("Not sending email to user " + email + "', user is not enrolled with identity service.");
		}



		
	}


	public static void sendEmail(DelegateTask delegateTask,String email,String interview) {
		if(email!=null) {
			String taskId = delegateTask.getId();
			IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(USER,PWD);
				}
			});


			try {

				Transport transport = session.getTransport();
				InternetAddress addressFrom = new InternetAddress(USER);

				MimeMessage message = new MimeMessage(session);
				message.setSender(addressFrom);
				message.setSubject("Task assigned: " + interview);
				if(interview.equalsIgnoreCase("interview"))
					message.setContent("Please complete: http://localhost:8083/Recruiter.html?taskId=" + taskId+ "&callbackUrl=http://localhost:8083/camunda/app/tasklist/default/#/","text/plain");
				else if(interview.equalsIgnoreCase("jobprofile"))	
					message.setContent("Please complete: http://localhost:8083/RecruiterManager-Dashboard.html?taskId=" + taskId+ "&callbackUrl=http://localhost:8083/camunda/app/tasklist/default/#/","text/plain");
				else
					System.out.println("task is not found");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				transport.connect();
				Transport.send(message);
				transport.close();
				LOGGER.info("Task Assignment Email successfully sent to user '" + email + "' with address '" + email + "'.");

			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "Could not send email to email", e);
			}
			//} else {
			//	LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
			//}
		} else {
			LOGGER.warning("Not sending email to user " + email + "', user is not enrolled with identity service.");
		}



		
	}


}
