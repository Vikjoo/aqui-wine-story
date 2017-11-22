package net.spark.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class PaymentCondition extends AbstractEntity {
	@Size(max = 255)
	private String name;
	@Min(0)
	@Max(100)
	private int downpaymentPercent ;

	public PaymentCondition() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDownpaymentPercent() {
		return downpaymentPercent;
	}
	public void setDownpaymentPercent(int downpaymentPercent) {
		this.downpaymentPercent = downpaymentPercent;
	}


	
}
