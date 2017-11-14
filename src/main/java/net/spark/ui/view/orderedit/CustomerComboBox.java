package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;

import net.spark.backend.data.entity.Customer;

@SpringComponent
@PrototypeScope
public class CustomerComboBox extends ComboBox<Customer> {

	private final CustomerComboBoxDataProvider dataProvider;

	@Autowired
	public CustomerComboBox(CustomerComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Customer Name");
		setItemCaptionGenerator(Customer::getLastName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
