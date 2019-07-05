package com.altimetrik.invoice.service;

import java.sql.SQLException;

import com.altimetrik.invoice.constants.InvoiceConstants;
import com.altimetrik.invoice.service.DB.StatusCheck;
import com.altimetrik.invoice.service.mail.ReceiveEmailWithAttachment;
import com.altimetrik.invoice.service.mail.SendEmail;

public class InvoiceApplication {

	public static void main(String[] args) throws SQLException {
		
		int fileCheckStatus = ReceiveEmailWithAttachment.receiveEmail(InvoiceConstants.pop3Host, InvoiceConstants.mailStoreType, InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
		if(fileCheckStatus == 0 )
		{
			System.out.println("No new Mails received!!");
		}
		else
		{
		int status = StatusCheck.doStatusCheck();
		if (status >= 0) {
			SendEmail.doApprovalMail(InvoiceConstants.pop3Host, InvoiceConstants.mailStoreType, InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
		} 
	}

}
}