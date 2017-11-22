package net.spark.ui.view.admin.PaymentMethod;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

import net.spark.backend.data.entity.PaymentMethod;
import net.spark.backend.service.PaymentMethodService;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class PaymentMethodAdminPresenter extends AbstractCrudPresenter<PaymentMethod, PaymentMethodService, PaymentMethodAdminView> {

	@Autowired
	public PaymentMethodAdminPresenter(PaymentMethodAdminDataProvider productAdminDataProvider, NavigationManager navigationManager,
			PaymentMethodService service) {
		super(navigationManager, service, productAdminDataProvider);
	}


}
