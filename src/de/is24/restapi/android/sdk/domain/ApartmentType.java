package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link RealEstateType#ApartmentBuy},
 * {@link RealEstateType#ApartmentRent}.
 *
 * @author mboehmer
 */
public enum ApartmentType implements ValueEnum {
  ROOF_STOREY(R.string.filter_flat_type_roof_storey, "ROOF_STOREY"), LOFT(R.string.filter_flat_type_loft, "LOFT"),
  MAISONETTE(R.string.filter_flat_type_maisonette, "MAISONETTE"),
  PENTHOUSE(R.string.filter_flat_type_penthouse, "PENTHOUSE"),
  TERRACED_FLAT(R.string.filter_flat_type_terraced_flat, "TERRACED_FLAT"),
  GROUND_FLOOR(R.string.filter_flat_type_ground_floor, "GROUND_FLOOR"),
  APARTMENT(R.string.filter_flat_type_apartment, "APARTMENT"),
  RAISED_GROUND_FLOOR(R.string.filter_flat_type_raised_ground_floor, "RAISED_GROUND_FLOOR"),
  HALF_BASEMENT(R.string.filter_flat_type_souterrain, "HALF_BASEMENT"), OTHER(R.string.filter_flat_type_other, "OTHER"),
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION");

  private final int i18nResource;
  private final String restapiName;

  private ApartmentType(final int i18nResource, final String restapiName) {
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
