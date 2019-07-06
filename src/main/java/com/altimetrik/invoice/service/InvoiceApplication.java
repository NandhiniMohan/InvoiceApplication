package com.altimetrik.invoice.service;

import java.sql.SQLException;

import com.altimetrik.invoice.constants.InvoiceConstants;
import com.altimetrik.invoice.service.dao.UpdateDB_StatusCheck;
import com.altimetrik.invoice.service.mail.ReceiveEmailWithAttachment;
import com.altimetrik.invoice.service.mail.SendEmail;

public class InvoiceApplication {

	public static void main(String[] args) throws SQLException {

		ReceiveEmailWithAttachment receiveEmailAttachement = new ReceiveEmailWithAttachment();
		SendEmail sendEmail = new SendEmail();
		UpdateDB_StatusCheck updateDB_StatusCheck = new UpdateDB_StatusCheck();
		int fileCheckStatus = receiveEmailAttachement.receiveEmail(InvoiceConstants.pop3Host,
				InvoiceConstants.mailStoreType, InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
		if (fileCheckStatus == 0) {
			System.out.println(InvoiceConstants.SET_TEXTFOR_NULLEMAIL);
		} else {
			int status = updateDB_StatusCheck.doStatusCheck();
			if (status >= 0) {
				sendEmail.doApprovalMail(InvoiceConstants.pop3Host, InvoiceConstants.mailStoreType,
						InvoiceConstants.USER_NAME, InvoiceConstants.PASSWORD);
			}
		}

	}
}