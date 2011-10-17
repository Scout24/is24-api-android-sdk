package de.is24.restapi.android.sdk.reference;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.is24.restapi.android.sdk.domain.MiniExpose;
import de.is24.restapi.android.sdk.domain.MiniExposeAttributes;
import de.is24.restapi.android.sdk.domain.Page;
import de.is24.restapi.android.sdk.domain.SearchQuery;
import de.is24.restapi.android.sdk.http.NoConnectionException;
import de.is24.restapi.android.sdk.http.ServiceException;
import de.is24.restapi.android.sdk.service.SearchService;
import de.is24.restapi.android.sdk.util.SearchQueryWrapper;


public class SearchResultListActivity extends Activity {
  private static final String TAG = SearchResultListActivity.class.getSimpleName();
  private ListView resultList;
  private ProgressBar progress;
  private LayoutInflater inflater;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.result_list);
    resultList = (ListView) findViewById(R.id.resultList);
    resultList.setOnItemClickListener(onItemCLickListener);
    progress = (ProgressBar) findViewById(R.id.progress);
    inflater = getLayoutInflater();
  }

  @Override
  protected void onResume() {
    super.onResume();
    progress.setVisibility(View.VISIBLE);
    resultList.setVisibility(View.GONE);
    new AsyncTask<SearchQuery, Void, Page>() {
      @Override
      protected Page doInBackground(SearchQuery... params) {
        Page resultPage = null;
        try {
          // Services are singletons and should always be called asynchronously
          resultPage = SearchService.getInstance().execute(
            params[0], 1, false);
        } catch (ServiceException e) {
          Log.e(TAG, "Problem while calling the SearchService.", e);
        } catch (NoConnectionException e) {
          Log.e(TAG, "Connection Error.", e);
        }
        return resultPage;
      }

      @Override
      protected void onPostExecute(Page result) {
        super.onPostExecute(result);
        progress.setVisibility(View.GONE);
        resultList.setVisibility(View.VISIBLE);
        resultList.setAdapter(new ResultListAdapter(result.results));

      }
    }.execute(SearchQueryWrapper.getInstance().searchQuery);
  }

  private class ResultListAdapter extends BaseAdapter {
    private ArrayList<MiniExpose> results = null;

    public ResultListAdapter(ArrayList<MiniExpose> results) {
      this.results = results;
    }

    @Override
    public int getCount() {
      return results.size();
    }

    @Override
    public Object getItem(int position) {
      return results.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      TextView text = (TextView) convertView;

      if (text == null) {
        text = (TextView) inflater.inflate(R.layout.row_layout, parent, false);
      }
      text.setTypeface(Typeface.DEFAULT);

      MiniExpose miniExpose = results.get(position);
      text.setText(miniExpose.getString(MiniExposeAttributes.TITLE));
      text.setTag(miniExpose.id);
      return text;
    }
  }

  private OnItemClickListener onItemCLickListener = new OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
      Intent exposeIntent = new Intent(getApplicationContext(), ExposeActivity.class);
      exposeIntent.putExtra(ExposeActivity.class.getSimpleName(), (String) view.getTag());
      startActivity(exposeIntent);
    }
  };
}
