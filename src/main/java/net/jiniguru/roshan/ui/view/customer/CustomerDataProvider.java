package net.jiniguru.roshan.ui.view.customer;

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
import net.jiniguru.roshan.backend.data.entity.Customer;
import net.jiniguru.roshan.backend.data.entity.User;
import net.jiniguru.roshan.backend.service.CustomerService;
import net.jiniguru.roshan.backend.service.UserService;

@SpringComponent
@PrototypeScope
public class CustomerDataProvider extends FilterablePageableDataProvider<Customer, Object> {

	private transient CustomerService customerService;

	@Override
	protected Page<Customer> fetchFromBackEnd(Query<Customer, Object> query, Pageable pageable) {
		return getCustomerService().findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Customer, Object> query) {
		return (int) getCustomerService().countAnyMatching(getOptionalFilter());
	}

	protected CustomerService getCustomerService() {
		if (customerService == null) {
			customerService = BeanLocator.find(CustomerService.class);
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