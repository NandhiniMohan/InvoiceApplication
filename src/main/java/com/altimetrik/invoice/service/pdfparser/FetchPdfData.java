package com.altimetrik.invoice.service.pdfparser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.altimetrik.invoice.service.dao.InvoiceDB;

import java.io.File;
import java.io.IOException;

import javax.mail.Address;

public class FetchPdfData {

	InvoiceDB invoiceDb = new InvoiceDB();

	public void doPdfExtraction(File targetFile, Address From) throws InvalidPasswordException, IOException {

		int count = 0;

		try (PDDocument pdfDocument = PDDocument.load(targetFile)) {

			pdfDocument.getClass();

			if (!pdfDocument.isEncrypted()) {

				PDFTextStripper pdfTextStripper = new PDFTextStripper();
				String pdfFileInText = pdfTextStripper.getText(pdfDocument);
				String lines[] = pdfFileInText.split("\\r?\\n");
				String invoiceNo = null, invoiceDate = null, totalInvoice, customerPO = null, shipToAddress = null,
						soldToAddress = null, remitToAddress = null;

				for (String line : lines) {
					count++;
					if (line.equals("Invoice No")) {
						invoiceNo = lines[count];
					}
					if (line.equals("Invoice Date")) {
						invoiceDate = lines[count];
					}
					if (line.equals("Customer P.O.")) {
						customerPO = lines[count];
					}
					if (line.equals("Sold To")) {
						soldToAddress = lines[count] + "," + lines[count + 1] + "," + lines[count + 2];
					}
					if (line.equals("Ship To")) {
						shipToAddress = lines[count] + "," + lines[count + 1] + "," + lines[count + 2] + ","
								+ lines[count + 3];
					}
					if (line.equals("Remit To")) {
						remitToAddress = lines[count] + "," + lines[count + 1] + "," + lines[count + 2];
					}
					if (line.equals("Total Invoice") || (line.equals("CREDIT"))) {
						totalInvoice = lines[count];
						invoiceDb.insertInvoiceData(invoiceNo, invoiceDate, totalInvoice, customerPO, shipToAddress,
								soldToAddress, remitToAddress, From);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
