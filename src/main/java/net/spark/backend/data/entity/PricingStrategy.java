package net.spark.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import net.spark.backend.data.PricingType;

@Entity
public class PricingStrategy extends AbstractEntity {
	@Size(max = 255)
	private String name;
	private Double price;
	@Enumerated(EnumType.STRING)
	private PricingType pricingType;
	public PricingStrategy() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public PricingType getPricingType() {
		return pricingType;
	}
	public void setPricingType(PricingType pricingType) {
		this.pricingType = pricingType;
	}
	
}
