package com.altimetrik.invoice.service.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.Address;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.altimetrik.invoice.service.dao.InvoiceDB;

public class TestInvoiceDB {

	private static final Address From = null;
	@Mock
	public Connection connection;
	@Mock
	public InvoiceDB invoiceDataBase;
	@Mock
	public Statement statement;
	@Mock
	public ResultSet resultSet;

	@Before
	public void setBefore() throws Exception {
		invoiceDataBase = new InvoiceDB();
	}

	@Test(expected = Exception.class)
	public void displayInvoiceDetailsTest() throws SQLException, ClassNotFoundException {
		Mockito.doReturn(statement).when(connection.createStatement());
		Mockito.doReturn(resultSet).when(statement.executeQuery(Mockito.anyString()));
		Mockito.doReturn(false).doReturn(true).when(resultSet.next());
		Mockito.doReturn(Mockito.anyString()).doReturn(Mockito.anyString()).doReturn(Mockito.anyString()).doReturn(Mockito.anyString()).doReturn(Mockito.anyString())
				.doReturn(Mockito.anyString()).doReturn(Mockito.anyString()).when(resultSet.getString(Mockito.anyInt()));
		invoiceDataBase.insertInvoiceData("123456", "12/08/2019", "123.23", "12334565678", "shipToAddress",
				"soldToAddress", "remitToAddress", From);
	}

}
