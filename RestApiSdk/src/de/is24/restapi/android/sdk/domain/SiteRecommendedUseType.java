package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


public enum SiteRecommendedUseType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"),
  FUTURE_DEVELOPMENT_LAND(R.string.site_recommended_use_type_future_development_land, "FUTURE_DEVELOPMENT_LAND"),
  TWINHOUSE(R.string.site_recommended_use_type_twinhouse, "TWINHOUSE"),
  SINGLE_FAMILY_HOUSE(R.string.site_recommended_use_type_single_family_house, "SINGLE_FAMILY_HOUSE"),
  GARAGE(R.string.site_recommended_use_type_garage, "GARAGE"),
  GARDEN(R.string.site_recommended_use_type_garden, "GARDEN"),
  NO_DEVELOPMENT(R.string.site_recommended_use_type_no_development, "NO_DEVELOPMENT"),
  APARTMENT_BUILDING(R.string.site_recommended_use_type_apartment_building, "APARTMENT_BUILDING"),
  ORCHARD(R.string.site_recommended_use_type_orchard, "ORCHARD"),
  TERRACE_HOUSE(R.string.site_recommended_use_type_terrace_house, "TERRACE_HOUSE"),
  PARKING_SPACE(R.string.site_recommended_use_type_parking_space, "PARKING_SPACE"),
  VILLA(R.string.site_recommended_use_type_villa, "VILLA"),
  FORREST(R.string.site_recommended_use_type_forrest, "FORREST");

  public final int i18nResource;
  public final String restapiName;

  private SiteRecommendedUseType(final int i18nResource, final String restapiName) {
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
