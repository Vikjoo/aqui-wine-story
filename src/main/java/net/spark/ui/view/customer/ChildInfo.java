package net.spark.ui.view.customer;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.spring.annotation.SpringComponent;

import net.spark.backend.data.entity.Children;
import net.spark.backend.data.entity.OrderItem;
import net.spark.backend.data.entity.Product;

@SpringComponent
@PrototypeScope
public class ChildInfo extends ChildnfoDesign{
	private BeanValidationBinder<Children> binder;
	@PostConstruct
	public void init() {
		binder = new BeanValidationBinder<>(Children.class);
		binder.setRequiredConfigurator(null);
/*		binder.forField(quantity).withConverter(new StringToIntegerConverter(-1, "Please enter a number"))
				.bind("quantity");
*/		binder.bindInstanceFields(this);
/*		binder.addValueChangeListener(e -> fireProductInfoChanged());

		product.addSelectionListener(e -> {
			Optional<Product> selectedProduct = e.getFirstSelectedItem();
			int productPrice = selectedProduct.map(Product::getPrice).orElse(0);
			updatePrice(productPrice);
		});

		readOnlyComment.setWidth("100%");
		readOnlyComment.setId(comment.getId());
		readOnlyComment.setStyleName(comment.getStyleName());

		delete.addClickListener(e -> fireOrderItemDeleted());*/
	}
	@Override
	public void focus() {
		name.focus();
	}
	public void setItem(Children childItem) {
		binder.setBean(childItem);
		
	}
}
