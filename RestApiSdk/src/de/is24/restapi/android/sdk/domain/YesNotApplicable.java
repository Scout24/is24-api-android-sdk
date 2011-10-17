package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * Enumeration for two-state boolean elements.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum YesNotApplicable implements ValueEnum {
  YES(R.string.expose_yes, "YES"), NOT_APPLICABLE(R.string.no_information, "NOT_APPLICABLE");

  public final int i18nResource;
  public final String restapiName;

  private YesNotApplicable(final int i18nResource, final String restapiName) {
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
