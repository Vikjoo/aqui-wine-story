package net.spark.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Stock extends AbstractEntity {

	private int count;
	public Stock() {
		
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}



}
