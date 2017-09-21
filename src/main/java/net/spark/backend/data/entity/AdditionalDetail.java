package net.spark.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 
 * 
 * 
 * @author rgunoo
 *
 */
@Embeddable
public class AdditionalDetail {
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
