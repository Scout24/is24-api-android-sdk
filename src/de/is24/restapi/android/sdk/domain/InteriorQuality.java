package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link InteriorQuality} for {@link RealEstateType#HouseBuy},
 * {@link RealEstateType#HouseRent},{@link RealEstateType#ApartmentBuy},{@link RealEstateType#ApartmentRent},
 * @author mboehmer
 */
public enum InteriorQuality implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"), LUXURY(R.string.iq_luxury, "LUXURY"),
  SOPHISTICATED(R.string.iq_sophisticated, "SOPHISTICATED"), NORMAL(R.string.iq_normal, "NORMAL"),
  SIMPLE(R.string.iq_simple, "SIMPLE");

  public final int i18nResource;
  public final String restapiName;

  private InteriorQuality(final int i18nResource, final String restapiName) {
    this.i18nResource = i18nResource;
    this.restapiName = restapiName;
  }

  public int getResId() {
    return i18nResource;
  }

  public String getRestapiName() {
    return restapiName;
  }
}
