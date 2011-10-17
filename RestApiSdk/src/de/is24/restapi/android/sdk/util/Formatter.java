package de.is24.restapi.android.sdk.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.Locale;
import android.text.TextUtils;
import android.util.Log;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;


/**
 * This class contains all used formatters
 * @author Hasan Hosgel
 *
 */
public class Formatter {
  private static final String TAG = Formatter.class.getSimpleName();
  public static DecimalFormat currencyFormatter;
  public static DecimalFormat numberFormatter;
  public static DecimalFormat distanceFormatter;
  public static DecimalFormat sqmCurrencyFormatter;
  public static DecimalFormat coordinateFormatter;

  static {
    initCurrencyFormatter();
    initDistanceFormatter();
    initNumberFormatter();
    initSqmCurrencyFormatter();
    initCoordinateFormatter();
  }

  private static Locale useLocale() {
    return Locale.GERMANY;
  }

  private static void initCoordinateFormatter() {
    coordinateFormatter = new DecimalFormat("##0.0000");
    coordinateFormatter.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
  }

  private static void initCurrencyFormatter() {
    currencyFormatter = new DecimalFormat("##,###,##0.00");
    currencyFormatter.setGroupingUsed(true);
    currencyFormatter.setGroupingSize(3);
    currencyFormatter.setDecimalFormatSymbols(new DecimalFormatSymbols(useLocale()));
    currencyFormatter.setCurrency(Currency.getInstance(useLocale()));
  }

  private static void initSqmCurrencyFormatter() {
    sqmCurrencyFormatter = new DecimalFormat("##,###,##0.00 €/ m²");
    sqmCurrencyFormatter.setGroupingUsed(true);
    sqmCurrencyFormatter.setGroupingSize(3);
    sqmCurrencyFormatter.setDecimalFormatSymbols(new DecimalFormatSymbols(useLocale()));
  }

  private static void initNumberFormatter() {
    numberFormatter = new DecimalFormat("##,###,##0.##");
    numberFormatter.setGroupingUsed(true);
    numberFormatter.setGroupingSize(3);
    numberFormatter.setDecimalFormatSymbols(new DecimalFormatSymbols(useLocale()));
  }

  private static void initDistanceFormatter() {
    distanceFormatter = new DecimalFormat("#0.0 km");
    distanceFormatter.setGroupingUsed(true);
    distanceFormatter.setGroupingSize(3);
    distanceFormatter.setDecimalFormatSymbols(new DecimalFormatSymbols(useLocale()));
  }

  public static String reformatArea(final String value) {
    if (!TextUtils.isEmpty(value)) {
      try {
        return TextUtils.concat(Formatter.numberFormatter.format(Double.parseDouble(value)), StringUtils.SPACE,
          LibraryContext.getInstance().getApplicationContext().getResources().getString(R.string.suffix_area))
          .toString();
      } catch (NumberFormatException nfe) {
        Log.e(TAG, "Area value is not a correct number. Value: " + value, nfe);
      }
    }
    return value;
  }

  public static String reformatDistance(final String value) {
    if (!TextUtils.isEmpty(value)) {
      try {
        return Formatter.distanceFormatter.format(Double.parseDouble(value));
      } catch (NumberFormatException nfe) {
        Log.e(TAG, "Distance value is not a correct number. Value: " + value, nfe);
      }
    }
    return value;
  }

  public static String reformatCurrency(final String value) {
    if (!TextUtils.isEmpty(value)) {
      try {
        double price = Double.parseDouble(value);
        if (price > 0.0) {
          return TextUtils.concat(Formatter.currencyFormatter.format(price), StringUtils.SPACE,
            Formatter.currencyFormatter.getCurrency().getSymbol()).toString();
        }
      } catch (NumberFormatException nfe) {
        Log.e(TAG, "Currency value is not a correct number. Value: " + value, nfe);
      }
    }
    return "Preis auf Anfrage";
  }

  public static String reformatNumber(final String value) {
    if (!TextUtils.isEmpty(value)) {
      try {
        return Formatter.numberFormatter.format(Double.parseDouble(value));
      } catch (NumberFormatException nfe) {
        Log.e(TAG, "Value is not a correct number. Value: " + value, nfe);
      }
    }
    return value;
  }
}
