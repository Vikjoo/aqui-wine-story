package net.spark.backend.data.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;

import net.spark.backend.data.OrderState;

@Entity(name = "OrderInfo") // "Order" is a reserved word
@NamedEntityGraphs({ @NamedEntityGraph(name = "Order.gridData", attributeNodes = { @NamedAttributeNode("customer") }),
		@NamedEntityGraph(name = "Order.allData", attributeNodes = { @NamedAttributeNode("customer"),
				@NamedAttributeNode("items"), @NamedAttributeNode("history") }) })
public class Order extends AbstractEntity {

	@NotNull
	private LocalDate dueDate;
	@NotNull
	private LocalTime dueTime;
	@NotNull
	@OneToOne
	private PickupLocation pickupLocation;
	@OneToOne
	private Currency currency;
	@OneToOne
	private PricingStrategy pricingStrategy;

	@OneToOne
	private PaymentCondition paymentCondition;
	
	@OneToOne
	private PaymentMethod paymentMethod;

	private int paymentTerm;
	@NotNull
	@OneToOne
	private Customer customer;
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderColumn(name = "id")
	private List<OrderItem> items;
	@NotNull
	private OrderState state;
      
	private boolean paid;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderColumn(name = "id")
	private List<HistoryItem> history;

	public Order() {
		// Empty constructor is needed by Spring Data / JPA
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalTime getDueTime() {
		return dueTime;
	}

	public void setDueTime(LocalTime dueTime) {
		this.dueTime = dueTime;
	}

	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(PickupLocation pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public List<HistoryItem> getHistory() {
		return history;
	}

	public void setHistory(List<HistoryItem> history) {
		this.history = history;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public PricingStrategy getPricingStrategy() {
		return pricingStrategy;
	}

	public void setPricingStrategy(PricingStrategy pricingStrategy) {
		this.pricingStrategy = pricingStrategy;
	}

	public PaymentCondition getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(PaymentCondition paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}



}
