package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.PaymentTerm;

import com.vaadin.ui.ComboBox;

@SpringComponent
@PrototypeScope
public class PaymentConditionComboBox extends ComboBox<PaymentCondition> {

	private final PaymentConditionComboBoxDataProvider dataProvider;

	@Autowired
	public PaymentConditionComboBox(PaymentConditionComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("PaymentTerm");
		setItemCaptionGenerator(PaymentCondition::getName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
