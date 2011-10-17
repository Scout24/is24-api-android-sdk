package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Abstract class for deriving attachment types like {@link PdfAttachment} or {@link PictureAttachment}.
 * @author mboehmer
 */
public abstract class Attachment {
  private static final String KEY_CAPTION = "caption";
  private static final String KEY_ISFLOORPLAN = "isFloorplan";
  public static final String KEY_TYPE = "type";
  private static final String KEY_URL = "url";
  public static final int PICTURE_TYPE = 7;
  public static final int PDF_TYPE = 9;
  public String caption;
  public boolean isFloorplan;
  public final int type;
  public String url;


  public Attachment(final int type) {
    this.type = type;
  }

  public Attachment(final JSONObject item, final int type) {
    this(type);
    caption = item.optString(KEY_CAPTION, null);
    isFloorplan = item.optBoolean(KEY_ISFLOORPLAN, false);
    url = item.optString(KEY_URL, null);
  }

  public final JSONObject toJson() throws JSONException {
    final JSONObject json = new JSONObject();
    additions(json);
    json.putOpt(KEY_CAPTION, caption);
    json.putOpt(KEY_ISFLOORPLAN, isFloorplan);
    json.putOpt(KEY_URL, url);
    json.putOpt(KEY_TYPE, type);
    return json;
  }

  public abstract void additions(JSONObject json) throws JSONException;

}
