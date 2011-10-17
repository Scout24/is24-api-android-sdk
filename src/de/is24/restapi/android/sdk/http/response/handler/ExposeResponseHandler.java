package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;
import android.util.Log;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.R;
import de.is24.restapi.android.sdk.domain.ApartmentAttributes;
import de.is24.restapi.android.sdk.domain.ApartmentBuyExposeAttributes;
import de.is24.restapi.android.sdk.domain.Attachment;
import de.is24.restapi.android.sdk.domain.AttributeEnum;
import de.is24.restapi.android.sdk.domain.Expose;
import de.is24.restapi.android.sdk.domain.FiringType;
import de.is24.restapi.android.sdk.domain.HouseAttibutes;
import de.is24.restapi.android.sdk.domain.HouseBuyExposeAttributes;
import de.is24.restapi.android.sdk.domain.LivingAttibutes;
import de.is24.restapi.android.sdk.domain.LivingBuyAttributes;
import de.is24.restapi.android.sdk.domain.LivingExtendedAttributes;
import de.is24.restapi.android.sdk.domain.LivingRentAttributes;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.PdfAttachment;
import de.is24.restapi.android.sdk.domain.PictureAttachment;
import de.is24.restapi.android.sdk.domain.RealEstateState;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.domain.ReferencePrice;
import de.is24.restapi.android.sdk.domain.SiteAttributes;
import de.is24.restapi.android.sdk.domain.SiteRecommendedUseType;
import de.is24.restapi.android.sdk.domain.ValueEnum;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.util.Formatter;


public class ExposeResponseHandler extends JsonResponseHandler<Expose> {
  private static final String TAG = ExposeResponseHandler.class.getSimpleName();
  private static final int FORMAT_TYPE_DEFAULT = -1;
  private static final int FORMAT_TYPE_NUMBER = 1;
  private static final int FORMAT_TYPE_CURRENCY = 2;
  private static final int FORMAT_TYPE_AREA = 3;
  private static final int FORMAT_TYPE_THERMAL_CHARACTERISTIC = 4;
  private static final String JSON_KEY_ROOT_ELEMENT = "expose.expose";
  private static final String JSON_KEY_REALESTATE_ID = "@id";
  private static final String JSON_KEY_REALESTATE_META_DATA = "realEstate";
  private static final String JSON_KEY_REALESTATE_TYPE = "@xsi.type";
  private static final String JSON_KEY_ADDRESS_META_DATA = "address";
  private static final String JSON_KEY_GEOHIERARCHY_META_DATA = "geoHierarchy";
  private static final String JSON_KEY_GEOHIERARCHY_ID = "geoCodeId";
  private static final String JSON_KEY_WGS84_COORDINATES = "wgs84Coordinate";
  private static final String JSON_KEY_ATTACHMENTS = "attachments";
  private static final String JSON_KEY_ATTACHMENT_ARRAY = "attachment";
  private static final String JSON_KEY_ATTACHMENT_TYPE = "@xsi.type";
  private static final String JSON_KEY_ATTACHMENT_TITLE = "title";
  private static final String JSON_KEY_ATTACHMENT_FLOORPLAN = "floorplan";
  private static final String JSON_KEY_URLS_ARRAY = "urls";
  private static final String JSON_KEY_URL = "url";
  private static final String JSON_KEY_IMAGE_LINK = "@href";
  private static final String JSON_KEY_IMAGE_SCALE = "@scale";
  private static final String JSON_KEY_STATE = "state";
  private static final String JSON_KEY_COURTAGE_META_DATA = "courtage";
  private static final String JSON_KEY_CONTACT_DETAILS_META_DATA = "contactDetails";
  private static final String JSON_KEY_PRICE_VALUE = "value";
  private static final String JSON_KEY_FIRING_TYPE_ARRAY_KEY = "firingType";
  private static final String JSON_KEY_SITES_RECOMMENDED_USE_TYPE_ARRAY_KEY = "siteRecommendedUseType";

