package net.spark.ui.view.admin.PaymentMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import net.spark.backend.data.entity.PaymentMethod;
import net.spark.ui.util.DollarPriceConverter;
import net.spark.ui.view.admin.AbstractCrudView;

@SpringView
public class PaymentMethodAdminView extends AbstractCrudView<PaymentMethod> {

	private final PaymentMethodAdminPresenter presenter;

	private final PaymentMethodAdminViewDesign userAdminViewDesign;

	private final DollarPriceConverter priceToStringConverter;

	
	@Autowired
	public PaymentMethodAdminView(PaymentMethodAdminPresenter presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new PaymentMethodAdminViewDesign();
	}

	@Override
	public void init() {
		super.init();
		presenter.init(this);
		// Show two columns: "name" and "price".
		getGrid().setColumns("name");
		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		
		
	}

	@Override
	public void bindFormFields(BeanValidationBinder<PaymentMethod> binder) {
		binder.forField(getViewComponent().name).bind("name");

		binder.bindInstanceFields(getViewComponent());
	}

	@Override
	public PaymentMethodAdminViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected PaymentMethodAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<PaymentMethod> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<PaymentMethod> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().name;
	}

}