package de.is24.restapi.android.sdk.domain;

import org.json.JSONException;
import org.json.JSONObject;


public class PdfAttachment extends Attachment {
  public PdfAttachment() {
    super(PDF_TYPE);
  }

  public PdfAttachment(final JSONObject json) {
    super(json, PDF_TYPE);
  }

  @Override
  public void additions(JSONObject json) throws JSONException {
    // nothing in the moment
  }

}
