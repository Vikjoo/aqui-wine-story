package net.spark.ui.view.orderedit;

import net.spark.backend.data.entity.Currency;

public class CurrencyChangeEvent {
	private Currency currency;

	public CurrencyChangeEvent() {
		// TODO Auto-generated constructor stub
	}

	public CurrencyChangeEvent(Currency value) {
		this.setCurrency(value);
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
