package net.spark.cellar.ui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValueContext;
import com.vaadin.spring.annotation.SpringView;
import net.spark.backend.data.entity.Product;
import net.spark.cellar.Wine;
import net.spark.ui.util.DollarPriceConverter;
import net.spark.ui.view.admin.AbstractCrudView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView
public class WineAdminView extends AbstractCrudView<Wine> {

	private final WineAdminPresenter presenter;

	private final WineFormViewDesign userAdminViewDesign;

	private final DollarPriceConverter priceToStringConverter;

	private static final String PRICE_PROPERTY = "price";

	@Autowired
	public WineAdminView(WineAdminPresenter presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new WineFormViewDesign();
	}

	@Override
	public void init() {
		super.init();
		presenter.init(this);
		// Show two columns: "name" and "price".
		getGrid().setColumns("name", PRICE_PROPERTY);
		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		//getGrid().addColumn(product -> priceToStringConverter.convertToPresentation(product.getPrice(),
		//		new ValueContext(getGrid()))).setSortProperty(PRICE_PROPERTY);
		//getViewComponent().storage.addClickListener(listener->presenter.storageClick());
		
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Wine> binder) {
		//binder.forField(getViewComponent().price).withConverter(priceToStringConverter).bind(PRICE_PROPERTY);
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
/*		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");
		binder.bind(getViewComponent().typeOfWine, "productWine.typeOfWine");*/
		binder.bindInstanceFields(getViewComponent());
	}

	@Override
	public WineFormViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected WineAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Wine> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Wine> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().name;
	}

}