  public Response<Expose> handleResponse(InputStream response, int responseCode, Response<Expose> result)
    throws IOException {
    result.statusCode = responseCode;
    Log.d(TAG, "statuscode=" + result.statusCode);
    if (result.statusCode == HttpStatus.SC_OK) {
      try {
        JSONObject jsonResponse = parseJson(response);
        Expose expose = null;
        result.result = expose;

        JSONObject rootObject = jsonResponse.getJSONObject(JSON_KEY_ROOT_ELEMENT);
        JSONObject realEstateMetaData = rootObject.optJSONObject(JSON_KEY_REALESTATE_META_DATA);
        String realEstateString = realEstateMetaData.getString(JSON_KEY_REALESTATE_TYPE);
        try {
          expose = new Expose(RealEstateType.valueOf(realEstateString.substring(realEstateString.indexOf(":") + 1)));
        } catch (IllegalArgumentException e) {
          Log.w(TAG, "cannot parse realestatetype", e);
          result.statusCode = ServiceException.UNKNOWN_TYPE;
          return result;
        }
        expose.id = rootObject.optString(JSON_KEY_REALESTATE_ID);

        switch (expose.realEstateType) {
          case ApartmentRent: {
            fillApartmentRentAttributes(realEstateMetaData, expose);
            break;
          }

          case ApartmentBuy: {
            fillApartmentBuyAttributes(realEstateMetaData, expose);
            break;
          }

          case HouseBuy: {
            fillHouseBuyAttributes(realEstateMetaData, expose);
            break;
          }

          case HouseRent: {
            fillHouseRentAttributes(realEstateMetaData, expose);
            break;
          }

          case LivingRentSite: {
            fillSiteRentAttributes(realEstateMetaData, expose);
            break;
          }

          case LivingBuySite: {
            fillSiteBuyAttributes(realEstateMetaData, expose);
            break;
          }

          default: {
            Log.w(TAG, "unallowed realestatetype:" + expose.realEstateType);
            result.statusCode = ServiceException.UNKNOWN_TYPE;
            return result;
          }
        }
        opt(expose, LivingAttibutes.CONTACT_FORM_TYPE, rootObject);
        optString(expose, MiniExposeAttributes.CREATION_DATE, realEstateMetaData);
        optString(expose, MiniExposeAttributes.MODIFICATION_DATE, realEstateMetaData);

        addContactDetails(expose, rootObject.optJSONObject(JSON_KEY_CONTACT_DETAILS_META_DATA));

        addAttachments(expose, getJSONArrayForObject(realEstateMetaData.opt(JSON_KEY_ATTACHMENTS)));

        //        Header etagHeader = response.getFirstHeader(HttpParameters.ETAG);
        //        Log.d(TAG, "Etag: " + etagHeader);
        //        if (null != etagHeader) {
        //          expose.etag = etagHeader.getValue();
        //        }
        result.success = true;
        result.result = expose;
      } catch (Exception e) {
        if (e instanceof IOException) {
          throw (IOException) e;
        }
        Log.e(TAG, "cannot parse json", e);
        result.statusCode = ServiceException.PARSING_ERROR;
        result.optionalMessage = e.getMessage();
      }
    } else if (result.statusCode == HttpStatus.SC_NOT_IMPLEMENTED) {
      Log.w(TAG, "searched realesttetpe not implemented");
      result.statusCode = ServiceException.UNKNOWN_TYPE;
    }
    return result;
  }

  private void addContactDetails(Expose expose, JSONObject contactDetailsMetaData) throws JSONException {
    if (contactDetailsMetaData != null) {
      optString(expose, LivingAttibutes.CONTACT_FIRSTNAME, contactDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_LASTNAME, contactDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_COMPANY, contactDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_CELL_PHONE, contactDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_PHONE, contactDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_REALTOR_LOGO, contactDetailsMetaData);
      addContactAddressDetails(expose, contactDetailsMetaData.optJSONObject(JSON_KEY_ADDRESS_META_DATA));
    }
  }

  private void addContactAddressDetails(Expose expose, JSONObject contactAddressDetailsMetaData) throws JSONException {
    if (contactAddressDetailsMetaData != null) {
      optString(expose, LivingAttibutes.CONTACT_ADDRESS_CITY, contactAddressDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_ADDRESS_HOUSENUMBER, contactAddressDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_ADDRESS_STREET, contactAddressDetailsMetaData);
      optString(expose, LivingAttibutes.CONTACT_ADDRESS_ZIPCODE, contactAddressDetailsMetaData);
    }
  }

