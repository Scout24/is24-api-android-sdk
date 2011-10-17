package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;


/**
 * This is a domain object, for representing ranges for the search query
 * @author Hasan Hosgel
 *
 */
public class Range {
  private static final String KEY_FROM = "from";
  private static final String KEY_TO = "to";

  public String from;
  public String to;

  public Range() {
  }

  /**
   * @param jsonRange
   * @throws JSONException
   */
  public Range(JSONObject jsonRange) throws JSONException {
    if (null != jsonRange) {
      from = jsonRange.optString(KEY_FROM, null);
      to = jsonRange.optString(KEY_TO, null);
    }
  }

  public Range(String from, String to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }

    Range other = (Range) obj;
    if (from == null) {
      if (other.from != null) {
        return false;
      }
    } else if (!from.equals(other.from)) {
      return false;
    }
    if (to == null) {
      if (other.to != null) {
        return false;
      }
    } else if (!to.equals(other.to)) {
      return false;
    }
    return true;
  }

  public boolean isEmpty() {
    return TextUtils.isEmpty(from) && TextUtils.isEmpty(to);
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.putOpt(KEY_FROM, from);
    json.putOpt(KEY_TO, to);
    return json;
  }

}
