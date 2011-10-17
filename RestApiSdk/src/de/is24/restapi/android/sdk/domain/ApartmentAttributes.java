package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#ApartmentBuy},
 * {@link RealEstateType#ApartmentRent}.
 *
 * @author hhosgel
 */

public enum ApartmentAttributes implements AttributeEnum {
  APARTMENT_TYPE(R.string.expose_lbl_type, "apartmentType", ApartmentType.class),
  FLOOR(R.string.expose_lbl_level, "floor", String.class), LIFT(R.string.expose_lbl_lift, "lift", Boolean.class),
  BALCONY(R.string.expose_lbl_hasbalcony, "balcony", Boolean.class),
  GARDEN(R.string.expose_lbl_hasgarden, "garden", Boolean.class),
  CERTIFICATE_OF_ELIGIBILITY_NEEDED(R.string.expose_lbl_haspromotion, "certificateOfEligibilityNeeded", Boolean.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  ApartmentAttributes(final int resId, final String restapiName, final Class<?> valueType) {
    this.resId = resId;
    this.restapiName = restapiName;
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
}