  private void addAttachments(Expose expose, JSONArray attachmentsJsonArray) throws JSONException {
    if (attachmentsJsonArray != null) {
      expose.attachments = new ArrayList<Attachment>();
      for (int x = 0; x < attachmentsJsonArray.length(); x++) {
        JSONObject attachment = (JSONObject) attachmentsJsonArray.get(x);
        JSONArray attachmentUrlsArray = getJSONArrayForObject(attachment.opt(JSON_KEY_ATTACHMENT_ARRAY));
        if (null == attachmentUrlsArray) {
          break;
        }
        for (int y = 0; y < attachmentUrlsArray.length(); y++) {
          JSONObject urlsElement = attachmentUrlsArray.optJSONObject(y);
          String type = urlsElement.optString(JSON_KEY_ATTACHMENT_TYPE);
          if (!TextUtils.isEmpty(type)) {
            if ("common:Picture".equals(type)) {
              addPictureAttachment(expose, urlsElement);
            } else if ("common:PDFDocument".equals(type)) {
              PdfAttachment pdfAttachment = new PdfAttachment();
              pdfAttachment.caption = urlsElement.optString(JSON_KEY_ATTACHMENT_TITLE);
              pdfAttachment.isFloorplan = urlsElement.optBoolean(JSON_KEY_ATTACHMENT_FLOORPLAN, false);
              pdfAttachment.url = urlsElement.optString(JSON_KEY_URL);
              expose.attachments.add(pdfAttachment);
            }
          }
        }
      }
    }
  }

  private void addPictureAttachment(Expose expose, JSONObject urlsElement) {
    PictureAttachment picAttachment = new PictureAttachment();
    picAttachment.caption = urlsElement.optString(JSON_KEY_ATTACHMENT_TITLE);
    picAttachment.isFloorplan = urlsElement.optBoolean(JSON_KEY_ATTACHMENT_FLOORPLAN, false);

    JSONArray urlArray = getJSONArrayForObject(urlsElement.opt(JSON_KEY_URLS_ARRAY));
    for (int z = 0; z < urlArray.length(); z++) {
      JSONObject urlElement = urlArray.optJSONObject(z);
      JSONArray urlMetaDataArray = getJSONArrayForObject(urlElement.opt(JSON_KEY_URL));
      for (int a = 0; a < urlMetaDataArray.length(); a++) {
        JSONObject urlMetaDataElement = urlMetaDataArray.optJSONObject(a);
        String scale = urlMetaDataElement.optString(JSON_KEY_IMAGE_SCALE);
        if (scale.equals("SCALE_60x60")) {
          picAttachment.url = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
        } else if (scale.equals("SCALE_210x210")) {
          picAttachment.exposeUrl = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
        } else if (scale.equals("SCALE_540x540")) {
          picAttachment.largeUrl = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
        }
      }
      expose.attachments.add(picAttachment);
    }
  }

  private void fillApartmentRentAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralApartmentAttributes(realEstateMetaData, expose);

