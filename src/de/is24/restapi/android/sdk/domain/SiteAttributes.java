package de.is24.restapi.android.sdk.domain;

import java.util.ArrayList;
import de.is24.restapi.android.sdk.R;


/**
 * {@link ExposeEnums} for {@link RealEstateType#LivingBuySite},
 * {@link RealEstateType#LivingRentSite},
 *
 * @author mboehmer
 */
public enum SiteAttributes implements AttributeEnum {
  SHORT_TERM_CONSTRUCTABLE(R.string.expose_lbl_short_term_constructable, "shortTermConstructible", Boolean.class),
  BUILDING_PERMISSION(R.string.expose_lbl_building_permission, "buildingPermission", Boolean.class),
  DEMOLITION(R.string.expose_lbl_demolition, "demolition", Boolean.class),
  GRZ(R.string.expose_lbl_grz, "grz", String.class), GFZ(R.string.expose_lbl_gfz, "gfz", String.class),
  LEASE_INTERVAL(R.string.expose_lbl_lease_interval, "leaseInterval", LeaseIntervalType.class),
  SITE_DEVELOPMENT_TYPE(R.string.expose_lbl_development_type, "siteDevelopmentType", SiteDevelopmentTypes.class),
  SITE_CONSTRUCTABLE_TYPE(R.string.expose_lbl_constructable_type, "siteConstructibleType", SiteConstructableTypes.class),
  MIN_DIVISIBLE(R.string.expose_lbl_min_divisible, "minDivisible", String.class),
  RECOMMENDED_USE_TYPES(R.string.expose_lbl_recommended_use_type, "recommendedUseTypes", ArrayList.class),
  TENANCY(R.string.expose_lbl_tenancy, "tenancy", String.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  SiteAttributes(final int resId, final String restapiName, final Class<?> valueType) {
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
