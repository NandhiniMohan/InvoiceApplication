package com.altimetrik.invoice.service.mail;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import com.altimetrik.invoice.service.pdfparser.FetchPdfData;

public class ReceiveEmailWithAttachment {
	
	static File targetFile;
	static Address From ;
	static Message[] messages;
	
	public static int receiveEmail(String pop3Host, String mailStoreType, String userName, String password) {
		
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true"); 

		Session session = Session.getInstance(props);

		try {
			
			Store store = session.getStore("pop3s");
			store.connect(pop3Host, userName, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			messages = emailFolder.getMessages();
			System.out.println("Total Message : " + messages.length);
			String fileName ="";
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
				System.out.println("---------------------------------");
				System.out.println("Details of Email Message " + (i + 1) + " :");
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				From = message.getFrom()[0];
				System.out.println("To: ");
				for (int j = 0; j < toAddress.length; j++) {
					System.out.println(toAddress[j].toString());
				}
				
				Object mpart = message.getContent();
				if (mpart instanceof Multipart) {
					Multipart multipart = (Multipart) mpart;
					for (int k = 0; k < multipart.getCount(); k++) {
						BodyPart bodyPart = multipart.getBodyPart(k);
						if (bodyPart.getDisposition() != null
								&& bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
							System.out.println("file name " + bodyPart.getFileName());
							System.out.println("size " + bodyPart.getSize());
							System.out.println("content type " + bodyPart.getContentType());
							InputStream stream = (InputStream) bodyPart.getInputStream();
							fileName = bodyPart.getFileName();
							targetFile = new File(
									"D:\\MailAttachment\\" + bodyPart.getFileName());
							java.nio.file.Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						}
					}
				}
			}
			
			if(messages.length ==0)
			{
			  return messages.length;
			}
			emailFolder.close(false);
			store.close();
			FetchPdfData fetchPdfData = new FetchPdfData();
			fetchPdfData.doPdfExtraction(targetFile , From); 

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messages.length;
	}
	
}