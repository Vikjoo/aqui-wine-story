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
public class Customer extends AbstractEntity {

	//@NotNull
	//@NotEmpty
	@Size(max = 255)
	private String email;
	//@NotNull
	//@NotEmpty
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
	
	
	private LocalDate dob;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn(name = "id")
	private List<Children> childrenList = new ArrayList<>();
	
	public LocalDate getDoe() {
		return doe;
	}



	public void setDoe(LocalDate doe) {
		this.doe = doe;
	}



	public String getTom() {
		return tom;
	}



	public void setTom(String tom) {
		this.tom = tom;
	}



	public LocalDate getMemSince() {
		return memSince;
	}



	public void setMemSince(LocalDate memSince) {
		this.memSince = memSince;
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



	public String getHomeNumber() {
		return homeNumber;
	}



	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getQrCode() {
		return qrCode;
	}



	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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



	private LocalDate doe;
	@Size(max = 255)
	private String tom;
	
	private LocalDate memSince;
	@Size(max = 255)
	private String company;
	@Size(max = 255)
	private String billAddress;
	@Size(max = 255)
	private String deliveryAddress;
	
	@Size(max = 255)
	private String homeNumber;
	@Size(max = 255)
	private String mobileNumber;
	@Size(max = 255)
	private String qrCode;
	@Size(max = 255)
	private String coursedFollowed;
	@Size(max = 255)
	private String note;
	@Size(max = 255)
	private String status;
	
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



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public List<Children> getChildrenList() {
		return childrenList;
	}



	public void setChildrenList(List<Children> childrenList) {
		this.childrenList = childrenList;
	}


}
