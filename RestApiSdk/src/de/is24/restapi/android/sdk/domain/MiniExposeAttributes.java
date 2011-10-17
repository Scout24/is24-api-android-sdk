package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ExposeEnums} for {@link RealEstateType#ApartmentBuy},{@link RealEstateType#ApartmentRent},
 * {@link RealEstateType#HouseBuy},{@link RealEstateType#HouseRent}
 * @author hhosgel
 */
public enum MiniExposeAttributes implements AttributeEnum {
  TITLE(R.string.no_information, "title", String.class), DISTANCE(R.string.no_information, "distance", String.class),
  CREATION_DATE(R.string.no_information, "creationDate", String.class),
  MODIFICATION_DATE(R.string.no_information, "lastModificationDate", String.class),
  TITLE_PICTURE(R.string.no_information, "titlePicture", PictureAttachment.class),
  LIVING_SPACE(R.string.expose_lbl_living_space, "livingSpace", String.class),
  PRICE(R.string.expose_lbl_price, "price", String.class),
  NUMBER_OF_ROOMS(R.string.expose_lbl_rooms, "numberOfRooms", String.class),
  LATITUDE(R.string.no_information, "latitude", String.class),
  LONGITUDE(R.string.no_information, "longitude", String.class),
  OBJECT_CITY(R.string.no_information, "city", String.class),
  OBJECT_POSTCODE(R.string.no_information, "postcode", String.class),
  OBJECT_STREET(R.string.no_information, "street", String.class),
  OBJECT_HOUSE_NUMBER(R.string.no_information, "houseNumber", String.class),
  PRICE_INTERVAL_TYPE(R.string.expose_lbl_price_interval_type, "priceIntervalType", PriceIntervalType.class),
  PLOT_AREA(R.string.expose_lbl_lot_size, "plotArea", String.class);

  private final int resId;
  private final String restapiName;
  private final Class<?> valueType;

  MiniExposeAttributes(final int resId, final String restapiName, final Class<?> valueType) {
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

  public boolean availableFor(RealEstateType realestateType) {
    return true;
  }

  public MiniExposeAttributes[] getValues() {
    return MiniExposeAttributes.values();
  }
}
