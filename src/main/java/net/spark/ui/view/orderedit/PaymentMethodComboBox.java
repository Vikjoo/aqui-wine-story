package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.spring.annotation.SpringComponent;

import net.spark.backend.data.entity.PaymentMethod;
import net.spark.backend.data.entity.PaymentTerm;

import com.vaadin.ui.ComboBox;

@SpringComponent
@PrototypeScope
public class PaymentMethodComboBox extends ComboBox<PaymentMethod> {

	private final PaymentMethodComboBoxDataProvider dataProvider;

	@Autowired
	public PaymentMethodComboBox(PaymentMethodComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Payment Method");
		setItemCaptionGenerator(PaymentMethod::getName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
