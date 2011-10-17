package de.is24.restapi.android.sdk.domain;

import de.is24.restapi.android.sdk.R;


/**
 * {@link ValueEnum} for {@link CommercializationType}.
 *
 * @author mboehmer
 */
public enum CommercializationType implements ValueEnum {
  BUY(R.string.commercialization_type_buy, "BUY"), RENT(R.string.commercialization_type_rent, "RENT"),
  COMPULSORY_AUCTION(R.string.commercialization_type_compulsory_auction, "COMPULSORY_AUCTION"),
  LEASE(R.string.commercialization_type_lease, "LEASE"),
  LEASEHOLD(R.string.commercialization_type_leasehold, "LEASEHOLD");

  public final int i18nResource;
  public final String restapiName;

  private CommercializationType(final int i18nResource,
    final String restapiName) {
    this.i18nResource = i18nResource;
    this.restapiName = restapiName;
  }

  public int getResId() {
    return i18nResource;
  }

  public String getRestapiName() {
    return restapiName;
  }
}
