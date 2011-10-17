package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link CriteriaEnum} for {@link LivingExtendedCriteria} used for API-requests.
 * @author mboehmer
 */
public enum LivingExtendedCriteria implements CriteriaEnum {
  LIVING_SPACE_RANGE(R.string.no_information, null, "livingspace", Range.class),
  ROOMS_RANGE(R.string.no_information, null, "numberofrooms", Range.class),
  PARKING(R.string.filter_flat_equipment_parking, "parking", "equipment", Void.class),
  GUEST_TOILET(R.string.filter_flat_equipment_guesttoilet, "guestToilet", "equipment", Void.class),
  CELLAR(R.string.filter_flat_equipment_cellar, "cellar", "equipment", Void.class),
  HANDICAPPED_ACCESSIBLE(R.string.filter_flat_equipment_handicapped_accessible, "handicappedAccessible", "equipment",
    Void.class);

  private final String paramName;
  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  private LivingExtendedCriteria(int resId, String restapiName, String paramName, Class<?> valueType) {
    this.resId = resId;
    this.restapiName = restapiName;
    this.paramName = paramName;
    this.valueType = valueType;
  }

  public int getResId() {
    return resId;
  }

  public String getRestapiName() {
    return restapiName;
  }

  public Class<?> getValueType() {
    return valueType;
  }

  public String getRequestParameterName() {
    return paramName;
  }
}
