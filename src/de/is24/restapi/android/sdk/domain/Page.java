package de.is24.restapi.android.sdk.domain;

import java.util.ArrayList;


/**
 * this domain object represents the resultpage of a search query
 * @author Hasan Hosgel
 *
 */
public class Page {
  public int pageSize = 0;
  public int totalMatchCount = 0;
  public int pageCount = 0;
  public int pageNumber = 0;

  public ArrayList<MiniExpose> results;

}
