package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * enumeration for {@link SiteDevelopmentTypes}, which implements the interface {@link CheckableItem}.
 * @author Hasan Hosgel
 *
 */
public enum SiteDevelopmentTypes implements ValueEnum {
  DEVELOPED(R.string.filter_site_development_type_developed, "DEVELOPED"),
  DEVELOPED_PARTIALLY(R.string.filter_site_development_type_developed_partially, "DEVELOPED_PARTIALLY"),
  NOT_DEVELOPED(R.string.filter_site_development_type_not_developed, "NOT_DEVELOPED"),
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION");

  private final int i18nResource;
  private final String restapiName;

  private SiteDevelopmentTypes(final int i18nResource, final String restapiName) {
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
