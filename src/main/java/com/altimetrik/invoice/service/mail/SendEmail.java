package com.altimetrik.invoice.service.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.altimetrik.invoice.constants.InvoiceConstants;

public class SendEmail {

	public void doApprovalMail(String pop3Host, String mailStoreType, String userName, String password) {
		String to = ReceiveEmailWithAttachment.From.toString();
		String from = userName;
		String pwd = password;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pwd);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(InvoiceConstants.SET_MAIL_SUBJECT);
			message.setText(InvoiceConstants.SET_MAIL_TEXT);

			Transport.send(message);
			System.out.println(InvoiceConstants.SET_CONSOLE_TEXT);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}