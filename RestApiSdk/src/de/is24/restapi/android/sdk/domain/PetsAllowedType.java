package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * The indicator if pets are allowed.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum PetsAllowedType implements ValueEnum {
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION"), NEGOTIABLE(R.string.pa_negotiable, "NEGOTIABLE"),
  YES(R.string.pa_yes, "YES"), NO(R.string.pa_no, "NO");

  public final int i18nResource;
  public final String restapiName;

  private PetsAllowedType(final int i18nResource, final String restapiName) {
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
