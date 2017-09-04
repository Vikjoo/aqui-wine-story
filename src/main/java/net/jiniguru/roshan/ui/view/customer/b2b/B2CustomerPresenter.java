package net.jiniguru.roshan.ui.view.customer.b2b;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import net.jiniguru.roshan.backend.data.entity.B2Customer;
import net.jiniguru.roshan.backend.service.B2CustomerService;
import net.jiniguru.roshan.ui.navigation.NavigationManager;
import net.jiniguru.roshan.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class B2CustomerPresenter extends AbstractCrudPresenter<B2Customer, B2CustomerService, B2CustomerView>
		implements Serializable {


	@Autowired
	public B2CustomerPresenter(B2CustomerDataProvider customerDataProvider, NavigationManager navigationManager,
			B2CustomerService service) {
		super(navigationManager, service, customerDataProvider);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(B2Customer item) {
		super.editItem(item);
	
		
	}




}