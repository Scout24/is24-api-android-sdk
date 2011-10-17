package de.is24.restapi.android.sdk.http.response.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.Page;
import de.is24.restapi.android.sdk.http.HttpHeader;
import de.is24.restapi.android.sdk.http.response.Response;
import de.is24.restapi.android.sdk.util.Formatter;


/**
 * Parses the JSON response for a search request against the REST-API.
 *
 * @author mboehmer
 */
public class SearchResponseHandler extends JsonResponseHandler<Page> {
  private static final String TAG = "SearchResponseHandler";
  private static final String JSON_KEY_ROOT_ELEMENT = "resultlist.resultlist";
  private static final String JSON_KEY_ROOT_ELEMENT_STRICT = "common.strictList";
  private static final String JSON_KEY_PAGING = "paging";
  private static final String JSON_KEY_PAGE_SIZE = "pageSize";
  private static final String JSON_KEY_NUMBER_OF_HITS = "numberOfHits";
  private static final String JSON_KEY_NUMBER_OF_PAGES = "numberOfPages";
  private static final String JSON_KEY_PAGE_NUMBER = "pageNumber";
  private static final String JSON_KEY_RESULTLIST_ENTRIES_META_DATA = "resultlistEntries";
  private static final String JSON_KEY_RESULTLIST_ENTRIES_ARRAY = "resultlistEntry";
  private static final String JSON_KEY_RESULTLIST_ENTRY_META_DATA = "resultlist.realEstate";

  private boolean strictMode;

  @Override
  protected String getTag() {
    return TAG;
  }

  public boolean isStrictMode() {
    return strictMode;
  }

  public void setStrictMode(boolean strictMode) {
    this.strictMode = strictMode;
  }

  @Override
  public HttpHeader getAcceptHeader() {
    if (strictMode) {
      return HttpHeader.ACCEPT_JSON_STRICT;
    }
    return super.getAcceptHeader();
  }

  /**
   * Method which parses the JSON response returned by a search request against
   * the REST-API.
   *
   * @param response
   *          The HTTP response of the REST-API search request.
   * @param result
   *          The result object which will be filled with the response data.
   * @throws IOException
   *           Can be thrown while parsing the JSON response.
   * @return The filled response object.
   */
  public Response<Page> handleResponse(InputStream response, int responseCode, Response<Page> result)
    throws IOException {
    result.statusCode = responseCode;
    if (result.statusCode == HttpStatus.SC_OK) {
      try {
        JSONObject jsonResponse = parseJson(response);

        Object object = null;
        if (strictMode) {
          object = jsonResponse.opt(JSON_KEY_ROOT_ELEMENT_STRICT);
        } else {
          object = jsonResponse.opt(JSON_KEY_ROOT_ELEMENT);
        }

        Page page = new Page();
        page.results = new ArrayList<MiniExpose>();

        if (object instanceof JSONObject) {
          JSONObject rootObject = ((JSONObject) object);

          // paging attributes
          JSONObject pagingObject = rootObject.optJSONObject(JSON_KEY_PAGING);
          if (pagingObject != null) {
            page.pageNumber = pagingObject.optInt(JSON_KEY_PAGE_NUMBER);
            page.pageSize = pagingObject.optInt(JSON_KEY_PAGE_SIZE);
            page.pageCount = pagingObject.optInt(JSON_KEY_NUMBER_OF_PAGES);
            page.totalMatchCount = pagingObject.optInt(JSON_KEY_NUMBER_OF_HITS);
          }

          if (!strictMode) {
            // resultlistEntries meta data
            JSONArray resultlistEntriesMetaData = getJSONArrayForObject(rootObject.opt(
              JSON_KEY_RESULTLIST_ENTRIES_META_DATA));
            JSONObject resultlistEntries = null;
            if (resultlistEntriesMetaData != null) {
              resultlistEntries = resultlistEntriesMetaData.optJSONObject(0);
            }

            // array of resultEntries
            JSONArray resultlistEntryArray = null;
            if (resultlistEntries != null) {
              resultlistEntryArray = getJSONArrayForObject(resultlistEntries.opt(JSON_KEY_RESULTLIST_ENTRIES_ARRAY));

            }

            // iterate through single resultEntries
            if (resultlistEntryArray != null) {
              MiniExpose miniExpose;
              for (int y = 0; y < resultlistEntryArray.length(); y++) {
                JSONObject resultlistEntry = resultlistEntryArray.optJSONObject(y);
                if (null != resultlistEntry) {
                  JSONObject realestate = resultlistEntry.optJSONObject(
                    SearchResponseHandler.JSON_KEY_RESULTLIST_ENTRY_META_DATA);

                  miniExpose = MiniExposeHandlerHelper.parseMiniExpose(realestate);
                  if (null != miniExpose) {
                    if (resultlistEntry.has(MiniExposeAttributes.DISTANCE.getRestapiName())) {
                      miniExpose.put(MiniExposeAttributes.DISTANCE,
                        Formatter.reformatDistance(
                          resultlistEntry.getString(MiniExposeAttributes.DISTANCE.getRestapiName())));
                    }

                    page.results.add(miniExpose);
                  }
                }
              }
            }
          }
        } else {
          Log.w(TAG, "unknown ObjectClass:" + ((object == null) ? object : object.getClass()));
          return result;
        }
        result.result = page;
        result.success = true;

      } catch (IllegalStateException e) {
        Log.e(TAG, "illegalState", e);
      } catch (IOException e) {
        Log.e(TAG, "io problem", e);
      } catch (JSONException e) {
        Log.e(TAG, "json problem", e);
      }
    } else {
      Log.w(
        TAG, new StringBuilder().append("Response is faulty. StatusCode: ").append(result.statusCode).toString());
    }
    return result;
  }
}
