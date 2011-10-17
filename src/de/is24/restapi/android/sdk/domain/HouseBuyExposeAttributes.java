package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link RealEstateType#HouseBuy}.
 * @author mboehmer
 */
public enum HouseBuyExposeAttributes implements AttributeEnum {
  LODGER_FLAT(R.string.expose_lbl_lodger_flat, "lodgerFlat", YesNotApplicable.class),
  CONSTRUCTION_PHASE(R.string.expose_lbl_building_phase, "constructionPhase", ConstructionPhaseType.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  private HouseBuyExposeAttributes(final int resId, final String restapiName, final Class<?> valueType) {
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
