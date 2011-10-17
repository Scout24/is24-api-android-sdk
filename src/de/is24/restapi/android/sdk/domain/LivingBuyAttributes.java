package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#ApartmentBuy}, {@link RealEstateType#HouseBuy}
 * @author hhosgel
 */

public enum LivingBuyAttributes implements AttributeEnum {
  RENTED(R.string.expose_lbl_isrented, "rented", YesNotApplicable.class),
  RENTAL_INCOME(R.string.expose_lbl_rentalincomeactual, "rentalIncome", String.class),
  LISTED(R.string.expose_lbl_hasmonumentprotection, "listed", YesNotApplicable.class),
  SUMMER_RESIDENCE_PRACTICAL(R.string.expose_lbl_isasholidayhomeusable, "summerResidencePractical",
    YesNotApplicable.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  LivingBuyAttributes(final int resId, final String restapiName, final Class<?> valueType) {
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
