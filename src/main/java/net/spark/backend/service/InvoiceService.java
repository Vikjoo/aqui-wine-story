package net.spark.backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.itextpdf.zugferd.exceptions.DataIncompleteException;
import com.itextpdf.zugferd.exceptions.InvalidCodeException;
import com.itextpdf.zugferd.pojo.Customer;
import com.itextpdf.zugferd.pojo.Invoice;
import com.itextpdf.zugferd.pojo.Item;
import com.itextpdf.zugferd.pojo.PojoFactory;
import com.itextpdf.zugferd.pojo.Product;

import net.spark.backend.data.entity.Order;
import net.spark.backend.service.pdf.PdfInvoicesComfort;

@Service
public class InvoiceService {
public File generateInvoice (Order order) {
	
	File returnInvoice= null;
	try {
		returnInvoice = File.createTempFile("Invoice", ".pdf");
		
	    PdfInvoicesComfort app = new PdfInvoicesComfort();
	    
	  
	    	Invoice invoice = createInvoiceFrom(order);
			app.createPdf(invoice , new FileOutputStream(returnInvoice));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DataIncompleteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidCodeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  
	return returnInvoice;
}

private Invoice createInvoiceFrom(Order order) {
	Invoice invoice = new Invoice();
	invoice.setInvoiceDate(new Date());
	
	Customer custo = new Customer();
	custo.setCity("Ebene");
	custo.setCountryId("MU");
	custo.setFirstName("Delphine");
	custo.setLastName("Espitalier-Noel");
	custo.setPostalcode("90210");
	custo.setStreet("Labourdonais");
	custo.setId(100);
	invoice.setCustomer(custo );
	
	List<Item> items= new ArrayList();
	Item item = new Item();
	item.setCost(100D);
	item.setItem(101);
	Product product= new Product();
	product.setId(102);
	product.setName("Merlot");
	product.setPrice(100D);
	product.setVat(15D);
	item.setProduct(product);
	item.setQuantity(100);
	items.add(item );
	invoice.setItems(items);
	return invoice;
}
}
