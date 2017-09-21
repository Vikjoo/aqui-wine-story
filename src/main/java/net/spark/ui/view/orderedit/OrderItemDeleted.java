package net.spark.ui.view.orderedit;

import net.spark.backend.data.entity.OrderItem;

public class OrderItemDeleted {

	private OrderItem orderItem;

	public OrderItemDeleted(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}
}
