package com.altimetrik.invoice.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.Address;
import com.altimetrik.invoice.constants.*;

public class InvoiceDB {
	Connection connection = null;

	public void insertInvoiceData(String invoiceNo, String invoiceDate, String totalInvoice, String customerPO,
			String shipToAddress, String soldToAddress, String remitToAddress, Address From)
			throws ClassNotFoundException, SQLException {
		Class.forName(InvoiceConstants.DRIVER);

		connection = DriverManager.getConnection(InvoiceConstants.URL, InvoiceConstants.USER,
				InvoiceConstants.CONNECTION_PASSWORD);

		try {

			PreparedStatement preparedStatement_Invoice = connection
					.prepareStatement(InvoiceConstants.INSERT_INVOICE_DATA);
			preparedStatement_Invoice.setString(1, invoiceNo);
			preparedStatement_Invoice.setString(2, invoiceDate);
			preparedStatement_Invoice.setString(3, customerPO);
			preparedStatement_Invoice.setString(4, totalInvoice);
			preparedStatement_Invoice.setString(5, From.toString());
			preparedStatement_Invoice.setString(6, "NOT APPROVED");
			preparedStatement_Invoice.executeUpdate();

			PreparedStatement preparedStatement_Address = connection
					.prepareStatement(InvoiceConstants.INSERT_INVOICE_ADDRESS);
			preparedStatement_Address.setString(1, invoiceNo);
			preparedStatement_Address.setString(2, soldToAddress);
			preparedStatement_Address.setString(3, shipToAddress);
			preparedStatement_Address.setString(4, remitToAddress);
			preparedStatement_Address.executeUpdate();

			fetchInvoiceData(invoiceNo);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		finally {
			connection.close();
		}
	}

	public void fetchInvoiceData(String invoiceNo) throws ClassNotFoundException, SQLException {
		Class.forName(InvoiceConstants.DRIVER);

		connection = DriverManager.getConnection(InvoiceConstants.URL, InvoiceConstants.USER,
				InvoiceConstants.CONNECTION_PASSWORD);

		try {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(InvoiceConstants.GET_INVOICE_DATA);

			while (rs.next()) {

				System.out.println(
						"----------------------------------------------------------------------------------------");
				System.out.println("Invoice No: " + rs.getString(1));
				System.out.println("Invoice Date: " + rs.getString(2));
				System.out.println("Customer PO: " + rs.getString(3));
				System.out.println("Total Invoice: " + rs.getString(4));
				System.out.println("Ship_To_Address: " + rs.getString(5));
				System.out.println("Sold_To_Address: " + rs.getString(6));

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		finally {
			connection.close();
		}
	}
}
