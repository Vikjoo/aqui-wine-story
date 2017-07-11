package net.jiniguru.roshan;

import java.util.Currency;
import java.util.Locale;

public class CurrencyUtils {
public static void main(String[] args) {
	System.out.println(Currency.getInstance(new Locale("Mauritius", "MU")).getSymbol());
}
}
