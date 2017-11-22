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
import net.spark.backend.data.entity.Customer;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.service.CustomerService;
import net.spark.backend.service.PickupLocationService;

/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class CustomerComboBoxDataProvider extends PageableDataProvider<Customer, String> {

	private transient CustomerService customerService;

	@Override
	protected Page<Customer> fetchFromBackEnd(Query<Customer, String> query, Pageable pageable) {
		
		return getCustomerService().findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Customer, String> query) {
		return (int) getCustomerService().countAnyMatching(query.getFilter());
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
		sortOrders.add(new QuerySortOrder("lastName", SortDirection.ASCENDING));
		sortOrders.add(new QuerySortOrder("firstName", SortDirection.ASCENDING));
		return sortOrders;
	}

}
