package net.spark.ui.view.orderedit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;

import net.spark.app.BeanLocator;
import net.spark.backend.data.entity.PaymentMethod;
import net.spark.backend.data.entity.PaymentTerm;
import net.spark.backend.service.PaymentMethodService;
import net.spark.backend.service.PaymentTermService;

/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class PaymentMethodComboBoxDataProvider extends PageableDataProvider<PaymentMethod, String> {

	private transient PaymentMethodService paymentMethodService;

	@Override
	protected Page<PaymentMethod> fetchFromBackEnd(Query<PaymentMethod, String> query, Pageable pageable) {
		return getPaymentMethodService().findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<PaymentMethod, String> query) {
		return (int) getPaymentMethodService().countAnyMatching(query.getFilter());
	}

	protected PaymentMethodService getPaymentMethodService() {
		if (paymentMethodService == null) {
			paymentMethodService = BeanLocator.find(PaymentMethodService.class);
		}
		return paymentMethodService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}
