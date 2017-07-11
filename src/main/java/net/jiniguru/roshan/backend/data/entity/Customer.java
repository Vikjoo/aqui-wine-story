package net.jiniguru.roshan.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer extends AbstractEntity {

	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String email;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String phoneNumber;
	@Size(max = 255)
	private String details;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String lastName;
	
	private boolean locked = false;
	public Customer() {
		// Empty constructor is needed by Spring Data / JPA
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
