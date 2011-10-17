package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class PictureAttachmentTest extends AndroidTestCase {
  public void testConstructor() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("caption", "caption");
    json.put("exposeUrl", "exposeUrl");
    json.put("largeUrl", "largeUrl");
    json.put("isFloorplan", true);

    PictureAttachment picture = new PictureAttachment(json);
    assertEquals(true, picture.isFloorplan);
    assertEquals("caption", picture.caption);
    assertEquals("exposeUrl", picture.exposeUrl);
    assertEquals("largeUrl", picture.largeUrl);
  }

}
