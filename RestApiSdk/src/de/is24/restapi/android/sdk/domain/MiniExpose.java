package de.is24.restapi.android.sdk.domain;


/**
 * This domain object represents the expose for list views .
 * @author Hasan Hosgel
 *
 */
public class MiniExpose extends MementoMap<AttributeEnum> {
  private static final long serialVersionUID = 8622036925242440561L;
  public String id;
  public RealEstateState state = RealEstateState.ACTIVE;
  public boolean isFavorite = false;
  public String etag;
  public String shortlistEntryId;

  public MiniExpose(RealEstateType realEstateType) {
    super(realEstateType);
  }
}
