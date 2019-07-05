package com.altimetrik.invoice.service.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.mail.Address;

import com.altimetrik.invoice.constants.*;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;




public class InvoiceDB {
	Connection connection = null;
	public void insertInvoiceDate(String invoiceNo,  String invoiceDate, String totalInvoice,  String customerPO, String shipToAddress, String soldToAddress, String remitToAddress, Address From) throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");  
	  
		connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");  
	
		try { 	
	
			PreparedStatement stmt1 =connection.prepareStatement(InvoiceConstants.INSERT_INVOICE_DATA);
			stmt1.setString(1,invoiceNo); 
			stmt1.setString(2,invoiceDate);  
			stmt1.setString(3,customerPO);
			stmt1.setString(4,totalInvoice);
			stmt1.setString(5, From.toString());
			stmt1.setString(6,"NOT APPROVED");
			
			PreparedStatement stmt2 =connection.prepareStatement(InvoiceConstants.INSERT_INVOICE_ADDRESS);  
			stmt2.setString(1,invoiceNo); 
			stmt2.setString(2,soldToAddress);  
			stmt2.setString(3,shipToAddress);
			stmt2.setString(4,remitToAddress);
			
			Statement stmt=connection.createStatement();  
			ResultSet rs = stmt.executeQuery(InvoiceConstants.GET_INVOICE_DATA);  
			
			while(rs.next())  {
				 
				 System.out.println("----------------------------------------------------------------------------------------");
				 System.out.println("Invoice No: " +rs.getString(1));
				 System.out.println("Invoice Date: " +rs.getString(2));
				 System.out.println("Customer PO: " +rs.getString(3));
				 System.out.println("Total Invoice: " +rs.getString(4));
				 
			 }
			stmt1.executeUpdate(); 
			stmt2.executeUpdate();
 
		} catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		
		finally{
			connection.close();
		}		 
	}
}
