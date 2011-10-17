package de.is24.restapi.android.sdk.domain;


public interface RestApiAvailable {
  /**
   * returns the resource id for the string representation
   */
  int getResId();

  /**
   * Returns the REST-API request param name of the enum object.
   */
  String getRestApiSearchRequestName();
}
