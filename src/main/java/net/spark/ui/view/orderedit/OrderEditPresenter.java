package net.spark.ui.view.orderedit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.spring.events.EventBus.ViewEventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.vaadin.data.HasValue;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import net.spark.app.HasLogger;
import net.spark.backend.data.OrderState;
import net.spark.backend.data.PricingType;
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.Customer;
import net.spark.backend.data.entity.Order;
import net.spark.backend.data.entity.OrderItem;
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.data.entity.Product;
import net.spark.backend.service.CurrencyService;
import net.spark.backend.service.OrderService;
import net.spark.backend.service.OutOfStockException;
import net.spark.backend.service.PickupLocationService;
import net.spark.backend.service.PricingStrategyService;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.orderedit.OrderEditView.Mode;
import net.spark.ui.view.storefront.StorefrontView;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SpringComponent
@ViewScope
public class OrderEditPresenter implements Serializable, HasLogger {

	private OrderEditView view;

	private final OrderService orderService;

	private final PickupLocationService pickupLocationService;

	private final NavigationManager navigationManager;

	private final ViewEventBus viewEventBus;

	private PricingStrategyService pricingStrategyService;

	private CurrencyService currencyService;
	@Autowired
	@Lazy
	private CurrentCurrency currentCurrency;
	

	private static final List<OrderState> happyPath = Arrays.asList(OrderState.NEW, OrderState.CONFIRMED,
			OrderState.READY, OrderState.DELIVERED);

	@Autowired
	public OrderEditPresenter(ViewEventBus viewEventBus, NavigationManager navigationManager, OrderService orderService,
			PickupLocationService pickupLocationService,PricingStrategyService pricingStrategyService,CurrencyService currencyService) {
		this.viewEventBus = viewEventBus;
		this.navigationManager = navigationManager;
		this.orderService = orderService;
		this.pickupLocationService = pickupLocationService;
		this.pricingStrategyService = pricingStrategyService;
		this.currencyService = currencyService;
		
		viewEventBus.subscribe(this);
	}

	@PreDestroy
	public void destroy() {
		viewEventBus.unsubscribe(this);
	}

	@EventBusListenerMethod
	private void onCurrencyChange(CurrencyChangeEvent event) {
		
		Double rate = event.getCurrency().getRate();
		
//		for (Iterator iterator = view.productInfoContainer.iterator(); iterator.hasNext();) {
//			ProductInfo productInfo = (ProductInfo) iterator.next();
//			Product product = productInfo.getItem().getProduct();
//			if(null != product)
//			product.setTransientPrice(new Double (product.getPrice()/(rate*100)).intValue());
//			//productInfo.setConvertionRateValue(40);
//			
//		}
		
		updateTotalSum();
		view.onProductInfoChanged();
	}
	@EventBusListenerMethod
	private void onProductInfoChange(ProductInfoChangeEvent event) {
		updateTotalSum();
		view.onProductInfoChanged();
	}

	@EventBusListenerMethod
	private void onOrderItemDelete(OrderItemDeleted event) {
		removeOrderItem(event.getOrderItem());
		view.onProductInfoChanged();
	}

	@EventBusListenerMethod
	private void onOrderItemUpdate(OrderUpdated event) {
		refresh(view.getOrder().getId());
	}

	void init(OrderEditView view) {
		this.view = view;
	}

	/**
	 * Called when the user enters the view.
	 */
	public void enterView(Long id) {
		Order order;
		if (id == null) {
			// New
			order = new Order();
			order.setState(OrderState.NEW);
			order.setItems(new ArrayList<>());
			order.setCustomer(new Customer());
			order.setDueDate(LocalDate.now().plusDays(1));
			order.setDueTime(LocalTime.of(8, 00));
			order.setPickupLocation(pickupLocationService.getDefault());
		} else {
			order = orderService.findOrder(id);
			if (order == null) {
				view.showNotFound();
				return;
			}
		}

		refreshView(order);
	}
//item -> (item.getProduct().getTransientPrice())
	private void updateTotalSum() {
		int sum = 0;/*view.getOrder().getItems().stream().filter(item -> item.getProduct() != null)
				.collect(Collectors.summingInt(item -> (item.getProduct().getTransientPrice())
						 * item.getQuantity()));*/
		List<OrderItem> orderItems = view.getOrder().getItems();
		for(OrderItem item : orderItems) {
			if(item.getProduct() == null) continue;
			int price = selectPricing(item);
			int quantity = item.getQuantity();
			sum += price * quantity;
		}
		view.setSum(sum);
	}

