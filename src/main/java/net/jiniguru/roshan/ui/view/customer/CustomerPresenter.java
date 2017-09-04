package net.jiniguru.roshan.ui.view.customer;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import net.jiniguru.roshan.backend.data.entity.Customer;
import net.jiniguru.roshan.backend.data.entity.User;
import net.jiniguru.roshan.backend.service.CustomerService;
import net.jiniguru.roshan.backend.service.UserService;
import net.jiniguru.roshan.ui.navigation.NavigationManager;
import net.jiniguru.roshan.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class CustomerPresenter extends AbstractCrudPresenter<Customer, CustomerService, CustomerView>
		implements Serializable {

	private Customer customerItem;

	@Autowired
	public CustomerPresenter(CustomerDataProvider customerDataProvider, NavigationManager navigationManager,
			CustomerService service) {
		super(navigationManager, service, customerDataProvider);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(Customer item) {
		super.editItem(item);
		this.setCustomerItem(item);
		getView().setPasswordRequired(item.isNew());
	}

	public Customer getCustomerItem() {
		return customerItem;
	}

	public void setCustomerItem(Customer customerItem) {
		this.customerItem = customerItem;
	}


}