package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#HouseBuy},{@link RealEstateType#HouseRent}.
 * @author hhosgel
 */

public enum HouseAttibutes implements AttributeEnum {
  BUILDING_TYPE();

  HouseAttibutes() {
  }

  public int getResId() {
    return R.string.expose_lbl_building_type;
  }

  public String getRestapiName() {
    return "buildingType";
  }

  public Class<?> getValueType() {
    return BuildingType.class;
  }
}
