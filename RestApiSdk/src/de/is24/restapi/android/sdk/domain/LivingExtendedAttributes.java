package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link LivingExtendedAttributes} for {@link RealEstateType#ApartmentBuy},{@link RealEstateType#ApartmentRent},
 * {@link RealEstateType#HouseRent}
 * @author hhosgel
 */
public enum LivingExtendedAttributes implements AttributeEnum {
  ASSISTED_LIVING(R.string.expose_lbl_cared, "assistedLiving"),
  BUILT_IN_KITCHEN(R.string.expose_lbl_kitchencomplete, "builtInKitchen");

  private final int resId;
  private final String restapiName;

  LivingExtendedAttributes(final int resId, final String restapiName) {
    this.resId = resId;
    this.restapiName = restapiName;
  }

  public int getResId() {
    return resId;
  }

  public String getRestapiName() {
    return restapiName;
  }

  public Class<?> getValueType() {
    return Boolean.class;
  }
}
