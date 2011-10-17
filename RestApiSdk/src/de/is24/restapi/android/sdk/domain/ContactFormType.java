package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link ContactFormType}.
 *
 * @author mboehmer
 */
public enum ContactFormType implements ValueEnum {
  EMAIL(R.string.cf_email, "EMAIL"), LONG(R.string.cf_long, "LONG"), PHONE(R.string.cf_phone, "PHONE"),
  NONE(R.string.cf_none, "NONE");

  public final int resid;
  public final String restapiName;

  private ContactFormType(final int i18nResource, final String restapiName) {
    this.resid = i18nResource;
    this.restapiName = restapiName;
  }

  public int getResId() {
    return resid;
  }

  public String getRestapiName() {
    return restapiName;
  }

}
