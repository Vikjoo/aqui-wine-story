package net.spark.cellar.ui.view;

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
import net.spark.backend.data.entity.Product;
import net.spark.backend.service.CrudService;
import net.spark.backend.service.ProductService;
import net.spark.backend.service.WineService;
import net.spark.cellar.Wine;

@SpringComponent
@PrototypeScope
public class WineAdminDataProvider extends FilterablePageableDataProvider<Wine, Object> {

	private transient CrudService<Wine> productService;

	@Override
	protected Page<Wine> fetchFromBackEnd(Query<Wine, Object> query, Pageable pageable) {
		return getProductService().findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Wine, Object> query) {
		return (int) getProductService().countAnyMatching(getOptionalFilter());
	}

	protected CrudService<Wine> getProductService() {
		if (productService == null) {
			productService = BeanLocator.find(WineService.class);
		}
		return productService;
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}