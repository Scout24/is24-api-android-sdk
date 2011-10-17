package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * The condition of the real estate object.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum RealEstateCondition implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"),
  FIRST_TIME_USE(R.string.rec_first_time_use, "FIRST_TIME_USE"),
  FIRST_TIME_USE_AFTER_REFURBISHMENT(R.string.rec_first_time_use_after_refurbishment,
    "FIRST_TIME_USE_AFTER_REFURBISHMENT"), MINT_CONDITION(R.string.rec_mint_condition, "MINT_CONDITION"),
  REFURBISHED(R.string.rec_refurbished, "REFURBISHED"), MODERNIZED(R.string.rec_modernized, "MODERNIZED"),
  FULLY_RENOVATED(R.string.rec_fully_renovated, "FULLY_RENOVATED"), WELL_KEPT(R.string.rec_well_kept, "WELL_KEPT"),
  NEED_OF_RENOVATION(R.string.rec_need_of_renovation, "NEED_OF_RENOVATION"),
  NEGOTIABLE(R.string.rec_negotiable, "NEGOTIABLE"),
  RIPE_FOR_DEMOLITION(R.string.rec_ripe_for_demolition, "RIPE_FOR_DEMOLITION");

  public final int i18nResource;
  public final String restapiName;

  private RealEstateCondition(final int i18nResource, final String restapiName) {
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
