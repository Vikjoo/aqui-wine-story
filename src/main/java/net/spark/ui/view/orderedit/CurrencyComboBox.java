package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.Customer;
import net.spark.backend.data.entity.PickupLocation;
import com.vaadin.ui.ComboBox;

@SpringComponent
@PrototypeScope
public class CurrencyComboBox extends ComboBox<Currency> {

	private final CurrencyComboBoxDataProvider dataProvider;

	@Autowired
	public CurrencyComboBox(CurrencyComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Currency");
		setItemCaptionGenerator(Currency::getName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
