package de.is24.restapi.android.sdk.domain;

import java.io.Serializable;
import de.is24.restapi.android.sdk.R;


/**
 * This enumeration offers the possible {@link Sorting}s for any {@link SearchQuery}
 * @author Hasan Hosgel
 *
 */
public enum Sorting implements Serializable {
  //in the moment the sorting by date is implemented by ID
  DISTANCE(R.string.sorting_distance, "distance", false), PRICE_HIGH(R.string.sorting_price_high, "price", true),
  PRICE_LOW(R.string.sorting_price_low, "price", false),
  LIVINGSPACE_HIGH(R.string.sorting_area_high, "livingspace", true, RealEstateType.ApartmentBuy,
    RealEstateType.ApartmentRent, RealEstateType.HouseBuy, RealEstateType.HouseRent),
  LIVINGSPACE_LOW(R.string.sorting_area_low, "livingspace", false, RealEstateType.ApartmentBuy,
    RealEstateType.ApartmentRent, RealEstateType.HouseBuy, RealEstateType.HouseRent),
  PLOTAREA_HIGH(R.string.sorting_area_high, "plotarea", true, RealEstateType.LivingBuySite,
    RealEstateType.LivingRentSite),
  PLOTAREA_LOW(R.string.sorting_area_low, "plotarea", false, RealEstateType.LivingBuySite,
    RealEstateType.LivingRentSite),
  ROOMS_HIGH(R.string.sorting_room_high, "rooms", true, RealEstateType.ApartmentBuy, RealEstateType.ApartmentRent,
    RealEstateType.HouseBuy, RealEstateType.HouseRent),
  ROOMS_LOW(R.string.sorting_room_low, "rooms", false, RealEstateType.ApartmentBuy, RealEstateType.ApartmentRent,
    RealEstateType.HouseBuy, RealEstateType.HouseRent), NEWEST(R.string.sorting_date, "id", true);

  public final int resId;
  public final String restapiName;
  public final boolean isDescendingOrder;
  private final RealEstateType[] allowedTypes;

  private Sorting(final int resId, final String restapiName, final boolean isDescendingOrder,
    final RealEstateType... allowedTypes) {
    this.resId = resId;
    this.restapiName = restapiName;
    this.isDescendingOrder = isDescendingOrder;
    this.allowedTypes = allowedTypes;
  }

  public boolean isSupported(final RealEstateType type) {
    if ((null == allowedTypes) || (allowedTypes.length == 0)) {
      return true;
    }
    return type.equalsOne(allowedTypes);
  }

  public boolean equalsOne(final Sorting... sortings) {
    if ((null != sortings) && (sortings.length > 0)) {
      for (Sorting type : sortings) {
        if (type == this) {
          return true;
        }
      }
    }
    return false;
  }
}
