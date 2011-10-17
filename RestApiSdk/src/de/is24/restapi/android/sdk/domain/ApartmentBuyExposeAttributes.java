package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#ApartmentBuy}.
 *
 * @author mboehmer
 */
public enum ApartmentBuyExposeAttributes implements AttributeEnum {
  RENT_SUBSIDY();

  private ApartmentBuyExposeAttributes() {
  }

  public int getResId() {
    return R.string.expose_lbl_rent_subsidy;
  }

  public String getRestapiName() {
    return "rentSubsidy";
  }

  public Class<?> getValueType() {
    return String.class;
  }
}
