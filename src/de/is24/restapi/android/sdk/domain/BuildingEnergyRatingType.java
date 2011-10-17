package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link BuildingEnergyRatingType}.
 *
 * @author mboehmer
 */
public enum BuildingEnergyRatingType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"),
  ENERGY_REQUIRED(R.string.ber_energy_required, "ENERGY_REQUIRED"),
  ENERGY_CONSUMPTION(R.string.ber_energy_consumption, "ENERGY_CONSUMPTION");

  public final int i18nResource;
  public final String restapiName;

  private BuildingEnergyRatingType(final int i18nResource, final String restapiName) {
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
