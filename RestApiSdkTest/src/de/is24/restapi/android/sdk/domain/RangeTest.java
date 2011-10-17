package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class RangeTest extends AndroidTestCase {
  public void testConstructor() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("to", "to");
    json.put("from", "from");

    Range range = new Range(json);
    assertEquals("to", range.to);
    assertEquals("from", range.from);
    range = new Range(null);
    assertNull(range.to);
    assertNull(range.from);
  }

}
