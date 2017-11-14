package net.spark.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Currency extends AbstractEntity {
	@Size(max = 255)
	private String name;
	private Double rate;
	public Currency() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
