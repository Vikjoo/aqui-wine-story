package net.jiniguru.roshan.backend.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Children extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126428360147950272L;
	@Size(max = 255)
	private String name;
	@NotNull
	private java.time.LocalDate dob;
	
	public Children() {
	}
	public Children(String name, java.time.LocalDate dob) {
		this.name = name;
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.time.LocalDate getDob() {
		return dob;
	}
	public void setDob(java.time.LocalDate dob) {
		this.dob = dob;
	}
}
