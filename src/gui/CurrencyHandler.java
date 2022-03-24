package gui;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyHandler {
	private static String currency = "";
	
	public static String convertToString(BigDecimal d) {
		BigDecimal b = d.setScale(2, RoundingMode.CEILING);
			
		return b.toString() + currency;
	}
	
	public void setCurrency(String currency) {
		CurrencyHandler.currency = currency;
	}
	
	public String getCurrency() {
		return currency;
	}
}
