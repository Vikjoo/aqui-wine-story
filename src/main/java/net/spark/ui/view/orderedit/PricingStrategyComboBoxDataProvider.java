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
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.service.CurrencyService;
import net.spark.backend.service.PickupLocationService;
import net.spark.backend.service.PricingStrategyService;

/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class PricingStrategyComboBoxDataProvider extends PageableDataProvider<PricingStrategy, String> {

	private transient PricingStrategyService pricingStrategyService;

	@Override
	protected Page<PricingStrategy> fetchFromBackEnd(Query<PricingStrategy, String> query, Pageable pageable) {
		return getPricingStrategyService().findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<PricingStrategy, String> query) {
		return (int) getPricingStrategyService().countAnyMatching(query.getFilter());
	}

	protected PricingStrategyService getPricingStrategyService() {
		if (pricingStrategyService == null) {
			pricingStrategyService = BeanLocator.find(PricingStrategyService.class);
		}
		return pricingStrategyService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}
