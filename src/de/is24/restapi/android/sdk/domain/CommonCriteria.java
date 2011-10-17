package de.is24.restapi.android.sdk.domain;

import android.location.Location;
import de.is24.restapi.android.sdk.R;


/**
 * {@link CriteriaEnum} for {@link CommonCriteria} used for API-requests.
 *
 * @author mboehmer
 */
public enum CommonCriteria implements CriteriaEnum {
  GEOCODE_ID(String.class, null), LOCATION_LABEL(String.class, null), RADIUS(String.class, null),
  LOCATION(Location.class, null), PRICE_RANGE(Range.class, "price");

  private final Class<?> valueType;
  private final String paramName;

  private CommonCriteria(Class<?> valueType, String paramName) {
    this.valueType = valueType;
    this.paramName = paramName;
  }

  public int getResId() {
    return R.string.no_information;
  }

  public String getRestapiName() {
    return null;
  }

  public Class<?> getValueType() {
    return valueType;
  }

  public String getRequestParameterName() {
    return paramName;
  }
}
