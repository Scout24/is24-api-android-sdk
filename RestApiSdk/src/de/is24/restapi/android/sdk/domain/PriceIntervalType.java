package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * The interval to pay the price (day, month, year, one-time charge).
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum PriceIntervalType implements ValueEnum {
  DAY(R.string.pi_day, "DAY"), WEEK(R.string.pi_week, "WEEK"), MONTH(R.string.pi_month, "MONTH"),
  YEAR(R.string.pi_year, "YEAR"), ONE_TIME_CHARGE(R.string.pi_one_time_charge, "ONE_TIME_CHARGE");

  public final int i18nResource;
  public final String restapiName;

  private PriceIntervalType(final int i18nResource, final String restapiName) {
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
