package de.is24.restapi.android.sdk.util;

import de.is24.restapi.android.sdk.domain.SearchQuery;


public class SearchQueryWrapper {
  public SearchQuery searchQuery;
  private static SearchQueryWrapper INSTANCE;

  private SearchQueryWrapper() {
  }

  public static SearchQueryWrapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new SearchQueryWrapper();
    }
    return INSTANCE;
  }

}
