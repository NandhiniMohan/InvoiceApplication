package com.altimetrik.invoice.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class StatusCheck {
	static Connection connection = null;
	static int status = 0;

	public static int doStatusCheck() throws SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
          	Scanner sc = new Scanner(System.in);
          	Statement stmt = connection.createStatement();
          	System.out.println("Enter the number of Invoices to Approve :");
			int noOfInvoices = sc.nextInt();
          	String [] invoiceNumbers = new String[noOfInvoices];
          	String labeltext = "Enter the Invoice Number : ";
          	
          	for (int i = 0; i < noOfInvoices; i++)
            { 
          		if(i != 0) {
          			labeltext = "Enter the Next Invoice Number  : "; 
          		}
          		System.out.println(labeltext);
                invoiceNumbers[i] = sc.next();
                status = stmt.executeUpdate("UPDATE INVOICEDATA SET STATUS = 'APPROVED' WHERE INVOICE_NO = " + invoiceNumbers[i]);

            }
            
   		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return status;
	}
}