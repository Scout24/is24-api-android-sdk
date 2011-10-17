package de.is24.restapi.android.sdk.http.response.handler;

import org.json.JSONException;
import org.json.JSONObject;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.PictureAttachment;
import de.is24.restapi.android.sdk.domain.PriceIntervalType;
import de.is24.restapi.android.sdk.domain.RealEstateState;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import android.test.AndroidTestCase;


public class MiniExposeHandlerHelperTest extends AndroidTestCase {
  private static final String MINIEXPOSE_RESULT_APARTMENT_BUY =
    "{\"@xsi.type\":\"search:ApartmentBuy\",\"@id\":\"58340610\",\"title\":\"2 Zimmerwohnung in schönen Altbau\",\"address\":{\"street\":\"Koppenstraße\",\"houseNumber\":\"99\",\"postcode\":\"10243\",\"city\":\"Berlin\",\"quarter\":\"Friedrichshain (Friedrichshain)\",\"wgs84Coordinate\":{\"latitude\":52.51594707314044,\"longitude\":13.43502876343391}},\"titlePicture\":{\"@creation\":\"2011-10-07T14:07:12.853+02:00\",\"@modification\":\"2011-10-07T14:07:12.853+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/79\\/407\\/184\\/79407184-4.jpg?2366024720\",\"urls\":[{\"url\":{\"@href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/odw002\\/N\\/79\\/407\\/184\\/79407184-3.jpg?2366024720\",\"@scale\":\"SCALE_118x118\"}}]},\"price\":{\"value\":79950,\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":48.16,\"numberOfRooms\":2,\"energyPerformanceCertificate\":\"true\",\"builtInKitchen\":\"false\",\"balcony\":\"false\",\"certificateOfEligibilityNeeded\":\"false\",\"garden\":\"false\",\"floorplan\":\"false\",\"courtage\":{\"hasCourtage\":\"YES\"}}";
  private static final String MINIEXPOSE_RESULT_CHANGED_ROOMS =
    "{\"@xsi.type\":\"search:ApartmentBuy\",\"@id\":\"58340610\",\"title\":\"2 Zimmerwohnung in schönen Altbau\",\"address\":{\"street\":\"Koppenstraße\",\"houseNumber\":\"99\",\"postcode\":\"10243\",\"city\":\"Berlin\",\"quarter\":\"Friedrichshain (Friedrichshain)\",\"wgs84Coordinate\":{\"latitude\":52.51594707314044,\"longitude\":13.43502876343391}},\"titlePicture\":{\"@creation\":\"2011-10-07T14:07:12.853+02:00\",\"@modification\":\"2011-10-07T14:07:12.853+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/79\\/407\\/184\\/79407184-4.jpg?2366024720\",\"urls\":[{\"url\":{\"@href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/odw002\\/N\\/79\\/407\\/184\\/79407184-3.jpg?2366024720\",\"@scale\":\"SCALE_118x118\"}}]},\"price\":{\"value\":79950,\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":48.16,\"numberOfRooms\":4,\"energyPerformanceCertificate\":\"true\",\"builtInKitchen\":\"false\",\"balcony\":\"false\",\"certificateOfEligibilityNeeded\":\"false\",\"garden\":\"false\",\"floorplan\":\"false\",\"courtage\":{\"hasCourtage\":\"YES\"}}";
  private static final String MINIEXPOSE_RESULT_CHANGED_PRICE_INTERVAL =
    "{\"value\":79950,\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"YEAR\"}";
  private static final String MINIEXPOSE_NOT_PARSABLE_JSON =
    "{\"@xsiiiii.type\":\"search:ApartmentBuy\",\"@id\":\"58340610\",\"title\":\"2 Zimmerwohnung in schönen Altbau\",\"address\":{\"street\":\"Koppenstraße\",\"houseNumber\":\"99\",\"postcode\":\"10243\",\"city\":\"Berlin\",\"quarter\":\"Friedrichshain (Friedrichshain)\",\"wgs84Coordinate\":{\"latitude\":52.51594707314044,\"longitude\":13.43502876343391}},\"titlePicture\":{\"@creation\":\"2011-10-07T14:07:12.853+02:00\",\"@modification\":\"2011-10-07T14:07:12.853+02:00\",\"@id\":\"\",\"@xlink.href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/result002\\/N\\/79\\/407\\/184\\/79407184-4.jpg?2366024720\",\"urls\":[{\"url\":{\"@href\":\"http:\\/\\/picture.immobilienscout24.de\\/files\\/odw002\\/N\\/79\\/407\\/184\\/79407184-3.jpg?2366024720\",\"@scale\":\"SCALE_118x118\"}}]},\"price\":{\"value\":79950,\"currency\":\"EUR\",\"marketingType\":\"PURCHASE\",\"priceIntervalType\":\"ONE_TIME_CHARGE\"},\"livingSpace\":48.16,\"numberOfRooms\":2,\"energyPerformanceCertificate\":\"true\",\"builtInKitchen\":\"false\",\"balcony\":\"false\",\"certificateOfEligibilityNeeded\":\"false\",\"garden\":\"false\",\"floorplan\":\"false\",\"courtage\":{\"hasCourtage\":\"YES\"}}";

