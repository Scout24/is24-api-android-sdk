package de.is24.restapi.android.sdk.http.response.handler;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import de.is24.restapi.android.sdk.domain.AttributeEnum;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.PictureAttachment;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.domain.ValueEnum;
import de.is24.restapi.android.sdk.util.Formatter;


public class MiniExposeHandlerHelper {
  private static final String TAG = MiniExposeHandlerHelper.class.getSimpleName();
  static final String JSON_KEY_TITLE_PICTURE_URL = "@xlink.href";
  static final String JSON_KEY_REALESTATE_ID = "@id";
  static final String JSON_KEY_REALESTATE_TYPE = "@xsi.type";
  static final String JSON_KEY_PRICE_VALUE = "value";
  static final String JSON_KEY_WGS84_COORDINATES = "wgs84Coordinate";
  static final String JSON_KEY_ADDRESS = "address";

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static void opt(final MiniExpose expose, final AttributeEnum attrib, final JSONObject json) {
    if (json.has(attrib.getRestapiName())) {
      try {
        String value = json.getString(attrib.getRestapiName());
        if (Boolean.class == attrib.getValueType()) {
          expose.put(attrib, Boolean.valueOf(value));
        } else if (ValueEnum.class.isAssignableFrom(attrib.getValueType())) {
          expose.put(attrib, Enum.valueOf((Class<Enum>) attrib.getValueType(), value));
        } else {
          Log.e(TAG, "unknwon valueType: " + attrib.getValueType());
        }
      } catch (IllegalArgumentException e) {
        Log.e(TAG, "cannot parse value", e);
      } catch (JSONException e) {
        Log.e(TAG, "cannot occure here", e);
      }
    }
  }

  public static void setOptionalStringValue(final MiniExpose expose, final AttributeEnum attrib,
    final JSONObject json) {
    if (json.has(attrib.getRestapiName())) {
      try {
        expose.put(attrib, json.getString(attrib.getRestapiName()));
      } catch (JSONException e) {
        Log.e(TAG, "cannot occure here", e);
      }
    }
  }

  public static MiniExpose parseMiniExpose(JSONObject realestate) throws JSONException {
    MiniExpose miniExpose = null;
    if (realestate != null) {
      String realEstateString = realestate.getString(JSON_KEY_REALESTATE_TYPE);
      try {
        miniExpose = new MiniExpose(
          RealEstateType.valueOf(realEstateString.substring(realEstateString.indexOf(":") + 1)));
      } catch (IllegalArgumentException e) {
        Log.w(TAG, "cannot parse realestatetype=" + realEstateString);
        return null;
      }
      miniExpose.id = realestate.optString(JSON_KEY_REALESTATE_ID);
      setOptionalStringValue(miniExpose, MiniExposeAttributes.CREATION_DATE, realestate);
      setOptionalStringValue(miniExpose, MiniExposeAttributes.MODIFICATION_DATE, realestate);

      if (realestate != null) {
        setOptionalStringValue(miniExpose, MiniExposeAttributes.TITLE, realestate);
        if (realestate.has(MiniExposeAttributes.LIVING_SPACE.getRestapiName())) {
          miniExpose.put(MiniExposeAttributes.LIVING_SPACE,
            Formatter.reformatArea(realestate.getString(MiniExposeAttributes.LIVING_SPACE.getRestapiName())));
        }
        if (realestate.has(MiniExposeAttributes.PLOT_AREA.getRestapiName())) {
          miniExpose.put(MiniExposeAttributes.PLOT_AREA,
            Formatter.reformatArea(realestate.getString(MiniExposeAttributes.PLOT_AREA.getRestapiName())));
        }
        if (realestate.has(MiniExposeAttributes.NUMBER_OF_ROOMS.getRestapiName())) {
          miniExpose.put(MiniExposeAttributes.NUMBER_OF_ROOMS,
            Formatter.reformatNumber(realestate.getString(MiniExposeAttributes.NUMBER_OF_ROOMS.getRestapiName())));
        }

        if (realestate.has(MiniExposeAttributes.PRICE.getRestapiName())) {
          JSONObject resultlistEntryPriceData = realestate.getJSONObject(MiniExposeAttributes.PRICE.getRestapiName());
          miniExpose.put(MiniExposeAttributes.PRICE,
            Formatter.reformatCurrency(resultlistEntryPriceData.getString(JSON_KEY_PRICE_VALUE)));
          opt(miniExpose, MiniExposeAttributes.PRICE_INTERVAL_TYPE, resultlistEntryPriceData);
        }

        JSONObject addressData = realestate.optJSONObject(JSON_KEY_ADDRESS);
        if (addressData != null) {
          setOptionalStringValue(miniExpose, MiniExposeAttributes.OBJECT_STREET, addressData);
          setOptionalStringValue(miniExpose, MiniExposeAttributes.OBJECT_HOUSE_NUMBER, addressData);
          setOptionalStringValue(miniExpose, MiniExposeAttributes.OBJECT_POSTCODE, addressData);
          setOptionalStringValue(miniExpose, MiniExposeAttributes.OBJECT_CITY, addressData);

          JSONObject resultListEntryWgsData = addressData.optJSONObject(JSON_KEY_WGS84_COORDINATES);
          if (resultListEntryWgsData != null) {
            setOptionalStringValue(miniExpose, MiniExposeAttributes.LATITUDE, resultListEntryWgsData);
            setOptionalStringValue(miniExpose, MiniExposeAttributes.LONGITUDE, resultListEntryWgsData);
          }
        }

        if (realestate.has(MiniExposeAttributes.TITLE_PICTURE.getRestapiName())) {
          JSONObject titlePictureData = realestate.getJSONObject(MiniExposeAttributes.TITLE_PICTURE.getRestapiName());
          PictureAttachment pic = new PictureAttachment();
          pic.url = titlePictureData.optString(JSON_KEY_TITLE_PICTURE_URL);
          miniExpose.put(MiniExposeAttributes.TITLE_PICTURE, pic);
        }
      }
    }
    return miniExpose;
  }

}
