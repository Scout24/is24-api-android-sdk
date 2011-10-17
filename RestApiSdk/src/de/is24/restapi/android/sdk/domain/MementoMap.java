package de.is24.restapi.android.sdk.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Stores all Expose attributes and their values.
 *
 * @author mboehmer
 *
 * @param <ATTRIB> derivative of AttributeEnum.
 */
public class MementoMap<ATTRIB extends AttributeEnum> implements Serializable {
  private static final long serialVersionUID = -3071638526036335399L;
  protected final Map<ATTRIB, Object> attributes = new HashMap<ATTRIB, Object>();
  public final RealEstateType realEstateType;

  public MementoMap(RealEstateType realEstateType) {
    this.realEstateType = realEstateType;
  }

  public String getString(final ATTRIB attribute) {
    return (String) attributes.get(attribute);
  }

  @SuppressWarnings("unchecked")
  public <T> T opt(final ATTRIB attribute, final T defaultValue) {
    if (has(attribute)) {
      return (T) attributes.get(attribute);
    }
    return defaultValue;
  }

  @SuppressWarnings("unchecked")
  public List<ValueEnum> optList(final ATTRIB attribute) {
    if (has(attribute)) {
      return (List<ValueEnum>) attributes.get(attribute);
    }
    return new ArrayList<ValueEnum>();
  }

  public boolean has(final ATTRIB attribute) {
    return attributes.containsKey(attribute);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(final ATTRIB attribute, final Class<T> clazz) {
    if (has(attribute)) {
      return (T) attributes.get(attribute);
    }
    return null;
  }

  public void put(final ATTRIB attribute, final Object value) {
    attributes.put(attribute, value);
  }

  public void remove(final ATTRIB attribute) {
    attributes.remove(attribute);
  }

  public Set<ATTRIB> keySet() {
    return attributes.keySet();
  }

  public boolean hasAttributes() {
    return !attributes.isEmpty();
  }
}
