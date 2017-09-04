package net.jiniguru.roshan.backend.data.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@Entity
public class B2Customer extends AbstractEntity {

	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String email;

	@Size(max = 255)
	private String firstName;

	@Size(max = 255)
	private String details;


	@Size(max = 255)
	private String lastName;
	
	private LocalDate enrollmentDate;
	
	
	@NotNull
	@NotEmpty
	private String company;
	
	@Size(max = 255)
	private String billAddress;
	@Size(max = 255)
	private String deliveryAddress;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String workNumber;

	
	@Size(max = 255)
	private String coursedFollowed;
	@Size(max = 255)
	private String note;
	@Size(max = 255)
	private String status;
	
	private boolean locked = false;
	
	
	public void  setId(Long id) {
		
	}


	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getBillAddress() {
		return billAddress;
	}



	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}



	public String getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}











	public String getCoursedFollowed() {
		return coursedFollowed;
	}



	public void setCoursedFollowed(String coursedFollowed) {
		this.coursedFollowed = coursedFollowed;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}




	public B2Customer() {
		// Empty constructor is needed by Spring Data / JPA
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



	public String getWorkNumber() {
		return workNumber;
	}



	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}





	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}





	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}





}
