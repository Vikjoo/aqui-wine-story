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
import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.PaymentTerm;
import net.spark.backend.service.PaymentConditionService;
import net.spark.backend.service.PaymentTermService;

/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class PaymentConditionComboBoxDataProvider extends PageableDataProvider<PaymentCondition, String> {

	private transient PaymentConditionService paymentTermService;

	@Override
	protected Page<PaymentCondition> fetchFromBackEnd(Query<PaymentCondition, String> query, Pageable pageable) {
		return getPaymentConditionService().findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<PaymentCondition, String> query) {
		return (int) getPaymentConditionService().countAnyMatching(query.getFilter());
	}

	protected PaymentConditionService getPaymentConditionService() {
		if (paymentTermService == null) {
			paymentTermService = BeanLocator.find(PaymentConditionService.class);
		}
		return paymentTermService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}
