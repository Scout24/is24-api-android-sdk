package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link ConstructionPhaseType} which is used for {@link RealEstateType#HouseBuy}.
 *
 * @author mboehmer
 */
public enum ConstructionPhaseType implements ValueEnum {
  PROJECTED(R.string.cp_projected, "PROJECTED"),
  UNDER_CONSTRUCTION(R.string.cp_under_construction, "UNDER_CONSTRUCTION"),
  COMPLETED(R.string.cp_completed, "COMPLETED"), NO_INFORMATION(R.string.no_information, "NO_INFORMATION");

  public final int i18nResource;
  public final String restapiName;

  private ConstructionPhaseType(final int i18nResource, final String restapiName) {
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
