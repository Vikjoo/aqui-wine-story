package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;

import net.spark.backend.data.entity.PricingStrategy;

@SpringComponent
@PrototypeScope
public class PricingStrategyComboBox extends ComboBox<PricingStrategy> {

	private final PricingStrategyComboBoxDataProvider dataProvider;

	@Autowired
	public PricingStrategyComboBox(PricingStrategyComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Pricing");
		setItemCaptionGenerator(PricingStrategy::getName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
