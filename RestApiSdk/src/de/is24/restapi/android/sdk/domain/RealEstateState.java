package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * Publication state of a real Estate.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum RealEstateState implements ValueEnum {
  ACTIVE(R.string.no_information, "ACTIVE", true), INACTIVE(R.string.no_information, "INACTIVE", false),
  TO_BE_DELETED(R.string.no_information, "TO_BE_DELETED", false), DRAFT(R.string.no_information, "DRAFT", false),
  ARCHIVED(R.string.no_information, "ARCHIVED", false);

  public final int i18nResource;
  public final String restapiName;
  public final boolean isActive;

  private RealEstateState(final int i18nResource, final String restapiName, final boolean isActive) {
    this.i18nResource = i18nResource;
    this.restapiName = restapiName;
    this.isActive = isActive;
  }

  public int getResId() {
    return i18nResource;
  }

  public String getRestapiName() {
    return restapiName;
  }

}
