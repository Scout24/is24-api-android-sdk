package de.is24.restapi.android.sdk;

import android.content.Context;


public class LibraryContext {
  private static LibraryContext INSTANCE;
  private Context context;

  private LibraryContext() {
  }

  public static LibraryContext getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new LibraryContext();
    }
    return INSTANCE;
  }

  public void initApplicationContext(Context context) {
    this.context = context;
  }

  public Context getApplicationContext() {
    return context;
  }

}
