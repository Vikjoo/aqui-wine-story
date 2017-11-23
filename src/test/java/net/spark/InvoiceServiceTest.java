package net.spark;

import java.io.File;

import net.spark.backend.service.InvoiceService;

public class InvoiceServiceTest {

	public static void main(String[] args) {
		InvoiceService is = new InvoiceService();
		File fs = is.generateInvoice(null);
		fs.renameTo(new File("resources/"+fs.getName()));

	}

}
