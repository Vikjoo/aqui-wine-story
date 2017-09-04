package net.jiniguru.roshan.ui.view.customer.b2b;

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

import net.jiniguru.roshan.app.BeanLocator;
import net.jiniguru.roshan.backend.data.entity.B2Customer;
import net.jiniguru.roshan.backend.service.B2CustomerService;

@SpringComponent
@PrototypeScope
public class B2CustomerDataProvider extends FilterablePageableDataProvider<B2Customer, Object> {

	private transient B2CustomerService customerService;

	@Override
	protected Page<B2Customer> fetchFromBackEnd(Query<B2Customer, Object> query, Pageable pageable) {
		return getCustomerService().findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<B2Customer, Object> query) {
		return (int) getCustomerService().countAnyMatching(getOptionalFilter());
	}

	protected B2CustomerService getCustomerService() {
		if (customerService == null) {
			customerService = BeanLocator.find(B2CustomerService.class);
		}
		return customerService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("email", SortDirection.ASCENDING));
		return sortOrders;
	}

}