package net.spark.ui.view.customer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class CustomerViewDesign extends VerticalLayout {
	protected TextField search;
	protected Button add;
	protected Grid<net.spark.backend.data.entity.Customer> list;
	protected VerticalLayout form;
	protected TextField email;
	protected TextField name;
	protected TextField firstName;
	protected DateField dob;
	protected DateField doe;
	protected ComboBox<java.lang.String> tom;
	protected DateField memSince;
	protected TextField company;
	protected TextArea billAddress;
	protected TextArea deliveryAddress;
	protected TextField homeNumber;
	protected TextField mobileNumber;
	protected Image qrCode;
	protected TextField coursedFollowed;
	protected TextArea note;
	protected ComboBox<java.lang.String> status;
	protected TextField password;
	protected CssLayout childrenContainer;
	protected Button viewChild;
	protected Button addChild;
	protected Button update;
	protected Button cancel;
	protected Button print;
	protected Button delete;
	protected Button returnB;
	public CustomerViewDesign() {
		Design.read(this);
	}
}
