package net.spark.ui.view.admin.PaymentCondition;

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
import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.Product;
import net.spark.backend.service.CrudService;
import net.spark.backend.service.PaymentConditionService;
import net.spark.backend.service.ProductService;

@SpringComponent
@PrototypeScope
public class PaymentConditionAdminDataProvider extends FilterablePageableDataProvider<PaymentCondition, Object> {

	private transient CrudService<PaymentCondition> paymentConditionService;

	@Override
	protected Page<PaymentCondition> fetchFromBackEnd(Query<PaymentCondition, Object> query, Pageable pageable) {
		return getPaymentConditionService().findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<PaymentCondition, Object> query) {
		return (int) getPaymentConditionService().countAnyMatching(getOptionalFilter());
	}

	protected CrudService<PaymentCondition> getPaymentConditionService() {
		if (paymentConditionService == null) {
			paymentConditionService = BeanLocator.find(PaymentConditionService.class);
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