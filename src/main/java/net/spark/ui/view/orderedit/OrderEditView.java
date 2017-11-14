package net.spark.ui.view.orderedit;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus.ViewEventBus;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.HasValue;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import net.spark.app.BeanLocator;
import net.spark.backend.data.OrderState;
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.Order;
import net.spark.backend.data.entity.OrderItem;
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.data.entity.Product;
import net.spark.ui.components.ConfirmPopup;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.customer.CustomerView;

@SpringView(name = "order")
public class OrderEditView extends OrderEditViewDesign implements View {

	public enum Mode {
		EDIT, REPORT, CONFIRMATION;
	}

	private final OrderEditPresenter presenter;

	

	private BeanValidationBinder<Order> binder;

	private Mode mode;

	private boolean hasChanges;
	private final ViewEventBus viewEventBus;
	@Autowired
	private CurrentCurrency currentCurrency;
	
	NavigationManager navigationManager;
	@Autowired
	public OrderEditView(NavigationManager navigationManager,OrderEditPresenter presenter, ViewEventBus viewEventBus) {
		this.presenter = presenter;
		
		this.viewEventBus = viewEventBus;
		this.navigationManager = navigationManager;
	}

	@PostConstruct
	public void init() {
		presenter.init(this);

		// We're limiting dueTime to even hours between 07:00 and 17:00
		dueTime.setItems(IntStream.range(7, 17).mapToObj(h -> LocalTime.of(h, 0)));

		// Binder takes care of binding Vaadin fields defined as Java member
		// fields in this class to properties in the Order bean
		binder = new BeanValidationBinder<>(Order.class);

		// Almost all fields are required, so we don't want to display
		// indicators
		binder.setRequiredConfigurator(null);

		// Bindings are done in the order the fields appear on the screen as we
		// report validation errors for the first invalid field and it is most
		// intuitive for the user that we start from the top if there are
		// multiple errors.
		binder.bindInstanceFields(this);

		// Must bind sub properties manually until
		// https://github.com/vaadin/framework/issues/9210 is fixed
		//binder.bind(fullName, "customer.firstName");
		//binder.bind(fullName, "customer.lastName");
		//binder.bind(phone, "customer.phoneNumber");
		binder.bind(details, "customer.details");

		// Track changes manually as we use setBean and nested binders
		binder.addValueChangeListener(e ->binderHaveChanged(e) );
		//currency.setEmptySelectionAllowed(false);
        //currency.addValueChangeListener(e->fireCurrencyChange(e));
		addItems.addClickListener(e -> addEmptyOrderItem());
		cancel.addClickListener(e -> presenter.editBackCancelPressed());
		ok.addClickListener(e -> presenter.okPressed());
		
		pickCustomer.addClickListener(e->
			navigationManager.navigateTo(CustomerView.class)
		);
	}

	private Object binderHaveChanged(ValueChangeEvent<Object> e) {
		hasChanges = true;
		Object value = e.getValue();
		if(value instanceof Currency || value instanceof PricingStrategy) {
			if(value instanceof Currency)
		currentCurrency.setCurrentCurrency((Currency)value);
		if(value instanceof PricingStrategy)
		currentCurrency.setCurrentPricingStrategy((PricingStrategy)value);
		

		for (Component c : productInfoContainer) {
			if (c instanceof ProductInfo ) {
				//((ProductInfo) c).product.getDataProvider().refreshAll();
				
				Optional<Product> product = (Optional<Product>)((ProductInfo) c).calculatedPrice.getData(); 
				if(null!= product) {
				int price = currentCurrency.calculatePrice(product.get());
				
				
				((ProductInfo) c).calculatedPrice.setValue(currentCurrency.getPriceConverter().convertToPresentation(price, null));
				}else {
					((ProductInfo) c).calculatedPrice.setValue(currentCurrency.getPriceConverter().convertToPresentation(0, null));	
				}
				//break;
				//((ProductInfo) c).product.getDataProvider().refreshAll();
			}
		}
		viewEventBus.publish(this,new CurrencyChangeEvent(currentCurrency.getCurrentCurrency()));  
		//presenter.enterView(null);
		}
		return null;
	}

	



	private int retrievePricing(Product product) {
		int price = 0;
		switch(currentCurrency.getCurrentPricingStrategy().getPricingType()) {
		case DISCOUNT_WHOLESALE:
			price = product.getWholeSalePriceHT();
			break;
		case VAT_ENABLE:
			price = product.getPublicPriceHT();
			break;
		case WHOLESALE:
			price = product.getWholeSalePriceHT();
			break;
		default:
			price = product.getPrice();
			break;
		
		}
		
		return price;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		String orderId = event.getParameters();
		if ("".equals(orderId)) {
			presenter.enterView(null);
		}else if ("return".equals(orderId)) {
			System.out.println("returned");
		}
		else {
			presenter.enterView(Long.valueOf(orderId));
		}
	}

