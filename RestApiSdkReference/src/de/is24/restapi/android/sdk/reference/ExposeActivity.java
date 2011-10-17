package de.is24.restapi.android.sdk.reference;

import java.util.ArrayList;
import java.util.Iterator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.is24.restapi.android.sdk.domain.AttributeEnum;
import de.is24.restapi.android.sdk.domain.Expose;
import de.is24.restapi.android.sdk.domain.ValueEnum;
import de.is24.restapi.android.sdk.http.NoConnectionException;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.service.ExposeService;


public class ExposeActivity extends Activity {
  private static final String TAG = ExposeActivity.class.getSimpleName();
  private ProgressBar progress;
  private LinearLayout exposeLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.expose);
    progress = (ProgressBar) findViewById(R.id.progress);
    exposeLayout = (LinearLayout) findViewById(R.id.exposeLayout);
  }

  @Override
  protected void onResume() {
    super.onResume();
    progress.setVisibility(View.VISIBLE);
    exposeLayout.setVisibility(View.GONE);

    String realEstateId = getIntent().getStringExtra(ExposeActivity.class.getSimpleName());
    new AsyncTask<String, Void, Expose>() {
      @Override
      protected Expose doInBackground(String... realEstateId) {
        Expose expose = null;
        try {
          // Services are singletons and should always be called asynchronously
          expose = ExposeService.getInstance().getExpose(realEstateId[0]);
        } catch (ServiceException e) {
          Log.e(TAG, "Problem while calling the SearchService.", e);
        } catch (NoConnectionException e) {
          Log.e(TAG, "Connection Error.", e);
        }
        return expose;
      }

      @Override
      protected void onPostExecute(Expose result) {
        super.onPostExecute(result);
        progress.setVisibility(View.GONE);
        exposeLayout.setVisibility(View.VISIBLE);
        fillExposeLayout(result);
      }

    }.execute(realEstateId);
  }

  private void fillExposeLayout(Expose expose) {
    LayoutParams textLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    for (AttributeEnum attrib : expose.keySet()) {
      String attribValue = null;
      TextView label = new TextView(this);
      label.setLayoutParams(textLayoutParams);
      label.setText((attrib.getResId() != R.string.no_information) ? getString(attrib.getResId()) : attrib.toString());

      if (String.class == attrib.getValueType()) {
        attribValue = expose.getString(attrib);
      } else if (ValueEnum.class.isAssignableFrom(attrib.getValueType())) {
        ValueEnum valueEnum = expose.opt(attrib, (ValueEnum) null);
        if (valueEnum != null) {
          attribValue = getString(valueEnum.getResId());
        }
      } else if (ArrayList.class == attrib.getValueType()) {
        Iterator<ValueEnum> valuesIterator = expose.optList(attrib).iterator();
        StringBuilder fieldText = new StringBuilder();
        while (valuesIterator.hasNext()) {
          final int resId = valuesIterator.next().getResId();
          if (resId != R.string.no_information) {
            fieldText.append(this.getString(resId));
            if (valuesIterator.hasNext()) {
              fieldText.append(", ");
            }
          }
        }
        if (fieldText.length() > 0) {
          attribValue = fieldText.toString();
        }
      }

      TextView value = new TextView(this);
      value.setLayoutParams(textLayoutParams);
      value.setText(attribValue);

      exposeLayout.addView(label);
      exposeLayout.addView(value);

    }
  }
}
