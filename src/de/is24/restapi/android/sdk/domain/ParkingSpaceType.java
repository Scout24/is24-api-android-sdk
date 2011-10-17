package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * The type of parking space which is provided.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum ParkingSpaceType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"), GARAGE(R.string.ps_garage, "GARAGE"),
  OUTSIDE(R.string.ps_outside, "OUTSIDE"), CARPORT(R.string.ps_carport, "CARPORT"),
  DUPLEX(R.string.ps_duplex, "DUPLEX"), CAR_PARK(R.string.ps_car_park, "CAR_PARK"),
  UNDERGROUND_GARAGE(R.string.ps_underground_garage, "UNDERGROUND_GARAGE");

  public final int i18nResource;
  public final String restapiName;

  private ParkingSpaceType(final int i18nResource, final String restapiName) {
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
