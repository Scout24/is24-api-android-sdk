package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link FiringType}.
 *
 * @author mboehmer
 */
public enum FiringType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"), GEOTHERMAL(R.string.f_geothermal, "GEOTHERMAL"),
  SOLAR_HEATING(R.string.f_solar_heating, "SOLAR_HEATING"), PELLET_HEATING(R.string.f_pellet_heating, "PELLET_HEATING"),
  GAS(R.string.f_gas, "GAS"), OIL(R.string.f_oil, "OIL"),
  DISTRICT_HEATING(R.string.f_district_heading, "DISTRICT_HEATING"), ELECTRICITY(R.string.f_electricity, "ELECTRICITY"),
  COAL(R.string.f_coal, "COAL");

  public final int i18nResource;
  public final String restapiName;

  private FiringType(final int i18nResource, final String restapiName) {
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
