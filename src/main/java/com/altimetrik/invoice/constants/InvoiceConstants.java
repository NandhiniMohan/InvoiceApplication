package com.altimetrik.invoice.constants;

public class InvoiceConstants {

	public static final String USER_NAME = "nandhinimohan04081997@gmail.com";
	public static final String PASSWORD = "InfinityMode";
	public static final String pop3Host = "pop.gmail.com";
	public static final String mailStoreType = "pop3";

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "hr";
	public static final String CONNECTION_PASSWORD = "hr";
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	public static final String GET_INVOICE_DATA = "SELECT INVOICEDATA.INVOICE_NO,INVOICEDATA.INVOICE_DATE,INVOICEDATA.CUSTOMER_PO, INVOICEDATA.TOTAL_INVOICE,INVOICEADDRESS.SHIP_TO, INVOICEADDRESS.SOLD_TO FROM INVOICEDATA INNER JOIN INVOICEADDRESS ON INVOICEDATA.INVOICE_NO = INVOICEADDRESS.INVOICE_NO";
	public static final String INSERT_INVOICE_DATA = "INSERT INTO INVOICEDATA VALUES(?,?,?,?,?,?)";
	public static final String INSERT_INVOICE_ADDRESS = "INSERT INTO INVOICEADDRESS VALUES(?,?,?,?)";

	public static final String SET_MAIL_SUBJECT = "Regarding Status Approval";
	public static final String SET_MAIL_TEXT = "Invoice Status Approved Successfully . ";
	public static final String SET_CONSOLE_TEXT = "Approval Mail sent successfully....";
	public static final String SET_TEXTFOR_NULLEMAIL = "No new Mails received!!";
	public static final String GET_NO_OF_INVOICE = "Enter the no of Invoices to Approve : ";
	public static final String GET_INV_NO = "Enter the Invoice Number : ";
	public static final String GET_NEXT_INV_NO = "Enter the Next Invoice Number  : ";
}
