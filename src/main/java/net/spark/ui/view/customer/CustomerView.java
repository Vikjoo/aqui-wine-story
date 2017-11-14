package net.spark.ui.view.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

import net.spark.app.BeanLocator;
import net.spark.backend.data.entity.Children;
import net.spark.backend.data.entity.Customer;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.admin.AbstractCrudView;
import net.spark.ui.view.orderedit.OrderEditView;

@SpringView
public class CustomerView extends AbstractCrudView<Customer> {

	private final CustomerPresenter presenter;

	private final CustomerViewDesign customerViewDesign;

	private boolean passwordRequired;
	  @Autowired
	  
	NavigationManager navigationManager;
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

	private BeanValidationBinder<Customer> customerBinder;

	private boolean hasChanges;

	@Autowired
	public CustomerView(CustomerPresenter presenter) {
		this.presenter = presenter;
		customerViewDesign = new CustomerViewDesign();
	}

	@Override
	public void init() {
		super.init();
		presenter.init(this);
		getGrid().setColumns("email", "lastName");
		
		getViewComponent().addChild.addClickListener(e -> addEmptyChildItem());
		getViewComponent().list.addSelectionListener(listener->{
			getViewComponent().childrenContainer.removeAllComponents();
			
			populateChildItem(listener.getFirstSelectedItem().get().getChildrenList());
		});
		getViewComponent().returnB.addClickListener(e->{
			navigationManager.navigateTo(OrderEditView.class,"return");
		});
	}

	private void addEmptyChildItem() {
		Children childItem = new Children();
		ChildInfo childInfo = createChildInfo(childItem);
		this.customerBinder.forField(childInfo.name).bind(Customer::getDetails, Customer::setDetails);
//		this.customerBinder.bind(childInfo.name, "childrenList.name");
//		this.customerBinder.bind(childInfo.dob, "childrenList.dob");
		getViewComponent().childrenContainer .addComponent(childInfo);
		
		childInfo.focus();
		presenter.getCustomerItem().getChildrenList().add(childItem);
		//getOrder().getItems().add(orderItem);
		

	}
	private void populateChildItem( List<Children> children) {
         children.forEach(childItem->{
        	 ChildInfo childInfo = createChildInfo(childItem);
        	 childInfo.delete.addClickListener(listener->{
        		 childInfo.removeAllComponents();
        		 presenter.getCustomerItem().getChildrenList().remove(childItem);
        	 });
     		this.customerBinder.forField(childInfo.name).bind(Customer::getDetails, Customer::setDetails);
    		this.customerBinder.forField(childInfo.dob).bind(Customer::getDob, Customer::setDob);
    		
//     		this.customerBinder.bind(childInfo.name, "childrenList.name");
//     		this.customerBinder.bind(childInfo.dob, "childrenList.dob");
     		getViewComponent().childrenContainer .addComponent(childInfo);
     		//childInfo.focus();
     		
         });
		
		//getOrder().getItems().add(orderItem);
		

	}

	private ChildInfo createChildInfo(Children childItem) {
		ChildInfo childInfo = BeanLocator.find(ChildInfo.class);
		childInfo.setItem(childItem);
		return childInfo;
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Customer> binder) {
/*		binder.forField(getViewComponent().password).withValidator(passwordValidator).bind(bean -> "",
				(bean, value) -> {
					if (value.isEmpty()) {
						// If nothing is entered in the password field, do
						// nothing
					} else {
						bean.setPassword(presenter.encodePassword(value));
					}
				});*/
		this.customerBinder = binder;
		

		binder.bindInstanceFields(getViewComponent());
		
	}

	public void setPasswordRequired(boolean passwordRequired) {
		this.passwordRequired = passwordRequired;
		getViewComponent().password.setRequiredIndicatorVisible(passwordRequired);
	}

	@Override
	public CustomerViewDesign getViewComponent() {
		return customerViewDesign;
	}

	@Override
	protected CustomerPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Customer> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Customer> grid) {
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
		return getViewComponent().email;
	}
	
	protected Button getReturn() {
		return getViewComponent().returnB;
	}

}
