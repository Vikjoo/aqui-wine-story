package net.jiniguru.roshan.backend.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
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
	@Max(100000)
	private int price;

	public Product() {
		// Empty constructor is needed by Spring Data / JPA
	}
	@NotNull
	@OneToOne
	private ProductWine productWine;
	
	@ElementCollection
	@CollectionTable(name= "PRODUCT_DETAILS", joinColumns=@JoinColumn(name="id"))
	private
	List<AdditionalDetail> productDetails;
	
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

}
