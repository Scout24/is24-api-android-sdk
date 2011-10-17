package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link HeatingType}.
 *
 * @author mboehmer
 */
public enum HeatingType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"),
  SELF_CONTAINED_CENTRAL_HEATING(R.string.h_self_contained_heating, "SELF_CONTAINED_CENTRAL_HEATING"),
  STOVE_HEATING(R.string.h_stove_heating, "STOVE_HEATING"),
  CENTRAL_HEATING(R.string.h_central_heating, "CENTRAL_HEATING");

  public final int i18nResource;
  public final String restapiName;

  private HeatingType(final int i18nResource, final String restapiName) {
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
