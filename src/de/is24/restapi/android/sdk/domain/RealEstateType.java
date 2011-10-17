package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * This is the Definition of used RealEstateTypes
 * @author Hasan Hosgel
 *
 */
public enum RealEstateType {
  ApartmentBuy(true, "APARTMENT_BUY", true, R.string.search_preferences_value_what_apartment_buy),
  ApartmentRent(true, "APARTMENT_RENT", false, R.string.search_preferences_value_what_apartment_rent),
  HouseBuy(false, "HOUSE_BUY", true, R.string.search_preferences_value_what_house_buy),
  HouseRent(false, "HOUSE_RENT", false, R.string.search_preferences_value_what_house_rent),
  LivingBuySite(false, "LIVING_BUY_SITE", true, R.string.search_preferences_value_what_site_buy),
  LivingRentSite(false, "LIVING_RENT_SITE", false, R.string.search_preferences_value_what_site_rent);

  public final boolean hasReferencePrice;
  public final String restapiName;
  public final boolean isPurchase;
  public final int labelResId;

  private RealEstateType(final boolean hasReferencePrice, final String restapiName, final boolean isPurchase,
    final int labelResId) {
    this.hasReferencePrice = hasReferencePrice;
    this.restapiName = restapiName;
    this.isPurchase = isPurchase;
    this.labelResId = labelResId;
  }

  /**
   * This method checks if at least on var arg matches the {@link RealEstateType}
   * @param realEstateTypes vararg for {@link RealEstateType}s
   * @return true, if at least one {@link RealEstateType} matches
   */
  public boolean equalsOne(RealEstateType... realEstateTypes) {
    if ((null != realEstateTypes) && (realEstateTypes.length > 0)) {
      for (RealEstateType type : realEstateTypes) {
        if (type == this) {
          return true;
        }
      }
    }
    return false;
  }

  public static RealEstateType getRealEstateType(String valueName) {
    for (RealEstateType type : values()) {
      if (type.restapiName.equals(valueName)) {
        return type;
      }
    }
    throw new IllegalArgumentException("unknown RealEstateType");
  }
}
