package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * enumeration for {@link SiteConstructableTypes}, which implements the interface {@link CheckableItem}.
 * @author Hasan Hosgel
 *
 */
public enum SiteConstructableTypes implements ValueEnum {
  CONSTRUCTIONPLAN(R.string.filter_site_constructable_type_construction_plan, "CONSTRUCTIONPLAN"),
  NEIGHBOURCONSTRUCTION(R.string.filter_site_constructable_type_neighbour_construction, "NEIGHBOURCONSTRUCTION"),
  EXTERNALAREA(R.string.filter_site_constructable_type_external_area, "EXTERNALAREA"),
  NO_INFORMATION(R.string.no_information, "NO_INFORMATION");

  private final int i18nResource;
  private final String restapiName;

  private SiteConstructableTypes(final int i18nResource, final String restapiName) {
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
