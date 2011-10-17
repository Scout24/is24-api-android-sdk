package de.is24.restapi.android.sdk.reference;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import de.is24.restapi.android.sdk.LibraryContext;
import de.is24.restapi.android.sdk.domain.CommonCriteria;
import de.is24.restapi.android.sdk.domain.LivingExtendedCriteria;
import de.is24.restapi.android.sdk.domain.LivingSiteCriteria;
import de.is24.restapi.android.sdk.domain.Range;
import de.is24.restapi.android.sdk.domain.RealEstateType;
import de.is24.restapi.android.sdk.domain.SearchQuery;
import de.is24.restapi.android.sdk.http.HttpRequestExecuter;
import de.is24.restapi.android.sdk.util.SearchQueryWrapper;


public class SearchFormActivity extends Activity {
  private SearchQuery searchQuery;
  private EditText coordinatesLatEditText;
  private EditText coordinatesLongEditText;
  private TextView radiusTextView;
  private SeekBar radiusSeekBar;
  private RadioGroup realEstateTypeRadioGroup;
  private RadioGroup realEstatePurchaseTypeRadioGroup;
  private EditText priceMinEditText;
  private EditText priceMaxEditText;
  private EditText roomsMinEditText;
  private EditText roomsMaxEditText;
  private EditText areaMinEditText;
  private EditText areaMaxEditText;
  private Button searchButton;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.search_form);

    //-----------------------------------------------
    // Those two lines are the only thing you'll need to write to initialize the REST API SDK Library.
    // The Library needs the application context for internal resource lookups like texts or urls.
    // The HttpRequestExecuter is responsible for signing each request against the REST API.
    // You'll need to provide your consumerKey and your consumerSecret which you received with your API Developer registration.
    LibraryContext.getInstance().initApplicationContext(
      getApplicationContext());
    HttpRequestExecuter.getInstance().init2LeggedOauth(
      "consumer-key", "consumer-secret", false);
    //-----------------------------------------------

    searchButton = (Button) findViewById(R.id.search);
    searchButton.setOnClickListener(searchOnClickListener);

    coordinatesLatEditText = (EditText) findViewById(R.id.coordinatesLat);

    coordinatesLongEditText = (EditText) findViewById(R.id.coordinatesLong);

    radiusTextView = (TextView) findViewById(R.id.radiusSeekBarTextView);

    radiusSeekBar = (SeekBar) findViewById(R.id.radiusSeekBar);
    radiusSeekBar.setOnSeekBarChangeListener(radiusChangedListener);

    realEstateTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupRealEstateType);
    realEstateTypeRadioGroup.setOnCheckedChangeListener(checkedChangeListener);

    realEstatePurchaseTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupPurchaseType);
    realEstatePurchaseTypeRadioGroup.setOnCheckedChangeListener(checkedChangeListener);

    priceMinEditText = (EditText) findViewById(R.id.priceMin);

    priceMaxEditText = (EditText) findViewById(R.id.priceMax);

    roomsMinEditText = (EditText) findViewById(R.id.roomsMin);

    roomsMaxEditText = (EditText) findViewById(R.id.roomsMax);

    areaMinEditText = (EditText) findViewById(R.id.areaMin);

    areaMaxEditText = (EditText) findViewById(R.id.areaMax);

  }

  @Override
  protected void onResume() {
    super.onResume();
    if (searchQuery == null) {
      searchQuery = new SearchQuery(RealEstateType.ApartmentRent);
      searchQuery.setSearchType(SearchQuery.SEARCH_TYPE_GPS);
    }
    updateSearchQuery();
  }
  ;

  public void updateRealEstateType(RadioGroup realEstateType,
    RadioGroup realEstatePurchaseType) {
    switch (realEstateTypeRadioGroup.getCheckedRadioButtonId()) {
      case R.id.radioFlat: {
        if (realEstatePurchaseTypeRadioGroup.getCheckedRadioButtonId() == R.id.radioRent) {
          searchQuery = new SearchQuery(RealEstateType.ApartmentRent,
            searchQuery);
        } else {
          searchQuery = new SearchQuery(RealEstateType.ApartmentBuy,
            searchQuery);
        }
        break;
      }

      case R.id.radioHouse: {
        if (realEstatePurchaseTypeRadioGroup.getCheckedRadioButtonId() == R.id.radioRent) {
          searchQuery = new SearchQuery(RealEstateType.HouseRent,
            searchQuery);
        } else {
          searchQuery = new SearchQuery(RealEstateType.HouseBuy,
            searchQuery);
        }
        break;
      }

      case R.id.radioSite: {
        if (realEstatePurchaseTypeRadioGroup.getCheckedRadioButtonId() == R.id.radioRent) {
          searchQuery = new SearchQuery(RealEstateType.LivingRentSite,
            searchQuery);
        } else {
          searchQuery = new SearchQuery(RealEstateType.LivingBuySite,
            searchQuery);
        }
        break;
      }

      default: {
        searchQuery = new SearchQuery(RealEstateType.ApartmentRent,
          searchQuery);
        break;
      }
    }
  }

  OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
      updateSearchQuery();
    }
  };

  OnSeekBarChangeListener radiusChangedListener = new OnSeekBarChangeListener() {
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
      // not implemented
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
      // not implemented

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
      boolean fromUser) {
      radiusTextView.setText("Radius: " + (progress + 1) + "Km");

    }
  };

  OnClickListener searchOnClickListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      updateSearchQuery();
      SearchQueryWrapper.getInstance().searchQuery = searchQuery;

      Intent resultListIntent = new Intent(getApplicationContext(), SearchResultListActivity.class);
      startActivity(resultListIntent);
    }
  };

  private void updateSearchQuery() {
    //update coordinates
    String latitude = coordinatesLatEditText.getText().toString();
    Location location = new Location("Location by coordinates");
    if (!TextUtils.isEmpty(latitude)) {
      location.setLatitude(Double.parseDouble(latitude.replace(" ", "").replace(",", ".")));
    }

    String longitude = coordinatesLongEditText.getText().toString();
    if (!TextUtils.isEmpty(longitude)) {
      location.setLongitude(Double.parseDouble(longitude.replace(" ", "").replace(",", ".")));
    }
    searchQuery.put(CommonCriteria.LOCATION, location);

    //update radius
    String radius = String.valueOf(radiusSeekBar.getProgress() + 1);
    searchQuery.put(CommonCriteria.RADIUS, radius);

    //update realEstateType
    updateRealEstateType(realEstateTypeRadioGroup, realEstatePurchaseTypeRadioGroup);

    //update priceRange
    String priceMin = priceMinEditText.getText().toString();
    String priceMax = priceMaxEditText.getText().toString();
    Range priceRange = new Range(priceMin, priceMax);
    searchQuery.put(CommonCriteria.PRICE_RANGE, priceRange);

    //update roomsRange
    String roomsMin = roomsMinEditText.getText().toString();
    String roomsMax = roomsMaxEditText.getText().toString();
    Range roomsRange = new Range(roomsMin, roomsMax);
    searchQuery.put(LivingExtendedCriteria.ROOMS_RANGE, roomsRange);

    //update areaRange
    String areaMin = areaMinEditText.getText().toString();
    String areaMax = areaMaxEditText.getText().toString();
    Range areaRange = new Range(areaMin, areaMax);
    if (searchQuery.realEstateType.equalsOne(RealEstateType.LivingBuySite, RealEstateType.LivingRentSite)) {
      searchQuery.put(LivingSiteCriteria.PLOT_RANGE, areaRange);
    } else {
      searchQuery.put(LivingExtendedCriteria.LIVING_SPACE_RANGE, areaRange);
    }
  }
}