  private JSONObject json;

  protected void setUp() throws JSONException {
    LibraryContext.getInstance().initApplicationContext(getContext());
    json = new JSONObject(MINIEXPOSE_RESULT_APARTMENT_BUY);
  }

  public void testParseMiniExpose() throws JSONException {
    MiniExpose miniExpose = MiniExposeHandlerHelper.parseMiniExpose(json);
    assertEquals("58340610", miniExpose.id);
    assertEquals(RealEstateState.ACTIVE, miniExpose.state);
    assertFalse(miniExpose.isFavorite);
    assertEquals(RealEstateType.ApartmentBuy, miniExpose.realEstateType);
    assertEquals("2 Zimmerwohnung in schönen Altbau", miniExpose.getString(MiniExposeAttributes.TITLE));

    assertEquals("Koppenstraße", miniExpose.getString(MiniExposeAttributes.OBJECT_STREET));
    assertEquals("99", miniExpose.getString(MiniExposeAttributes.OBJECT_HOUSE_NUMBER));
    assertEquals("10243", miniExpose.getString(MiniExposeAttributes.OBJECT_POSTCODE));
    assertEquals("Berlin", miniExpose.getString(MiniExposeAttributes.OBJECT_CITY));
    assertEquals("52.51594707314044", miniExpose.getString(MiniExposeAttributes.LATITUDE));
    assertEquals("13.43502876343391", miniExpose.getString(MiniExposeAttributes.LONGITUDE));
    assertEquals("http://picture.immobilienscout24.de/files/result002/N/79/407/184/79407184-4.jpg?2366024720",
      miniExpose.get(MiniExposeAttributes.TITLE_PICTURE, PictureAttachment.class).url);
    assertEquals("48,16 m²", miniExpose.getString(MiniExposeAttributes.LIVING_SPACE));
    assertEquals("2", miniExpose.getString(MiniExposeAttributes.NUMBER_OF_ROOMS));
    assertEquals("79.950,00 €", miniExpose.getString(MiniExposeAttributes.PRICE));
    assertEquals(PriceIntervalType.ONE_TIME_CHARGE,
      (PriceIntervalType) miniExpose.opt(MiniExposeAttributes.PRICE_INTERVAL_TYPE, PriceIntervalType.DAY));
  }

