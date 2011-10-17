package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link CriteriaEnum} for {@link LivingSiteCriteria} used for API-requests.
 * @author hhosgel
 */
public enum LivingSiteCriteria implements CriteriaEnum {
  PLOT_RANGE(R.string.no_information, null, "plotarea"),
  CONSTRUCTION_TYPE_LABEL(R.string.filter_site_constructable_type_title, null, null),
  CONSTRUCTIONPLAN(R.string.filter_site_constructable_type_construction_plan, "constructionplan",
    "siteconstructibletypes"),
  NEIGHBOURCONSTRUCTION(R.string.filter_site_constructable_type_neighbour_construction, "neighbourconstruction",
    "siteconstructibletypes"),
  EXTERNALAREA(R.string.filter_site_constructable_type_external_area, "externalarea", "siteconstructibletypes"),
  DEVELOPMENT_TYPE_LABEL(R.string.filter_site_constructable_type_title, null, null),
  DEVELOPED(R.string.filter_site_development_type_developed, "developed", "sitedevelopmenttypes"),
  DEVELOPED_PARTIALLY(R.string.filter_site_development_type_developed_partially, "developedpartially",
    "sitedevelopmenttypes"),
  NOT_DEVELOPED(R.string.filter_site_development_type_not_developed, "notdeveloped", "sitedevelopmenttypes");

  private final int resId;
  private final String restapiName;
  private final String paramName;

  private LivingSiteCriteria(final int resId, final String restapiName, final String paramName) {
    this.resId = resId;
    this.restapiName = restapiName;
    this.paramName = paramName;
  }

  public int getResId() {
    return resId;
  }

  public String getRestapiName() {
    return restapiName;
  }

  public Class<?> getValueType() {
    return (this == PLOT_RANGE) ? Range.class : Void.class;
  }

  public String getRequestParameterName() {
    return paramName;
  }
}
