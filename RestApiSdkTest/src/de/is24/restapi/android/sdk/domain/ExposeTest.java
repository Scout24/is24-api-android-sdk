package de.is24.restapi.android.sdk.domain;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class ExposeTest extends AndroidTestCase {
  public void testGetNetPrice() {
    Expose expose = new Expose(RealEstateType.ApartmentBuy);
    assertNull(expose.getString(MiniExposeAttributes.PRICE));
    expose.put(MiniExposeAttributes.PRICE, "");
    assertEquals("", expose.getString(MiniExposeAttributes.PRICE));
    expose.put(MiniExposeAttributes.PRICE, "50€");
    assertEquals("50€", expose.getString(MiniExposeAttributes.PRICE));
  }

}
