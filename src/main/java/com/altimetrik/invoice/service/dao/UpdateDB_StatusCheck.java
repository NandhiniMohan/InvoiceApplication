package com.altimetrik.invoice.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.altimetrik.invoice.constants.InvoiceConstants;

public class UpdateDB_StatusCheck {
	Connection connection = null;
	int status = 0;

	public int doStatusCheck() throws SQLException {
		int i;
		try {
			Class.forName(InvoiceConstants.DRIVER);
			connection = DriverManager.getConnection(InvoiceConstants.URL, InvoiceConstants.USER,
					InvoiceConstants.CONNECTION_PASSWORD);
			Scanner sc = new Scanner(System.in);
			Statement stmt = connection.createStatement();
			System.out.println(InvoiceConstants.GET_NO_OF_INVOICE);
			int noOfInvoices = sc.nextInt();
			String[] invoiceNumbers = new String[noOfInvoices];
			String labeltext = InvoiceConstants.GET_INV_NO;

			for (i = 0; i < noOfInvoices; i++) {
				if (i != 0) {
					labeltext = InvoiceConstants.GET_NEXT_INV_NO;
				}
				System.out.println(labeltext);
				invoiceNumbers[i] = sc.next();
				status = stmt.executeUpdate(
						"UPDATE INVOICEDATA SET STATUS = 'APPROVED' WHERE INVOICE_NO = " + invoiceNumbers[i]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return status;

	}
}