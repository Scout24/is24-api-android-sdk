package de.is24.restapi.android.sdk.domain;

/**
 * Supertype for all expose attributes.
 *
 * @author mboehmer
 */
public interface AttributeEnum {
  String getRestapiName();

  String name();

  Class<?> getValueType();

  int getResId();

}
