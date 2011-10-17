package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * the domain object for the referenceprice
 * @author Hasan Hosgel
 *
 */
public class ReferencePrice {
  private static final String KEY_AVG_QUARTER = "avgQuarter";
  private static final String KEY_AVG_NEIGHBOURHOOD = "avgNeighbourhood";
  private static final String KEY_QUOTIENT_QUARTER_OWN = "quotientQuarterOwn";
  private static final String KEY_QUOTIENT_NEIGHBOURHOOD_OWN = "quotientNeighbourhoodOwn";
  private static final String KEY_OWN_PRICE = "ownPrice";
  private static final String KEY_QUARTER_NAME = "quarterName";
  private static final String KEY_AVG_NEIGHBOURHOOD_VALUE = "avgNeighbourhoodValue";
  private static final String KEY_AVG_QUARTER_VALUE = "avgQuarterValue";
  private static final String KEY_OWN_PRICE_VALUE = "ownPriceValue";
  public String avgQuarter;
  public String avgNeighbourhood;
  public Double quotientQuarterOwn;
  public Double quotientNeighbourhoodOwn;
  public String ownPrice;
  public String quarterName;
  public Double avgNeighbourhoodValue;
  public Double avgQuarterValue;
  public Double ownPriceValue;

  public ReferencePrice() {
  }

  public ReferencePrice(JSONObject json) throws JSONException {
    avgNeighbourhood = json.optString(KEY_AVG_NEIGHBOURHOOD, null);
    avgQuarter = json.optString(KEY_AVG_QUARTER, null);
    ownPrice = json.optString(KEY_OWN_PRICE, null);
    quarterName = json.optString(KEY_QUARTER_NAME, null);
    quotientNeighbourhoodOwn = optionalDouble(json, KEY_QUOTIENT_NEIGHBOURHOOD_OWN);
    quotientQuarterOwn = optionalDouble(json, KEY_QUOTIENT_QUARTER_OWN);
    avgNeighbourhoodValue = optionalDouble(json, KEY_AVG_NEIGHBOURHOOD_VALUE);
    avgQuarterValue = optionalDouble(json, KEY_AVG_QUARTER_VALUE);
    ownPriceValue = optionalDouble(json, KEY_OWN_PRICE_VALUE);
  }

  public JSONObject toJson() throws JSONException {
    final JSONObject json = new JSONObject();
    json.putOpt(KEY_AVG_NEIGHBOURHOOD, avgNeighbourhood);
    json.putOpt(KEY_AVG_QUARTER, avgQuarter);
    json.putOpt(KEY_OWN_PRICE, ownPrice);
    json.putOpt(KEY_QUARTER_NAME, quarterName);
    json.putOpt(KEY_QUOTIENT_NEIGHBOURHOOD_OWN, quotientNeighbourhoodOwn);
    json.putOpt(KEY_QUOTIENT_QUARTER_OWN, quotientQuarterOwn);
    json.putOpt(KEY_AVG_NEIGHBOURHOOD_VALUE, avgNeighbourhoodValue);
    json.putOpt(KEY_AVG_QUARTER_VALUE, avgQuarterValue);
    json.putOpt(KEY_OWN_PRICE_VALUE, ownPriceValue);

    return json;
  }

  private Double optionalDouble(JSONObject json, String key) throws JSONException {
    if (json.has(key)) {
      return json.getDouble(key);
    }
    return null;

  }
}
