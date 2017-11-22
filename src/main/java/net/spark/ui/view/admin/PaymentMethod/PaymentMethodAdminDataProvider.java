package net.spark.ui.view.admin.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;

import net.spark.app.BeanLocator;
import net.spark.backend.data.entity.PaymentMethod;
import net.spark.backend.service.CrudService;
import net.spark.backend.service.PaymentMethodService;

@SpringComponent
@PrototypeScope
public class PaymentMethodAdminDataProvider extends FilterablePageableDataProvider<PaymentMethod, Object> {

	private transient CrudService<PaymentMethod> paymentConditionService;

	@Override
	protected Page<PaymentMethod> fetchFromBackEnd(Query<PaymentMethod, Object> query, Pageable pageable) {
		return getPaymentMethodService().findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<PaymentMethod, Object> query) {
		return (int) getPaymentMethodService().countAnyMatching(getOptionalFilter());
	}

	protected CrudService<PaymentMethod> getPaymentMethodService() {
		if (paymentConditionService == null) {
			paymentConditionService = BeanLocator.find(PaymentMethodService.class);
		}
		return paymentConditionService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}