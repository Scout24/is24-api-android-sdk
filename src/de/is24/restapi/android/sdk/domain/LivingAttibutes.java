package de.is24.restapi.android.sdk.domain;

import java.util.ArrayList;
import de.is24.restapi.android.sdk.R;


/**
 * {@link AttributeEnum} for {@link LivingAttibutes} for all RealEstate Types.
 * @author hhosgel
 */
public enum LivingAttibutes implements AttributeEnum {
  ENERGY_PERFORMANCE_CERTIFICATE(R.string.expose_lbl_energyperformancecertification, "energyPerformanceCertificate",
    Boolean.class), FLOORPLAN(R.string.expose_lbl_floorplan, "floorplan", Boolean.class),
  HAS_COURTAGE(R.string.not_specified, "hasCourtage", YesNoNotApplicable.class),
  COURTAGE(R.string.expose_lbl_courtage, "courtage", String.class),
  COURTAGE_NOTE(R.string.expose_lbl_courtage_note, "courtageNote", String.class),
  CELLAR(R.string.expose_lbl_hasbasement, "cellar", YesNotApplicable.class),
  HANDICAPPED_ACCESSIBLE(R.string.expose_lbl_barrierfree, "handicappedAccessible", YesNotApplicable.class),
  NUMBER_OF_PARKING_SPACES(R.string.expose_lbl_noparking, "numberOfParkingSpaces", String.class),
  CONDITION(R.string.expose_lbl_object_status, "condition", RealEstateCondition.class),
  LAST_REFURBISHMENT(R.string.expose_lbl_last_update, "lastRefurbishment", String.class),
  INTERIOR_QUALITY(R.string.expose_lbl_facility_quality, "interiorQuality", InteriorQuality.class),
  CONSTRUCTION_YEAR(R.string.expose_lbl_build_year, "constructionYear", String.class),
  FREE_FROM(R.string.expose_lbl_available, "freeFrom", String.class),
  HEATING_TYPE(R.string.expose_lbl_heater_type, "heatingType", HeatingType.class),
  FIRING_TYPES(R.string.expose_lbl_heater_fuel, "firingTypes", ArrayList.class),
  BUILDING_ENERGY_RATING_TYPE(R.string.expose_lbl_buildingenergyrating, "buildingEnergyRatingType",
    BuildingEnergyRatingType.class),
  THERMAL_CHARACTERISTICS(R.string.expose_lbl_energyconsumption, "thermalCharacteristic", String.class),
  ENERGY_CONSUMPTION_CONTAINS_WARM_WATER(R.string.expose_lbl_isenergyconsumtionwithwater,
    "energyConsumptionContainsWarmWater", YesNotApplicable.class),
  NUMBER_OF_FLOORS(R.string.expose_lbl_level_count, "numberOfFloors", String.class),
  USABLE_FLOOR_SPACE(R.string.expose_lbl_usable_floor_space, "usableFloorSpace", String.class),
  NUMBER_OF_BEDROOMS(R.string.expose_lbl_bedrooms, "numberOfBedRooms", String.class),
  NUMBER_OF_BATHROOMS(R.string.expose_lbl_bathrooms, "numberOfBathRooms", String.class),
  GUEST_TOILET(R.string.expose_lbl_hasguesttoilet, "guestToilet", YesNotApplicable.class),
  PARKING_SPACE_TYPE(R.string.expose_lbl_parkingtype, "parkingSpaceType", ParkingSpaceType.class),
  PARKING_SPACE_PRICE(R.string.expose_lbl_parkingprice, "parkingSpacePrice", String.class),
  EXTERNAL_ID(R.string.expose_lbl_external_id, "externalId", String.class),
  COMMERCIALIZATION_TYPE(R.string.expose_lbl_commercialization_type, "commercializationType",
    CommercializationType.class), CONTACT_FORM_TYPE(R.string.no_information, "@contactFormType", ContactFormType.class),
  CONTACT_FIRSTNAME(R.string.no_information, "firstname", String.class),
  CONTACT_LASTNAME(R.string.no_information, "lastname", String.class),
  CONTACT_COMPANY(R.string.no_information, "company", String.class),
  CONTACT_CELL_PHONE(R.string.no_information, "cellPhoneNumber", String.class),
  CONTACT_PHONE(R.string.no_information, "phoneNumber", String.class),
  CONTACT_ADDRESS_CITY(R.string.no_information, "city", String.class),
  CONTACT_ADDRESS_ZIPCODE(R.string.no_information, "postcode", String.class),
  CONTACT_ADDRESS_STREET(R.string.no_information, "street", String.class),
  CONTACT_ADDRESS_HOUSENUMBER(R.string.no_information, "housenumber", String.class),
  CONTACT_REALTOR_LOGO(R.string.no_information, "realtorLogo", String.class),
  OTHER_NOTE(R.string.expose_header_other, "otherNote", String.class),
  DESCRIPTION_NOTE(R.string.expose_header_object_description, "descriptionNote", String.class),
  FURNISHING_NOTE(R.string.expose_header_facilities, "furnishingNote", String.class),
  LOCATION_NOTE(R.string.expose_header_location, "locationNote", String.class),
  REFERENCE_PRICE(R.string.no_information, "referencePrice", ReferencePrice.class),
  GEOCODE_ID_REGION(R.string.no_information, "region", String.class),
  GEOCODE_ID_CITY(R.string.no_information, "city", String.class),
  GEOCODE_ID_QUARTER(R.string.no_information, "quarter", String.class),
  GEOCODE_ID_NEIGHBOURHOOD(R.string.no_information, "neighbourhood", String.class),
  GEOCODE_ID_FULL(R.string.no_information, "fullGeoCodeId", String.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  LivingAttibutes(final int resId, final String restapiName, final Class<?> valueType) {
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
