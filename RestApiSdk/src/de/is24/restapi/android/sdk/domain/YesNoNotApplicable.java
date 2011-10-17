package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * Enumeration for tri-state boolean elements.
 * Enum Type which is used in REST-API responses.
 *
 * @author mboehmer
 */
public enum YesNoNotApplicable implements ValueEnum {
  YES(R.string.expose_yes, "YES"), NO(R.string.expose_no, "NO"),
  NOT_APPLICABLE(R.string.no_information, "NOT_APPLICABLE");

  public final int i18nResource;
  public final String restapiName;

  private YesNoNotApplicable(final int i18nResource, final String restapiName) {
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