	public void setOrder(Order order) {
		stateLabel.setValue(order.getState().getDisplayName());
		binder.setBean(order);
		productInfoContainer.removeAllComponents();

		reportHeader.setVisible(order.getId() != null);
		if (order.getId() == null) {
			addEmptyOrderItem();
			dueDate.focus();
		} else {
			orderId.setValue("#" + order.getId());
			for (OrderItem item : order.getItems()) {
				ProductInfo productInfo = createProductInfo(item);
				productInfo.setReportMode(mode != Mode.EDIT);
				productInfoContainer.addComponent(productInfo);
			}
			history.setOrder(order);
		}
		hasChanges = false;
	}

	private void addEmptyOrderItem() {
		OrderItem orderItem = new OrderItem();
		ProductInfo productInfo = createProductInfo(orderItem);
		productInfoContainer.addComponent(productInfo);
		productInfo.focus();
		getOrder().getItems().add(orderItem);
	}

	protected void removeOrderItem(OrderItem orderItem) {
		getOrder().getItems().remove(orderItem);

		for (Component c : productInfoContainer) {
			if (c instanceof ProductInfo && ((ProductInfo) c).getItem() == orderItem) {
				productInfoContainer.removeComponent(c);
				break;
			}
		}
	}

	/**
	 * Create a ProductInfo instance using Spring so that it is injected and can
	 * in turn inject a ProductComboBox and its data provider.
	 *
	 * @param orderItem
	 *            the item to edit
	 *
	 * @return a new product info instance
	 */
	private ProductInfo createProductInfo(OrderItem orderItem) {
		ProductInfo productInfo = BeanLocator.find(ProductInfo.class);
		productInfo.setItem(orderItem);
		return productInfo;
	}

	protected Order getOrder() {
		return binder.getBean();
	}

	protected void setSum(int sum) {
		total.setValue(currentCurrency.getPriceConverter().convertToPresentation(sum, null));
		//total.setValue(priceConverter.convertToPresentation(sum, new ValueContext(Locale.getDefault())));
	}

	public void showNotFound() {
		removeAllComponents();
		addComponent(new Label("Order not found"));
	}

	public void setMode(Mode mode) {
		// Allow to style different modes separately
		if (this.mode != null) {
			removeStyleName(this.mode.name().toLowerCase());
		}
		addStyleName(mode.name().toLowerCase());

		this.mode = mode;
		binder.setReadOnly(mode != Mode.EDIT);
		for (Component c : productInfoContainer) {
			if (c instanceof ProductInfo) {
				((ProductInfo) c).setReportMode(mode != Mode.EDIT);
			}
		}
		addItems.setVisible(mode == Mode.EDIT);
		history.setVisible(mode == Mode.REPORT);
		state.setVisible(mode == Mode.EDIT);

		if (mode == Mode.REPORT) {
			cancel.setCaption("Edit");
			cancel.setIcon(VaadinIcons.EDIT);
			Optional<OrderState> nextState = presenter.getNextHappyPathState(getOrder().getState());
			ok.setCaption("Mark as " + nextState.map(OrderState::getDisplayName).orElse("?"));
			ok.setVisible(nextState.isPresent());
		} else if (mode == Mode.CONFIRMATION) {
			cancel.setCaption("Back");
			cancel.setIcon(VaadinIcons.ANGLE_LEFT);
			ok.setCaption("Place order");
			ok.setVisible(true);
		} else if (mode == Mode.EDIT) {
			cancel.setCaption("Cancel");
			cancel.setIcon(VaadinIcons.CLOSE);
			if (getOrder() != null && !getOrder().isNew()) {
				ok.setCaption("Save");
			} else {
				ok.setCaption("Review order");
			}
			ok.setVisible(true);
		} else {
			throw new IllegalArgumentException("Unknown mode " + mode);
		}
	}

	public Mode getMode() {
		return mode;
	}

	public Stream<HasValue<?>> validate() {
		Stream<HasValue<?>> errorFields = binder.validate().getFieldValidationErrors().stream()
				.map(BindingValidationStatus::getField);

		for (Component c : productInfoContainer) {
			if (c instanceof ProductInfo) {
				ProductInfo productInfo = (ProductInfo) c;
				if (!productInfo.isEmpty()) {
					errorFields = Stream.concat(errorFields, productInfo.validate());
				}
			}
		}
		return errorFields;
	}

	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		if (!containsUnsavedChanges()) {
			event.navigate();
		} else {
			ConfirmPopup.get().showLeaveViewConfirmDialog(this, event::navigate);
		}
	}

	public void onProductInfoChanged() {
		hasChanges = true;
	}

	public boolean containsUnsavedChanges() {
		return hasChanges;
	}
}
