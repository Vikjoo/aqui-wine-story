package net.spark.ui.view.customer.b2b;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import net.spark.backend.data.entity.B2Customer;
import net.spark.backend.data.entity.Customer;
import net.spark.ui.view.admin.AbstractCrudView;

@SpringView
public class B2CustomerView extends AbstractCrudView<B2Customer> {

	private final B2CustomerPresenter presenter;

	private final B2CustomerViewDesign customerViewDesign;

	private boolean passwordRequired;

	/**
	 * Custom validator to be able to decide dynamically whether the password
	 * field is required or not (empty value when updating the user is
	 * interpreted as 'do not change the password').
	 */
	private Validator<String> passwordValidator = new Validator<String>() {

		BeanValidator passwordBeanValidator = new BeanValidator(Customer.class, "password");

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			if (!passwordRequired && value.isEmpty()) {
				// No password required and field is empty
				// OK regardless of other restrictions as the empty value will
				// not be used
				return ValidationResult.ok();
			} else {
				return passwordBeanValidator.apply(value, context);
			}
		}
	};

	private BeanValidationBinder<B2Customer> customerBinder;

	private boolean hasChanges;

	@Autowired
	public B2CustomerView(B2CustomerPresenter presenter) {
		this.presenter = presenter;
		customerViewDesign = new B2CustomerViewDesign();
	}

	@Override
	public void init() {
		super.init();
		presenter.init(this);
		getGrid().setColumns("email", "company","billAddress");
		
		

	}




	@Override
	public void bindFormFields(BeanValidationBinder<B2Customer> binder) {
/*		binder.forField(getViewComponent().password).withValidator(passwordValidator).bind(bean -> "",
				(bean, value) -> {
					if (value.isEmpty()) {
						// If nothing is entered in the password field, do
						// nothing
					} else {
						bean.setPassword(presenter.encodePassword(value));
					}
				});*/
	
		
		 binder.forField( getViewComponent().id)
         .withNullRepresentation( "" )
         .withConverter( new StringToLongConverter( Long.valueOf( 0 ), "integers only" ) )
         .bind( B2Customer::getId, B2Customer::setId);
 binder.bindInstanceFields( this );

		this.customerBinder = binder;
		

		binder.bindInstanceFields(getViewComponent());
		
	}



	@Override
	public B2CustomerViewDesign getViewComponent() {
		return customerViewDesign;
	}

	@Override
	protected B2CustomerPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<B2Customer> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<B2Customer> grid) {
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
		return getViewComponent().id;
	}

}