    int selectPricing(OrderItem item) {
    return	currentCurrency.calculatePrice(item.getProduct());
//    	int cprice = 0 ;
//    	
//    PricingStrategy p = view.getOrder().getPricingStrategy();
//    
//     PricingType pricingType =p != null ? p.getPricingType():pricingStrategyService.getDefault().getPricingType();
//    
//	switch(pricingType) {
//     case DISCOUNT_WHOLESALE:
//    	 cprice = item.getProduct().getWholeSalePriceHT();
//    	 break;
//     case VAT_ENABLE :
//    	 cprice = item.getProduct().getPublicPriceHT();
//    	 break;
//     case WHOLESALE :
//    	 cprice = item.getProduct().getWholeSalePriceHT();
//    	 break;
//     }
//	double rate  = view.getOrder().getCurrency() != null ?view.getOrder().getCurrency().getRate():currencyService.getDefault().getRate();
//     int intValue=new Double(rate).intValue();
//    
//    	System.out.println(pricingType+",:rate:"+intValue+",price rs:"+cprice+",finalprice:"+cprice/intValue);
//		return cprice/intValue;
    }

	public void editBackCancelPressed() {
		if (view.getMode() == Mode.REPORT) {
			// Edit order
			view.setMode(Mode.EDIT);
		} else if (view.getMode() == Mode.CONFIRMATION) {
			// Back to edit
			view.setMode(Mode.EDIT);
		} else if (view.getMode() == Mode.EDIT) {
			// Cancel edit
			Long id = view.getOrder().getId();
			if (id == null) {
				navigationManager.navigateTo(StorefrontView.class);
			} else {
				enterView(id);
			}
		}
		
		
	}

	public void okPressed() {
		if (view.getMode() == Mode.REPORT) {
			// Set next state
			Order order = view.getOrder();
			Optional<OrderState> nextState = getNextHappyPathState(order.getState());
			if (!nextState.isPresent()) {
				throw new IllegalStateException(
						"The next state button should never be enabled when the state does not follow the happy path");
			}
			orderService.changeState(order, nextState.get());
			refresh(order.getId());
		} else if (view.getMode() == Mode.CONFIRMATION) {
			Order order = saveOrder();
			if (order != null) {
				// Navigate to edit view so URL is updated correctly
				navigationManager.updateViewParameter("" + order.getId());
				enterView(order.getId());
			}
		} else if (view.getMode() == Mode.EDIT) {
			Optional<HasValue<?>> firstErrorField = view.validate().findFirst();
			if (firstErrorField.isPresent()) {
				((Focusable) firstErrorField.get()).focus();
				return;
			}
			// New order should still show a confirmation page
			Order order = view.getOrder();
			if (order.getId() == null) {
				filterEmptyProducts();
				view.setMode(Mode.CONFIRMATION);
			} else {
				order = saveOrder();
				if (order != null) {
					refresh(order.getId());
				}
			}
		}
	}

	private void refresh(Long id) {
		Order order = orderService.findOrder(id);
		if (order == null) {
			view.showNotFound();
			return;
		}
		refreshView(order);

	}

	private void refreshView(Order order) {
		view.setOrder(order);
		updateTotalSum();
		if (order.getId() == null) {
			view.setMode(Mode.EDIT);
		} else {
			view.setMode(Mode.REPORT);
		}
	}

	private void filterEmptyProducts() {
		LinkedList<OrderItem> emptyRows = new LinkedList<>();
		view.getOrder().getItems().forEach(orderItem -> {
			if (orderItem.getProduct() == null) {
				emptyRows.add(orderItem);
			}
		});
		emptyRows.forEach(this::removeOrderItem);
	}

	private Order saveOrder() {
		try {
			filterEmptyProducts();
			Order order = view.getOrder();
			return orderService.saveOrder(order);
		} catch (ValidationException e) {
			// Should not get here if validation is setup properly
			Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
			getLogger().error("Validation error during order save", e);
			return null;
		} catch (OptimisticLockingFailureException e) {
			// Somebody else probably edited the data at the same time
			Notification.show("Somebody else might have updated the data. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().debug("Optimistic locking error while saving order", e);
			return null;
		}catch (OutOfStockException e) {
			Notification.show("Error: " + e.getMessage(), Type.ERROR_MESSAGE);
			return null;
		}
		catch (Exception e) {
			// Something went wrong, no idea what
			Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
					Type.ERROR_MESSAGE);
			getLogger().error("Unable to save order", e);
			return null;
		}
	}

	public Optional<OrderState> getNextHappyPathState(OrderState current) {
		final int currentIndex = happyPath.indexOf(current);
		if (currentIndex == -1 || currentIndex == happyPath.size() - 1) {
			return Optional.empty();
		}
		return Optional.of(happyPath.get(currentIndex + 1));
	}

	private void removeOrderItem(OrderItem orderItem) {
		view.removeOrderItem(orderItem);
		updateTotalSum();
	}
}
