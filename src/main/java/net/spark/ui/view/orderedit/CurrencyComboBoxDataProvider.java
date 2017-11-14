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
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.service.CurrencyService;
import net.spark.backend.service.PickupLocationService;

/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class CurrencyComboBoxDataProvider extends PageableDataProvider<Currency, String> {

	private transient CurrencyService currencyService;

	@Override
	protected Page<Currency> fetchFromBackEnd(Query<Currency, String> query, Pageable pageable) {
		return getCurrencyService().findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Currency, String> query) {
		return (int) getCurrencyService().countAnyMatching(query.getFilter());
	}

	protected CurrencyService getCurrencyService() {
		if (currencyService == null) {
			currencyService = BeanLocator.find(CurrencyService.class);
		}
		return currencyService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}
