package util.currency;

public class CurrencyUtil {

	public static final int getScale(String currencyPattern) {
		if (currencyPattern != null && currencyPattern.length() > 0 && currencyPattern.lastIndexOf(".") > 0) {
			int scale = currencyPattern.length() - (currencyPattern.lastIndexOf(".") + 1);
			return (scale < 0) ? 0 : scale;
		}
		return 2;
	}
}
