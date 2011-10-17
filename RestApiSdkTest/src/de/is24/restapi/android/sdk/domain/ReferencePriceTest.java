package de.is24.restapi.android.sdk.domain;

import org.json.JSONObject;
import android.test.AndroidTestCase;


public class ReferencePriceTest extends AndroidTestCase {
  public void testReferencePriceEmptyConstructor() throws Exception {
    ReferencePrice price = new ReferencePrice();
    assertNull(price.avgNeighbourhood);
    assertNull(price.avgQuarter);
    assertNull(price.ownPrice);
    assertNull(price.quarterName);
    assertNull(price.avgNeighbourhoodValue);
    assertNull(price.avgQuarterValue);
    assertNull(price.quotientNeighbourhoodOwn);
    assertNull(price.quotientQuarterOwn);
  }

  public void testToJson() throws Exception {
    ReferencePrice price = new ReferencePrice();

    JSONObject json = price.toJson();
    assertNotNull(json);
    assertEquals(0, json.length());

    price.avgNeighbourhood = "avgNeighbourhood";
    price.avgNeighbourhoodValue = 2.0;

    json = price.toJson();
    assertNotNull(json);
    assertEquals(2, json.length());
    assertEquals("avgNeighbourhood", json.getString("avgNeighbourhood"));
    assertEquals(2.0, json.getDouble("avgNeighbourhoodValue"));

    price.avgQuarter = "avgQuarter";
    price.avgQuarterValue = 3.0;
    price.ownPrice = "ownPrice";
    price.ownPriceValue = 4.0;
    price.quarterName = "quarterName";
    price.quotientNeighbourhoodOwn = 5.0;
    price.quotientQuarterOwn = 6.0;

    json = price.toJson();
    assertNotNull(json);
    assertEquals(9, json.length());
    assertEquals("avgNeighbourhood", json.getString("avgNeighbourhood"));
    assertEquals(2.0, json.getDouble("avgNeighbourhoodValue"));
    assertEquals("avgQuarter", json.getString("avgQuarter"));
    assertEquals(3.0, json.getDouble("avgQuarterValue"));
    assertEquals("ownPrice", json.getString("ownPrice"));
    assertEquals(4.0, json.getDouble("ownPriceValue"));
    assertEquals("quarterName", json.getString("quarterName"));
    assertEquals(5.0, json.getDouble("quotientNeighbourhoodOwn"));
    assertEquals(6.0, json.getDouble("quotientQuarterOwn"));
  }

  public void testContructorWithJson() throws Exception {
    JSONObject json = new JSONObject();
    json.put("avgNeighbourhood", "avgNeighbourhood");

    ReferencePrice price = new ReferencePrice(json);

    assertEquals("avgNeighbourhood", price.avgNeighbourhood);
    assertNull(price.avgNeighbourhoodValue);

    json.put("avgQuarter", "avgQuarter");
    json.put("ownPrice", "ownPrice");
    json.put("quarterName", "quarterName");
    json.put("ownPrice", "ownPrice");
    json.put("avgNeighbourhoodValue", 2.0);
    json.put("avgQuarterValue", 3.0);
    json.put("ownPriceValue", 4.0);
    json.put("quotientNeighbourhoodOwn", 5.0);
    json.put("quotientQuarterOwn", 6.0);

    price = new ReferencePrice(json);

    assertEquals("avgNeighbourhood", price.avgNeighbourhood);
    assertEquals("avgQuarter", price.avgQuarter);
    assertEquals("ownPrice", price.ownPrice);
    assertEquals("quarterName", price.quarterName);
    assertEquals(2.0, price.avgNeighbourhoodValue);
    assertEquals(3.0, price.avgQuarterValue);
    assertEquals(4.0, price.ownPriceValue);
    assertEquals(5.0, price.quotientNeighbourhoodOwn);
    assertEquals(6.0, price.quotientQuarterOwn);
  }

}
