package net.spark.ui.view.admin.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import net.spark.backend.data.entity.Product;
import net.spark.backend.service.ProductService;
import net.spark.ui.navigation.NavigationManager;
import net.spark.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class ProductAdminPresenter extends AbstractCrudPresenter<Product, ProductService, ProductAdminView> {

	@Autowired
	public ProductAdminPresenter(ProductAdminDataProvider productAdminDataProvider, NavigationManager navigationManager,
			ProductService service) {
		super(navigationManager, service, productAdminDataProvider);
	}

	public Object storageClick() {
		  final Window window = new Window("Window");
	        window.setWidth(300.0f, Unit.PIXELS);
	        final FormLayout content = new FormLayout();
	        content.setMargin(true);
	        content.addComponent(new Label("Storage"));
	        window.setContent(content);
	 
	        
	 
	        getView().getViewComponent().getUI().addWindow(window);
		return null;
	}
}
