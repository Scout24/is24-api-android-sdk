package de.is24.restapi.android.sdk.domain;

import android.location.Location;


/**
 * The domain object representing a search query, which can be stored or executed
 * @author Hasan Hosgel
 *
 */
public class SearchQuery extends MementoMap<CriteriaEnum> {
  private static final long serialVersionUID = -1882625980688952364L;
  public static final int SEARCH_TYPE_ADDRESS = 1;
  public static final int SEARCH_TYPE_GPS = 3;
  public static final int SEARCH_TYPE_INTERVAL = 5;

  private int searchType;
  public Long timeStamp;

  //not persisting
  public Sorting sorting;

  public SearchQuery(final RealEstateType searchType) {
    super(searchType);
  }

  /**
   * generates a new {@link SearchQuery}, where all {@link RealEstateType} unspecific attribute are copied
   * @param realEstateType
   * @param query
   */
  public SearchQuery(final RealEstateType realEstateType, final SearchQuery query) {
    this(realEstateType);
    searchType = query.searchType;
    if (realEstateType == query.realEstateType) {
      attributes.putAll(query.attributes);
    } else {
      copyAttribute(CommonCriteria.GEOCODE_ID, query);
      copyAttribute(CommonCriteria.LOCATION, query);
      copyAttribute(CommonCriteria.LOCATION_LABEL, query);
      copyAttribute(CommonCriteria.RADIUS, query);
    }
  }

  private void copyAttribute(CriteriaEnum criteria, SearchQuery query) {
    if (query.has(criteria)) {
      put(criteria, query.attributes.get(criteria));
    }
  }

  public void setSearchType(int searchType) {
    if (searchType != this.searchType) {
      attributes.remove(CommonCriteria.GEOCODE_ID);
      attributes.remove(CommonCriteria.LOCATION);
      if ((searchType == SEARCH_TYPE_INTERVAL) || (this.searchType == SEARCH_TYPE_INTERVAL)) {
        attributes.remove(CommonCriteria.RADIUS);
      }
      this.searchType = searchType;
    }
  }

  public int getSearchType() {
    return searchType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }

    SearchQuery query = (SearchQuery) obj;
    if ((query.realEstateType != realEstateType) || (query.getSearchType() != getSearchType()) ||
        (query.attributes.size() != attributes.size())) {
      return false;
    }
    for (CriteriaEnum criteria : query.attributes.keySet()) {
      if (!has(criteria)) {
        return false;
      }
      if (criteria.getValueType() == Location.class) {
        final Location loc1 = get(criteria, Location.class);
        final Location loc2 = query.get(criteria, Location.class);
        if ((loc1.getLatitude() != loc2.getLatitude()) || (loc1.getLongitude() != loc2.getLongitude())) {
          return false;
        }
      } else if (!attributes.get(criteria).equals(query.attributes.get(criteria))) {
        return false;
      }
    }

    return true;
  }
}
