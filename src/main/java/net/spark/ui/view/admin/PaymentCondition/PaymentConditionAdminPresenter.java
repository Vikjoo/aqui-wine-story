package net.spark.ui.view.admin.PaymentCondition;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.Product;
import net.spark.backend.service.PaymentConditionService;
import net.spark.backend.service.ProductService;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.admin.AbstractCrudPresenter;
import net.spark.ui.view.admin.product.ProductAdminDataProvider;
import net.spark.ui.view.admin.product.ProductAdminView;

@SpringComponent
@ViewScope
public class PaymentConditionAdminPresenter extends AbstractCrudPresenter<PaymentCondition, PaymentConditionService, PaymentConditionAdminView> {

	@Autowired
	public PaymentConditionAdminPresenter(PaymentConditionAdminDataProvider productAdminDataProvider, NavigationManager navigationManager,
			PaymentConditionService service) {
		super(navigationManager, service, productAdminDataProvider);
	}


}
