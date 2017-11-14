package net.spark.backend.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
/**
 * 
 * 
 * 
 * @author rgunoo
 *
 */
@Entity
public class Product extends AbstractEntity {

	@Size(max = 255)
	private String name;

	// Real price * 100 as an int to avoid rounding errors
	@Min(0)
	@Max(1000000)
	private int price;

	private String reference;

	private int wholeSalePriceHT;
	private int puht;
	private int wholesalePriceTTC;
	private int publicPriceTTC;
	private int publicPriceHT;
//Reference	Pays	Région	Domaine	Cuvée	Couleur	Appellation	Millesime	 P.U H.T 	Cost Price	Stock	 Wholesale Price H.T. 	Wholesale Price T.T.C	Margin Wholesale	Public Price H.T.	Public Price T.T.C	Margin Public Price
	public Product() {
		// Empty constructor is needed by Spring Data / JPA
	}
	@NotNull
	@OneToOne
	private ProductWine productWine;

	@OneToOne(fetch = FetchType.EAGER,
		       cascade = {CascadeType.PERSIST,CascadeType.MERGE })
		       
	private Stock stock;
	
	@ElementCollection
	@CollectionTable(name= "PRODUCT_DETAILS", joinColumns=@JoinColumn(name="id"))
	private
	List<AdditionalDetail> productDetails;
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	

	public int getWholeSalePriceHT() {
		return wholeSalePriceHT;
	}

	public void setWholeSalePriceHT(int wholeSalePriceHT) {
		this.wholeSalePriceHT = wholeSalePriceHT;
	}

	public int getPuht() {
		return puht;
	}

	public void setPuht(int puht) {
		this.puht = puht;
	}

	public int getWholesalePriceTTC() {
		return wholesalePriceTTC;
	}

	public void setWholesalePriceTTC(int wholesalePriceTTC) {
		this.wholesalePriceTTC = wholesalePriceTTC;
	}

	public int getPublicPriceTTC() {
		return publicPriceTTC;
	}

	public void setPublicPriceTTC(int publicPriceTTC) {
		this.publicPriceTTC = publicPriceTTC;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductWine getProductWine() {
		return productWine;
	}

	public void setProductWine(ProductWine productWine) {
		this.productWine = productWine;
	}

	public List<AdditionalDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<AdditionalDetail> productDetails) {
		this.productDetails = productDetails;
	}

	public int getPublicPriceHT() {
		return publicPriceHT;
	}

	public void setPublicPriceHT(int publicPriceHT) {
		this.publicPriceHT = publicPriceHT;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}



}
