package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link BuildingType}.
 *
 * @author mboehmer
 */
public enum BuildingType implements ValueEnum {
  SINGLE_FAMILY_HOUSE(R.string.filter_house_type_onefamily, "SINGLE_FAMILY_HOUSE"),
  MID_TERRACE_HOUSE(R.string.filter_house_type_mid_terracehouse, "MID_TERRACE_HOUSE"),
  END_TERRACE_HOUSE(R.string.filter_house_type_end_terracehouse, "END_TERRACE_HOUSE"),
  MULTI_FAMILY_HOUSE(R.string.filter_house_type_multifamily, "MULTI_FAMILY_HOUSE"),
  TERRACE_HOUSE(R.string.filter_house_type_terracehouse, null),
  BUNGALOW(R.string.filter_house_type_bungalow, "BUNGALOW"),
  FARMHOUSE(R.string.filter_house_type_farmhouse, "FARMHOUSE"),
  SEMIDETACHED_HOUSE(R.string.filter_house_type_semi, "SEMIDETACHED_HOUSE"),
  VILLA(R.string.filter_house_type_villa, "VILLA"),
  CASTLE_MANOR_HOUSE(R.string.filter_house_type_castle_manor, "CASTLE_MANOR_HOUSE"),
  SPECIAL_REAL_ESTATE(R.string.filter_house_type_special, "SPECIAL_REAL_ESTATE"),
  OTHER(R.string.filter_house_type_other, "OTHER");

  public final int i18nResource;
  public final String restapiName;

  private BuildingType(final int i18nResource, final String restapiName) {
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
