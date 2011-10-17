package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * The domain object for picture attachments
 * @author Hasan Hosgel
 *
 */
public class PictureAttachment extends Attachment {
  private static final String KEY_EXPOSE = "exposeUrl";
  private static final String KEY_LARGE = "largeUrl";
  public String largeUrl;
  public String exposeUrl;

  public PictureAttachment() {
    super(PICTURE_TYPE);
  }

  public PictureAttachment(JSONObject item) {
    super(item, PICTURE_TYPE);
    largeUrl = item.optString(KEY_LARGE, null);
    exposeUrl = item.optString(KEY_EXPOSE, null);
    if (null == url) {
      url = exposeUrl;
    }
  }

  @Override
  public void additions(JSONObject json) throws JSONException {
    json.putOpt(KEY_EXPOSE, exposeUrl);
    json.putOpt(KEY_LARGE, largeUrl);
  }

}