    optFormattedString(expose, LivingRentAttributes.BASE_RENT, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    if (expose.has(LivingAttibutes.REFERENCE_PRICE) && !expose.has(MiniExposeAttributes.PRICE)) {
      if (realEstateMetaData.has(LivingRentAttributes.BASE_RENT.getRestapiName())) {
        ReferencePrice refPrice = expose.get(LivingAttibutes.REFERENCE_PRICE, ReferencePrice.class);
        fillReferencePrice(refPrice, realEstateMetaData,
          realEstateMetaData.getDouble(LivingRentAttributes.BASE_RENT.getRestapiName()));
      } else {
        expose.remove(LivingAttibutes.REFERENCE_PRICE);
      }
    }
    optFormattedString(expose, LivingRentAttributes.TOTAL_RENT, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    optFormattedString(expose, LivingRentAttributes.SERVICE_CHARGE, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    optString(expose, LivingRentAttributes.DEPOSIT, realEstateMetaData);
    optFormattedString(expose, LivingRentAttributes.HEATING_COSTS, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    opt(expose, LivingRentAttributes.HEATING_COSTS_IN_SERVICE_CHARGE, realEstateMetaData);
    opt(expose, LivingRentAttributes.PETS_ALLOWED, realEstateMetaData);
  }

  private void fillApartmentBuyAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralApartmentAttributes(realEstateMetaData, expose);

    opt(expose, LivingBuyAttributes.RENTED, realEstateMetaData);
    optFormattedString(expose, LivingBuyAttributes.RENTAL_INCOME, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    opt(expose, LivingBuyAttributes.LISTED, realEstateMetaData);
    opt(expose, LivingBuyAttributes.SUMMER_RESIDENCE_PRACTICAL, realEstateMetaData);
    optFormattedString(expose, ApartmentBuyExposeAttributes.RENT_SUBSIDY, realEstateMetaData, FORMAT_TYPE_NUMBER);
  }

  private void fillHouseRentAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralHouseAttributes(realEstateMetaData, expose);

    opt(expose, LivingExtendedAttributes.ASSISTED_LIVING, realEstateMetaData);
    opt(expose, LivingExtendedAttributes.BUILT_IN_KITCHEN, realEstateMetaData);
    optFormattedString(expose, LivingRentAttributes.BASE_RENT, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    optFormattedString(expose, LivingRentAttributes.TOTAL_RENT, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    optFormattedString(expose, LivingRentAttributes.SERVICE_CHARGE, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    optString(expose, LivingRentAttributes.DEPOSIT, realEstateMetaData);
    optFormattedString(expose, LivingRentAttributes.HEATING_COSTS, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    opt(expose, LivingRentAttributes.HEATING_COSTS_IN_SERVICE_CHARGE, realEstateMetaData);
    opt(expose, LivingRentAttributes.PETS_ALLOWED, realEstateMetaData);
  }

  private void fillHouseBuyAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralHouseAttributes(realEstateMetaData, expose);

    opt(expose, HouseBuyExposeAttributes.LODGER_FLAT, realEstateMetaData);
    opt(expose, HouseBuyExposeAttributes.CONSTRUCTION_PHASE, realEstateMetaData);
    opt(expose, LivingBuyAttributes.RENTED, realEstateMetaData);
    optFormattedString(expose, LivingBuyAttributes.RENTAL_INCOME, realEstateMetaData, FORMAT_TYPE_CURRENCY);
    opt(expose, LivingBuyAttributes.LISTED, realEstateMetaData);
    opt(expose, LivingBuyAttributes.SUMMER_RESIDENCE_PRACTICAL, realEstateMetaData);
  }

  private void fillSiteRentAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralSiteAttributes(realEstateMetaData, expose);
  }

  private void fillSiteBuyAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralSiteAttributes(realEstateMetaData, expose);
  }


  private void fillGeneralApartmentAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralAttributes(realEstateMetaData, expose);

    opt(expose, ApartmentAttributes.APARTMENT_TYPE, realEstateMetaData);
    optFormattedString(expose, ApartmentAttributes.FLOOR, realEstateMetaData, FORMAT_TYPE_NUMBER);
    opt(expose, ApartmentAttributes.LIFT, realEstateMetaData);
    opt(expose, LivingExtendedAttributes.ASSISTED_LIVING, realEstateMetaData);
    opt(expose, LivingExtendedAttributes.BUILT_IN_KITCHEN, realEstateMetaData);
    opt(expose, ApartmentAttributes.BALCONY, realEstateMetaData);
    opt(expose, ApartmentAttributes.GARDEN, realEstateMetaData);
    opt(expose, ApartmentAttributes.CERTIFICATE_OF_ELIGIBILITY_NEEDED, realEstateMetaData);
  }

  private void fillGeneralHouseAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralAttributes(realEstateMetaData, expose);

    opt(expose, HouseAttibutes.BUILDING_TYPE, realEstateMetaData);
    optFormattedString(expose, MiniExposeAttributes.PLOT_AREA, realEstateMetaData, FORMAT_TYPE_AREA);
  }

  private void fillGeneralSiteAttributes(JSONObject realEstateMetaData, Expose expose) throws JSONException {
    fillGeneralAttributes(realEstateMetaData, expose);

    opt(expose, SiteAttributes.BUILDING_PERMISSION, realEstateMetaData);
    opt(expose, SiteAttributes.DEMOLITION, realEstateMetaData);
    optFormattedString(expose, SiteAttributes.GFZ, realEstateMetaData, FORMAT_TYPE_NUMBER);
    optFormattedString(expose, SiteAttributes.GRZ, realEstateMetaData, FORMAT_TYPE_NUMBER);
    opt(expose, SiteAttributes.LEASE_INTERVAL, realEstateMetaData);
    opt(expose, SiteAttributes.SHORT_TERM_CONSTRUCTABLE, realEstateMetaData);
    opt(expose, SiteAttributes.SITE_CONSTRUCTABLE_TYPE, realEstateMetaData);
    opt(expose, SiteAttributes.SITE_DEVELOPMENT_TYPE, realEstateMetaData);
    optFormattedString(expose, SiteAttributes.MIN_DIVISIBLE, realEstateMetaData, FORMAT_TYPE_AREA);
    optList(expose, SiteAttributes.RECOMMENDED_USE_TYPES, JSON_KEY_SITES_RECOMMENDED_USE_TYPE_ARRAY_KEY,
      realEstateMetaData);
    optFormattedString(expose, SiteAttributes.TENANCY, realEstateMetaData, FORMAT_TYPE_NUMBER);
    optFormattedString(expose, MiniExposeAttributes.PLOT_AREA, realEstateMetaData, FORMAT_TYPE_AREA);
  }

  private void fillReferencePrice(ReferencePrice refPrice, JSONObject metaData, double price) throws JSONException {
    if (metaData.has(MiniExposeAttributes.LIVING_SPACE.getRestapiName())) {
      refPrice.ownPriceValue = price /
        metaData.getDouble(MiniExposeAttributes.LIVING_SPACE.getRestapiName());
    } else if (metaData.has(LivingAttibutes.USABLE_FLOOR_SPACE.getRestapiName())) {
      refPrice.ownPriceValue = price /
        metaData.getDouble(LivingAttibutes.USABLE_FLOOR_SPACE.getRestapiName());
    }
    refPrice.ownPrice = Formatter.sqmCurrencyFormatter.format(refPrice.ownPriceValue);
  }

  private void fillGeneralAttributes(JSONObject realEstate, Expose expose) throws JSONException {
    addAddressInformation(expose, realEstate.optJSONObject(JSON_KEY_ADDRESS_META_DATA));

    boolean hasRefPrice = false;
    ReferencePrice refPrice = null;
    if (expose.realEstateType.hasReferencePrice && expose.has(LivingAttibutes.GEOCODE_ID_REGION) &&
        expose.has(LivingAttibutes.GEOCODE_ID_CITY) && expose.has(LivingAttibutes.GEOCODE_ID_QUARTER) &&
        (realEstate.has(LivingAttibutes.USABLE_FLOOR_SPACE.getRestapiName()) ||
          realEstate.has(MiniExposeAttributes.LIVING_SPACE.getRestapiName())) &&
        (realEstate.has(MiniExposeAttributes.PRICE.getRestapiName()) ||
          realEstate.has(LivingRentAttributes.BASE_RENT.getRestapiName()))) {
      refPrice = new ReferencePrice();
      expose.put(LivingAttibutes.REFERENCE_PRICE, refPrice);
      hasRefPrice = true;
    }

    optString(expose, LivingAttibutes.OTHER_NOTE, realEstate);
    optString(expose, LivingAttibutes.DESCRIPTION_NOTE, realEstate);
    optString(expose, LivingAttibutes.FURNISHING_NOTE, realEstate);
    optString(expose, LivingAttibutes.LOCATION_NOTE, realEstate);
    optFormattedString(expose, LivingAttibutes.USABLE_FLOOR_SPACE, realEstate, FORMAT_TYPE_AREA);

    if (realEstate.has(MiniExposeAttributes.PRICE.getRestapiName())) {
      JSONObject resultlistEntryPriceData = realEstate.getJSONObject(MiniExposeAttributes.PRICE.getRestapiName());
      expose.put(MiniExposeAttributes.PRICE,
        Formatter.reformatCurrency(resultlistEntryPriceData.getString(JSON_KEY_PRICE_VALUE)));
      if (expose.realEstateType.equalsOne(RealEstateType.LivingBuySite, RealEstateType.LivingRentSite)) {
        opt(expose, MiniExposeAttributes.PRICE_INTERVAL_TYPE, resultlistEntryPriceData);
      }
      if (hasRefPrice) {
        fillReferencePrice(refPrice, realEstate, resultlistEntryPriceData.getDouble(JSON_KEY_PRICE_VALUE));
      }
    }

    expose.state = RealEstateState.valueOf(realEstate.optString(JSON_KEY_STATE, "INACTIVE"));
    optString(expose, MiniExposeAttributes.TITLE, realEstate);
    optString(expose, LivingAttibutes.EXTERNAL_ID, realEstate);
    optFormattedString(expose, MiniExposeAttributes.LIVING_SPACE, realEstate, FORMAT_TYPE_AREA);
    optFormattedString(expose, MiniExposeAttributes.NUMBER_OF_ROOMS, realEstate, FORMAT_TYPE_NUMBER);
    opt(expose, LivingAttibutes.ENERGY_PERFORMANCE_CERTIFICATE, realEstate);
    opt(expose, LivingAttibutes.BUILDING_ENERGY_RATING_TYPE, realEstate);
    optFormattedString(expose, LivingAttibutes.THERMAL_CHARACTERISTICS, realEstate, FORMAT_TYPE_THERMAL_CHARACTERISTIC);
    opt(expose, LivingAttibutes.ENERGY_CONSUMPTION_CONTAINS_WARM_WATER, realEstate);
    opt(expose, LivingAttibutes.FLOORPLAN, realEstate);
    opt(expose, LivingAttibutes.COMMERCIALIZATION_TYPE, realEstate);

    JSONObject courtageMetaData = realEstate.optJSONObject(JSON_KEY_COURTAGE_META_DATA);
    if (courtageMetaData != null) {
      opt(expose, LivingAttibutes.HAS_COURTAGE, courtageMetaData);
      optString(expose, LivingAttibutes.COURTAGE, courtageMetaData);
      optString(expose, LivingAttibutes.COURTAGE_NOTE, courtageMetaData);
    }

    if (realEstate.has(MiniExposeAttributes.TITLE_PICTURE.getRestapiName())) {
      JSONObject resultListEntryTitlePictureData = realEstate.getJSONObject(MiniExposeAttributes.TITLE_PICTURE
        .getRestapiName());
      PictureAttachment pic = new PictureAttachment();
      pic.caption = resultListEntryTitlePictureData.optString(JSON_KEY_ATTACHMENT_TITLE);
      pic.isFloorplan = resultListEntryTitlePictureData.optBoolean(JSON_KEY_ATTACHMENT_FLOORPLAN, false);

      JSONArray urlArray = getJSONArrayForObject(resultListEntryTitlePictureData.opt(JSON_KEY_URLS_ARRAY));
      for (int z = 0; z < urlArray.length(); z++) {
        JSONObject urlElement = urlArray.optJSONObject(z);
        JSONArray urlMetaDataArray = getJSONArrayForObject(urlElement.opt(JSON_KEY_URL));
        for (int a = 0; a < urlMetaDataArray.length(); a++) {
          JSONObject urlMetaDataElement = urlMetaDataArray.optJSONObject(a);
          String scale = urlMetaDataElement.optString(JSON_KEY_IMAGE_SCALE);
          if ("SCALE_60x60".equals(scale)) {
            pic.url = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
          } else if ("SCALE_210x210".equals(scale)) {
            pic.exposeUrl = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
          } else if ("SCALE_540x540".equals(scale)) {
            pic.largeUrl = urlMetaDataElement.optString(JSON_KEY_IMAGE_LINK);
          }
        }
      }
      expose.put(MiniExposeAttributes.TITLE_PICTURE, pic);
    }

    opt(expose, LivingAttibutes.CELLAR, realEstate);
    opt(expose, LivingAttibutes.HANDICAPPED_ACCESSIBLE, realEstate);
    optFormattedString(expose, LivingAttibutes.NUMBER_OF_PARKING_SPACES, realEstate, FORMAT_TYPE_NUMBER);
    opt(expose, LivingAttibutes.CONDITION, realEstate);
    optString(expose, LivingAttibutes.LAST_REFURBISHMENT, realEstate);
    opt(expose, LivingAttibutes.INTERIOR_QUALITY, realEstate);
    optString(expose, LivingAttibutes.CONSTRUCTION_YEAR, realEstate);
    optString(expose, LivingAttibutes.FREE_FROM, realEstate);
    opt(expose, LivingAttibutes.HEATING_TYPE, realEstate);
    optList(expose, LivingAttibutes.FIRING_TYPES, JSON_KEY_FIRING_TYPE_ARRAY_KEY, realEstate);
    optFormattedString(expose, LivingAttibutes.NUMBER_OF_BEDROOMS, realEstate, FORMAT_TYPE_NUMBER);
    optFormattedString(expose, LivingAttibutes.NUMBER_OF_BATHROOMS, realEstate, FORMAT_TYPE_NUMBER);
    optFormattedString(expose, LivingAttibutes.NUMBER_OF_FLOORS, realEstate, FORMAT_TYPE_NUMBER);
    opt(expose, LivingAttibutes.GUEST_TOILET, realEstate);
    opt(expose, LivingAttibutes.PARKING_SPACE_TYPE, realEstate);
    optFormattedString(expose, LivingAttibutes.PARKING_SPACE_PRICE, realEstate, FORMAT_TYPE_CURRENCY);

  }

  private void addAddressInformation(Expose expose, JSONObject realEstateAddressMetaData) throws JSONException {
    if (realEstateAddressMetaData != null) {
      optString(expose, MiniExposeAttributes.OBJECT_STREET, realEstateAddressMetaData);
      optString(expose, MiniExposeAttributes.OBJECT_HOUSE_NUMBER, realEstateAddressMetaData);
      optString(expose, MiniExposeAttributes.OBJECT_POSTCODE, realEstateAddressMetaData);
      optString(expose, MiniExposeAttributes.OBJECT_CITY, realEstateAddressMetaData);
      addGeoHierarchyInformation(expose, realEstateAddressMetaData.optJSONObject(JSON_KEY_GEOHIERARCHY_META_DATA));

      JSONObject resultListEntryWgsData = realEstateAddressMetaData.optJSONObject(JSON_KEY_WGS84_COORDINATES);
      if (resultListEntryWgsData != null) {
        optString(expose, MiniExposeAttributes.LATITUDE, resultListEntryWgsData);
        optString(expose, MiniExposeAttributes.LONGITUDE, resultListEntryWgsData);
      }
    }
  }

  private void addGeoHierarchyInformation(Expose expose, JSONObject realEstateGeoHierarchyMetaData)
    throws JSONException {
    if (realEstateGeoHierarchyMetaData != null) {
      addGeoHierarchyAttribute(expose, LivingAttibutes.GEOCODE_ID_REGION,
        realEstateGeoHierarchyMetaData.optJSONObject(LivingAttibutes.GEOCODE_ID_REGION.getRestapiName()));
      addGeoHierarchyAttribute(expose, LivingAttibutes.GEOCODE_ID_CITY,
        realEstateGeoHierarchyMetaData.optJSONObject(LivingAttibutes.GEOCODE_ID_CITY.getRestapiName()));
      addGeoHierarchyAttribute(expose, LivingAttibutes.GEOCODE_ID_QUARTER,
        realEstateGeoHierarchyMetaData.optJSONObject(LivingAttibutes.GEOCODE_ID_QUARTER.getRestapiName()));
      addGeoHierarchyAttribute(expose, LivingAttibutes.GEOCODE_ID_NEIGHBOURHOOD,
        realEstateGeoHierarchyMetaData.optJSONObject(LivingAttibutes.GEOCODE_ID_NEIGHBOURHOOD.getRestapiName()));
    }
  }

  private void addGeoHierarchyAttribute(Expose expose, AttributeEnum attrib, JSONObject geoHierarchyAttributeMetaData)
    throws JSONException {
    if (geoHierarchyAttributeMetaData != null) {
      if (geoHierarchyAttributeMetaData.has(JSON_KEY_GEOHIERARCHY_ID)) {
        expose.put(attrib, geoHierarchyAttributeMetaData.getString(JSON_KEY_GEOHIERARCHY_ID));
        if (attrib.equals(LivingAttibutes.GEOCODE_ID_QUARTER)) {
          expose.put(LivingAttibutes.GEOCODE_ID_FULL,
            geoHierarchyAttributeMetaData.getString(LivingAttibutes.GEOCODE_ID_FULL.getRestapiName()));
        }
      }
    }
  }

  @Override
  protected String getTag() {
    return TAG;
  }

  private void optString(final MiniExpose expose, final AttributeEnum attrib, final JSONObject json)
    throws JSONException {
    optFormattedString(expose, attrib, json, FORMAT_TYPE_DEFAULT);
  }

  private void optFormattedString(final MiniExpose expose, final AttributeEnum attrib, final JSONObject json,
    final int formatType) throws JSONException {
    if (json.has(attrib.getRestapiName())) {
      String value = json.getString(attrib.getRestapiName());
      switch (formatType) {
        case FORMAT_TYPE_NUMBER: {
          value = Formatter.reformatNumber(value);
          break;
        }

        case FORMAT_TYPE_AREA: {
          value = Formatter.reformatNumber(value) + " " +
            LibraryContext.getInstance().getApplicationContext().getResources().getString(R.string.suffix_area);
          break;
        }

        case FORMAT_TYPE_THERMAL_CHARACTERISTIC: {
          value = Formatter.reformatArea(value) + " kWh/(m²*a)";
          break;
        }

        case FORMAT_TYPE_CURRENCY: {
          value = Formatter.reformatCurrency(value);
          break;
        }
      }

      //only add attributes if value is not empty
      if ((value != null) && (value.trim().length() > 0)) {
        expose.put(attrib, value);
      }
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  private void opt(final MiniExpose expose, final AttributeEnum attrib, final JSONObject json) throws JSONException {
    if (json.has(attrib.getRestapiName())) {
      String value = json.getString(attrib.getRestapiName());
      if (Boolean.class == attrib.getValueType()) {
        expose.put(attrib, Boolean.valueOf(value));
      } else if (ValueEnum.class.isAssignableFrom(attrib.getValueType())) {
        try {
          expose.put(attrib, Enum.valueOf((Class<Enum>) attrib.getValueType(), value));
        } catch (Exception e) {
          Log.e(TAG, "cannot parse enumValue", e);
        }
      } else {
        Log.e(TAG, "unknwon valueType" + attrib.getValueType());
      }
    }
  }

  private void optList(final MiniExpose expose, final AttributeEnum attrib, final String arrayKey,
    final JSONObject json) {
    if (json.has(attrib.getRestapiName())) {
      List<ValueEnum> result = new ArrayList<ValueEnum>();
      JSONArray array = getJSONArrayForObject(json.opt(attrib.getRestapiName()));
      if (array != null) {
        for (int x = 0; x < array.length(); x++) {
          JSONObject arrayEntry = array.optJSONObject(x);
          if (arrayEntry != null) {
            Object value = arrayEntry.opt(arrayKey);
            if (value != null) {
              JSONArray values = getJSONArrayForObject(value);
              for (int i = 0; i < values.length(); i++) {
                try {
                  if (attrib.equals(SiteAttributes.RECOMMENDED_USE_TYPES)) {
                    result.add(SiteRecommendedUseType.valueOf(values.getString(i)));
                  } else if (attrib.equals(LivingAttibutes.FIRING_TYPES)) {
                    result.add(FiringType.valueOf(values.getString(i)));
                  }
                } catch (Exception e) {
                  Log.e(TAG, "cannot parse enumValue", e);
                }
              }
            }
          }
        }
        if (!result.isEmpty()) {
          expose.put(attrib, result);
        }
      } else {
        Log.w(TAG, "JSONArray for key: " + attrib.getRestapiName() + " was not found");
      }
    }
  }
}
