package net.spark.ui.view.orderedit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.spring.annotation.ViewScope;

import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.data.entity.Product;
import net.spark.backend.service.CurrencyService;
import net.spark.backend.service.PricingStrategyService;
import net.spark.ui.util.DollarPriceConverter;
import net.spark.ui.util.EuroPriceConverter;
import net.spark.ui.util.MURPriceConverter;

@Component
@ViewScope
public class CurrentCurrency {
	@Autowired
	@Lazy
CurrencyService currencyService;
	@Autowired
	@Lazy
PricingStrategyService  pricingStrategyService;
private Currency currentCurrency ;
private PricingStrategy currentPricingStrategy;
public PricingStrategy getCurrentPricingStrategy() {
	return currentPricingStrategy;
}
private StringToIntegerConverter priceConverter=new MURPriceConverter();
@PostConstruct
public void init() {
	currentCurrency = currencyService.getDefault();
	currentPricingStrategy = pricingStrategyService.getDefault();
}
public Currency getCurrentCurrency() {
	return currentCurrency;
}

public void setCurrentCurrency(Currency currentCurrency) {
	this.currentCurrency = currentCurrency;
	processPresenter();
}
private void processPresenter() {
	
		switch(currentCurrency.getName()) {
		case "EUR":
			this.priceConverter = new EuroPriceConverter();
			break;
		case "USD":
			this.priceConverter = new DollarPriceConverter();
			break;
		case "MUR":
			this.priceConverter = new MURPriceConverter();
			break;
		default:
			this.priceConverter = new MURPriceConverter();
		}
	
	
}
public StringToIntegerConverter getPriceConverter() {
	return priceConverter;
}
public void setPriceConverter(StringToIntegerConverter priceConverter) {
	this.priceConverter = priceConverter;
}
public int getIntergerRate100() {
	return new Double (currentCurrency.getRate() * 100).intValue();
}
public void setCurrentPricingStrategy(PricingStrategy value) {
	currentPricingStrategy = value;
	
	
}
public int calculatePrice(Product product) {
	int cprice=0;
	switch(currentPricingStrategy.getPricingType()) {
    case DISCOUNT_WHOLESALE:
   	 cprice = product.getWholeSalePriceHT();
   	 break;
    case VAT_ENABLE :
   	 cprice =product.getPublicPriceHT();
   	 break;
    case WHOLESALE :
   	 cprice = product.getWholeSalePriceHT();
   	 break;
    }
	return cprice*100/getIntergerRate100();
}

}
