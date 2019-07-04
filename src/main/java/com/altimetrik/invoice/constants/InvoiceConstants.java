package com.altimetrik.invoice.constants;

public class InvoiceConstants {

	public static final String USER_NAME = "nandhinimohan04081997@gmail.com";
	public static final String PASSWORD = "InfinityMode"; 
	public static final String pop3Host = "pop.gmail.com";
	public static final String mailStoreType = "pop3";
    public static final String GET_INVOICE_DATA = "SELECT * FROM INVOICEDATA INNER JOIN INVOICEADDRESS ON INVOICEDATA.INVOICE_NO = INVOICEADDRESS.INVOICE_NO";

    public static final String INSERT_INVOICE_DATA = "INSERT INTO INVOICEDATA VALUES(?,?,?,?,?,?)";
    public static final String INSERT_INVOICE_ADDRESS = "INSERT INTO INVOICEADDRESS VALUES(?,?,?,?)";
   
    public static final String SET_MAIL_SUBJECT = "Regarding Status Approval";
    
    public static final String SET_MAIL_TEXT = "Invoice Status Approved Successfully . ";
    
    
   }

