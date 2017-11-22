package net.spark.ui.view.admin.PaymentCondition;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.spring.annotation.SpringView;
import net.spark.backend.data.entity.PaymentCondition;
import net.spark.ui.util.DollarPriceConverter;
import net.spark.ui.util.EuroPriceConverter;
import net.spark.ui.view.admin.AbstractCrudView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView
public class PaymentConditionAdminView extends AbstractCrudView<PaymentCondition> {

	private final PaymentConditionAdminPresenter presenter;

	private final PaymentConditionAdminViewDesign userAdminViewDesign;

	private final DollarPriceConverter priceToStringConverter;

	
	@Autowired
	public PaymentConditionAdminView(PaymentConditionAdminPresenter presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new PaymentConditionAdminViewDesign();
	}

	@Override
	public void init() {
		super.init();
		presenter.init(this);
		// Show two columns: "name" and "price".
		getGrid().setColumns("name", "downpaymentPercent");
		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		
		
	}

	@Override
	public void bindFormFields(BeanValidationBinder<PaymentCondition> binder) {
		binder.forField(getViewComponent().name).bind("name");
		binder.forField(getViewComponent().downPayment).withConverter(new StringToIntegerConverter("Int to String")).bind("downpaymentPercent");

		binder.bindInstanceFields(getViewComponent());
	}

	@Override
	public PaymentConditionAdminViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected PaymentConditionAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<PaymentCondition> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<PaymentCondition> grid) {
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