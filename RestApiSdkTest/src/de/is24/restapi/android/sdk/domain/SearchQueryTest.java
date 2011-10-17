package de.is24.restapi.android.sdk.domain;

import android.location.Location;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class SearchQueryTest extends AndroidTestCase {
  public void testConstructors() {
    SearchQuery query = new SearchQuery(RealEstateType.ApartmentBuy);
    assertEquals(RealEstateType.ApartmentBuy, query.realEstateType);
    query.sorting = Sorting.LIVINGSPACE_HIGH;
    query.setSearchType(SearchQuery.SEARCH_TYPE_GPS);
    query.put(CommonCriteria.GEOCODE_ID, "geocodeid");
    query.put(CommonCriteria.LOCATION_LABEL, "label");

    Location loc = new Location("dummy");
    query.put(CommonCriteria.LOCATION, loc);
    query.put(CommonCriteria.RADIUS, "radius");

    query = new SearchQuery(RealEstateType.HouseBuy, query);
    assertEquals(RealEstateType.HouseBuy, query.realEstateType);
    assertNull(query.sorting);
    assertEquals(SearchQuery.SEARCH_TYPE_GPS, query.getSearchType());
    assertEquals("geocodeid", query.getString(CommonCriteria.GEOCODE_ID));
    assertEquals("label", query.getString(CommonCriteria.LOCATION_LABEL));
    assertEquals("radius", query.getString(CommonCriteria.RADIUS));
    assertEquals(loc, query.get(CommonCriteria.LOCATION, Location.class));
    assertNull(query.get(CommonCriteria.PRICE_RANGE, Range.class));
  }

  public void testEquals() throws Exception {
    SearchQuery query1 = new SearchQuery(RealEstateType.ApartmentBuy);
    SearchQuery query2 = new SearchQuery(RealEstateType.ApartmentBuy);
    assertTrue(query1.equals(query2));

    SearchQuery query3 = new SearchQuery(RealEstateType.ApartmentRent);
    assertFalse(query1.equals(query3));

    query2.put(CommonCriteria.LOCATION_LABEL, "label");
    assertFalse(query1.equals(query2));
    query1.put(CommonCriteria.LOCATION_LABEL, "label1");
    assertFalse(query1.equals(query2));
    query1.put(CommonCriteria.LOCATION_LABEL, "label");
    assertTrue(query1.equals(query2));
    query1.put(CommonCriteria.RADIUS, "radius");
    assertFalse(query1.equals(query2));
    assertTrue(query1.equals(query1));
    assertFalse(query1.equals(null));
    assertFalse(query1.equals(new Object()));
  }

  public void testSetSearchType() throws Exception {
    SearchQuery query = new SearchQuery(RealEstateType.ApartmentBuy);
    query.sorting = Sorting.LIVINGSPACE_HIGH;
    query.setSearchType(SearchQuery.SEARCH_TYPE_GPS);
    query.put(CommonCriteria.GEOCODE_ID, "geocodeid");
    query.put(CommonCriteria.LOCATION_LABEL, "label");
    query.put(CommonCriteria.LOCATION, new Location("dummy"));
    query.put(CommonCriteria.RADIUS, "radius");

    assertTrue(query.has(CommonCriteria.GEOCODE_ID));
    assertTrue(query.has(CommonCriteria.LOCATION));
    assertTrue(query.has(CommonCriteria.LOCATION_LABEL));
    assertTrue(query.has(CommonCriteria.RADIUS));

    query.setSearchType(SearchQuery.SEARCH_TYPE_GPS);
    assertTrue(query.has(CommonCriteria.GEOCODE_ID));
    assertTrue(query.has(CommonCriteria.LOCATION));
    assertTrue(query.has(CommonCriteria.LOCATION_LABEL));
    assertTrue(query.has(CommonCriteria.RADIUS));

    query.setSearchType(SearchQuery.SEARCH_TYPE_ADDRESS);
    assertFalse(query.has(CommonCriteria.GEOCODE_ID));
    assertFalse(query.has(CommonCriteria.LOCATION));
    assertTrue(query.has(CommonCriteria.LOCATION_LABEL));
    assertTrue(query.has(CommonCriteria.RADIUS));
    query.setSearchType(SearchQuery.SEARCH_TYPE_INTERVAL);
    assertFalse(query.has(CommonCriteria.GEOCODE_ID));
    assertFalse(query.has(CommonCriteria.LOCATION));
    assertTrue(query.has(CommonCriteria.LOCATION_LABEL));
    assertFalse(query.has(CommonCriteria.RADIUS));
  }
}
