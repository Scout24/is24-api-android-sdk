package de.is24.restapi.android.sdk.domain;

import java.util.ArrayList;
import android.text.TextUtils;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.util.StringUtils;


/**
 * The domain object represents the expose and is an extended {@link MiniExpose}
 * @author Hasan Hosgel
 *
 */
public class Expose extends MiniExpose {
  private static final long serialVersionUID = 954449685785528611L;
  public static final byte STATE_UNKNOWN = 87;
  public static final byte STATE_ACTIVE = 121;
  public static final byte STATE_INACTIVE = 43;

  public ArrayList<Attachment> attachments = new ArrayList<Attachment>();

  public long lastCall;
  public long lastEmail;

  // --- not persisted fields !!!!
  public byte streetviewState = STATE_UNKNOWN;

  public Expose(RealEstateType realEstateType) {
    super(realEstateType);
  }

  public PictureAttachment getFirstPictureAttachement() {
    if ((null != attachments) && (attachments.size() > 0)) {
      for (Attachment atm : attachments) {
        if (atm instanceof PictureAttachment) {
          return (PictureAttachment) atm;
        }
      }
    }

    return null;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Expose (uuid=\"").append(id).append("\")");
    return builder.toString();
  }

  public String getPrice() {
    String priceValue = StringUtils.EMPTY_STRING;
    if (has(MiniExposeAttributes.PRICE)) {
      priceValue = getString(MiniExposeAttributes.PRICE);
      if (realEstateType.equalsOne(RealEstateType.LivingBuySite, RealEstateType.LivingRentSite) &&
          has(MiniExposeAttributes.PRICE_INTERVAL_TYPE)) {
        PriceIntervalType priceIntervalType = opt(MiniExposeAttributes.PRICE_INTERVAL_TYPE, null);
        if (priceIntervalType != null) {
          priceValue = priceValue + " " +
            LibraryContext.getInstance().getApplicationContext().getResources().getString(priceIntervalType.getResId());
        }
      }
    } else if (has(LivingRentAttributes.BASE_RENT)) {
      priceValue = getString(LivingRentAttributes.BASE_RENT) + " Kalt";
    }
    if (TextUtils.isEmpty(priceValue)) {
      priceValue = LibraryContext.getInstance().getApplicationContext().getResources().getString(
        R.string.expose_price_value_default);
    }
    return priceValue;
  }

  public String getArea() {
    if (has(MiniExposeAttributes.LIVING_SPACE)) {
      return getString(MiniExposeAttributes.LIVING_SPACE) + " Wohnfl.";
    }
    return StringUtils.EMPTY_STRING;
  }

  public String getRoom() {
    if (has(MiniExposeAttributes.NUMBER_OF_ROOMS)) {
      return getString(MiniExposeAttributes.NUMBER_OF_ROOMS) + " Zimmer";
    }
    return StringUtils.EMPTY_STRING;
  }

  public String getSite() {
    if (has(MiniExposeAttributes.PLOT_AREA)) {
      return getString(MiniExposeAttributes.PLOT_AREA) + " Grundst.";
    }
    return StringUtils.EMPTY_STRING;
  }
}
