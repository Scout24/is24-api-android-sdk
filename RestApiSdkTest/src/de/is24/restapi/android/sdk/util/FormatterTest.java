package de.is24.restapi.android.sdk.util;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class FormatterTest extends AndroidTestCase {
  public void testCurrencyFormatter() {
    assertEquals("5.001,95", Formatter.currencyFormatter.format(5001.95));
    assertEquals("5.001,00", Formatter.currencyFormatter.format(5001));
  }

  public void testNumberFormatter() {
    assertEquals("5.001,95", Formatter.numberFormatter.format(5001.95));
    assertEquals("5.001", Formatter.numberFormatter.format(5001));
  }

  public void testCoordinateFormatter() {
    assertEquals("5001.9596", Formatter.coordinateFormatter.format(5001.95959595));
    assertEquals("5001.0000", Formatter.coordinateFormatter.format(5001));
    assertEquals("5001.9595", Formatter.coordinateFormatter.format(5001.9595));
  }

  public void testSqmCurrencyFormatter() {
    assertEquals("5.001,96 €/ m²", Formatter.sqmCurrencyFormatter.format(5001.95959595));
    assertEquals("5.001,00 €/ m²", Formatter.sqmCurrencyFormatter.format(5001));
    assertEquals("5.001,96 €/ m²", Formatter.sqmCurrencyFormatter.format(5001.9595));
  }

  public void testDistanceFormatter() {
    assertEquals("5.002,0 km", Formatter.distanceFormatter.format(5001.95959595));
    assertEquals("5.001,0 km", Formatter.distanceFormatter.format(5001));
    assertEquals("5.001,9 km", Formatter.distanceFormatter.format(5001.9));
  }

}
