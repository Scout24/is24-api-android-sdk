package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link LeaseIntervalType} for {@link RealEstateType#LivingBuySite},
 * {@link RealEstateType#LivingRentSite}.
 * @author mboehmer
 */
public enum LeaseIntervalType implements ValueEnum {
  DAY(R.string.li_day, "DAY"), WEEK(R.string.li_week, "WEEK"), MONTH(R.string.li_month, "MONTH"),
  YEAR(R.string.li_year, "YEAR");

  public final int i18nResource;
  public final String restapiName;

  private LeaseIntervalType(final int i18nResource, final String restapiName) {
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
