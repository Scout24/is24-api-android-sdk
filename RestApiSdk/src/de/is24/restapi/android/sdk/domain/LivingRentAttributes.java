package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#ApartmentRent}, {@link RealEstateType#HouseRent}.
 * @author hhosgel
 */

public enum LivingRentAttributes implements AttributeEnum {
  BASE_RENT(R.string.expose_lbl_net_rent, "baseRent", String.class),
  TOTAL_RENT(R.string.expose_lbl_totalrent, "totalRent", String.class),
  SERVICE_CHARGE(R.string.expose_lbl_auxiliary_costs, "serviceCharge", String.class),
  DEPOSIT(R.string.expose_lbl_surety, "deposit", String.class),
  HEATING_COSTS(R.string.expose_lbl_heating_costs, "heatingCosts", String.class),
  HEATING_COSTS_IN_SERVICE_CHARGE(R.string.expose_lbl_heatingcostsinrent, "heatingCostsInServiceCharge",
    YesNotApplicable.class), PETS_ALLOWED(R.string.expose_lbl_pets, "petsAllowed", PetsAllowedType.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  LivingRentAttributes(final int resId, final String restapiName, final Class<?> valueType) {
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
