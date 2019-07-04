package com.altimetrik.invoice.service;

import java.sql.SQLException;

import com.altimetrik.invoice.constants.InvoiceConstants;

public class InvoiceApplication {

	public static void main(String[] args) throws SQLException {
		
		ReceiveEmailWithAttachment.receiveEmail(InvoiceConstants.pop3Host, InvoiceConstants.mailStoreType, InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
		int status = StatusCheck.doStatusCheck();
		if (status >= 0) {
			SendEmail.doApprovalMail(InvoiceConstants.pop3Host, InvoiceConstants.mailStoreType, InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
		} 
	}

}