  public void testSetAndGetMiniExposeAttributes() throws JSONException {
    JSONObject jsonChangedRooms = new JSONObject(MINIEXPOSE_RESULT_CHANGED_ROOMS);
    MiniExpose miniExpose = MiniExposeHandlerHelper.parseMiniExpose(json);
    MiniExposeHandlerHelper.setOptionalStringValue(miniExpose, MiniExposeAttributes.NUMBER_OF_ROOMS, jsonChangedRooms);

    assertEquals("4", miniExpose.getString(MiniExposeAttributes.NUMBER_OF_ROOMS));
    assertEquals("58340610", miniExpose.id);
    assertEquals(RealEstateState.ACTIVE, miniExpose.state);
    assertFalse(miniExpose.isFavorite);
    assertEquals(RealEstateType.ApartmentBuy, miniExpose.realEstateType);
    assertEquals("2 Zimmerwohnung in schönen Altbau", miniExpose.getString(MiniExposeAttributes.TITLE));
    assertEquals("Koppenstraße", miniExpose.getString(MiniExposeAttributes.OBJECT_STREET));
    assertEquals("99", miniExpose.getString(MiniExposeAttributes.OBJECT_HOUSE_NUMBER));
    assertEquals("10243", miniExpose.getString(MiniExposeAttributes.OBJECT_POSTCODE));
    assertEquals("Berlin", miniExpose.getString(MiniExposeAttributes.OBJECT_CITY));
    assertEquals("52.51594707314044", miniExpose.getString(MiniExposeAttributes.LATITUDE));
    assertEquals("13.43502876343391", miniExpose.getString(MiniExposeAttributes.LONGITUDE));
    assertEquals("http://picture.immobilienscout24.de/files/result002/N/79/407/184/79407184-4.jpg?2366024720",
      miniExpose.get(MiniExposeAttributes.TITLE_PICTURE, PictureAttachment.class).url);
    assertEquals("48,16 m²", miniExpose.getString(MiniExposeAttributes.LIVING_SPACE));
    assertEquals("79.950,00 €", miniExpose.getString(MiniExposeAttributes.PRICE));
    assertEquals(PriceIntervalType.ONE_TIME_CHARGE,
      (PriceIntervalType) miniExpose.opt(MiniExposeAttributes.PRICE_INTERVAL_TYPE, PriceIntervalType.DAY));

    JSONObject jsonChangedPriceInterval = new JSONObject(MINIEXPOSE_RESULT_CHANGED_PRICE_INTERVAL);
    MiniExposeHandlerHelper.opt(miniExpose, MiniExposeAttributes.PRICE_INTERVAL_TYPE, jsonChangedPriceInterval);

    assertEquals(PriceIntervalType.YEAR,
      (PriceIntervalType) miniExpose.opt(MiniExposeAttributes.PRICE_INTERVAL_TYPE, PriceIntervalType.DAY));
    assertEquals("4", miniExpose.getString(MiniExposeAttributes.NUMBER_OF_ROOMS));
    assertEquals("58340610", miniExpose.id);
    assertEquals(RealEstateState.ACTIVE, miniExpose.state);
    assertFalse(miniExpose.isFavorite);
    assertEquals(RealEstateType.ApartmentBuy, miniExpose.realEstateType);
    assertEquals("2 Zimmerwohnung in schönen Altbau", miniExpose.getString(MiniExposeAttributes.TITLE));
    assertEquals("Koppenstraße", miniExpose.getString(MiniExposeAttributes.OBJECT_STREET));
    assertEquals("99", miniExpose.getString(MiniExposeAttributes.OBJECT_HOUSE_NUMBER));
    assertEquals("10243", miniExpose.getString(MiniExposeAttributes.OBJECT_POSTCODE));
    assertEquals("Berlin", miniExpose.getString(MiniExposeAttributes.OBJECT_CITY));
    assertEquals("52.51594707314044", miniExpose.getString(MiniExposeAttributes.LATITUDE));
    assertEquals("13.43502876343391", miniExpose.getString(MiniExposeAttributes.LONGITUDE));
    assertEquals("http://picture.immobilienscout24.de/files/result002/N/79/407/184/79407184-4.jpg?2366024720",
      miniExpose.get(MiniExposeAttributes.TITLE_PICTURE, PictureAttachment.class).url);
    assertEquals("48,16 m²", miniExpose.getString(MiniExposeAttributes.LIVING_SPACE));
    assertEquals("79.950,00 €", miniExpose.getString(MiniExposeAttributes.PRICE));
  }

  public void testParseMiniExposeWithParseException() throws JSONException {
    JSONObject jsonNotValid = new JSONObject(MINIEXPOSE_NOT_PARSABLE_JSON);
    MiniExpose miniExpose = null;
    try {
      miniExpose = MiniExposeHandlerHelper.parseMiniExpose(jsonNotValid);
      fail();
    } catch (JSONException e) {
      assertNull(miniExpose);
    }

  }
}
