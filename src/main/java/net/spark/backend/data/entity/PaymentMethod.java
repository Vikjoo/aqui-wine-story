package net.spark.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class PaymentMethod extends AbstractEntity {
	@Size(max = 255)
	private String name;
	
	public PaymentMethod() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
