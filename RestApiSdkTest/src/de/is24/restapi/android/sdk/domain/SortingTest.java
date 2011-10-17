package de.is24.restapi.android.sdk.domain;

import android.test.AndroidTestCase;


/**
 * @author juschmidt
 *
 */
public class SortingTest extends AndroidTestCase {
  public void testEqualsOne() {
    assertFalse(Sorting.DISTANCE.equalsOne(null));
    assertFalse(Sorting.NEWEST.equalsOne(Sorting.DISTANCE, Sorting.LIVINGSPACE_HIGH));
    assertTrue(Sorting.LIVINGSPACE_HIGH.equalsOne(Sorting.PRICE_HIGH, Sorting.LIVINGSPACE_HIGH));
  }

  public void testIsSupported() {
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.DISTANCE.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.NEWEST.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.PRICE_HIGH.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.PRICE_LOW.isSupported(RealEstateType.LivingRentSite));

    assertFalse(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.ApartmentBuy));
    assertFalse(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.ApartmentRent));
    assertFalse(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.HouseBuy));
    assertFalse(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.PLOTAREA_HIGH.isSupported(RealEstateType.LivingRentSite));

    assertFalse(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.ApartmentBuy));
    assertFalse(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.ApartmentRent));
    assertFalse(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.HouseBuy));
    assertFalse(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.HouseRent));
    assertTrue(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.LivingBuySite));
    assertTrue(Sorting.PLOTAREA_LOW.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.HouseRent));
    assertFalse(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.LivingBuySite));
    assertFalse(Sorting.LIVINGSPACE_HIGH.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.HouseRent));
    assertFalse(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.LivingBuySite));
    assertFalse(Sorting.LIVINGSPACE_LOW.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.ROOMS_HIGH.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.ROOMS_HIGH.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.ROOMS_HIGH.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.ROOMS_HIGH.isSupported(RealEstateType.HouseRent));
    assertFalse(Sorting.ROOMS_HIGH.isSupported(RealEstateType.LivingBuySite));
    assertFalse(Sorting.ROOMS_HIGH.isSupported(RealEstateType.LivingRentSite));

    assertTrue(Sorting.ROOMS_LOW.isSupported(RealEstateType.ApartmentBuy));
    assertTrue(Sorting.ROOMS_LOW.isSupported(RealEstateType.ApartmentRent));
    assertTrue(Sorting.ROOMS_LOW.isSupported(RealEstateType.HouseBuy));
    assertTrue(Sorting.ROOMS_LOW.isSupported(RealEstateType.HouseRent));
    assertFalse(Sorting.ROOMS_LOW.isSupported(RealEstateType.LivingBuySite));
    assertFalse(Sorting.ROOMS_LOW.isSupported(RealEstateType.LivingRentSite));
  }
}
