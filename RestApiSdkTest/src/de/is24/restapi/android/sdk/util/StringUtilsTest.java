package de.is24.restapi.android.sdk.util;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;


@SmallTest
public class StringUtilsTest extends AndroidTestCase {
  public void testNoNull() {
    assertEquals("", StringUtils.noNullString(null));
    assertEquals("", StringUtils.noNullString(""));
    assertEquals("value", StringUtils.noNullString("value"));
  }

  public void testAppendNoNull() {
    StringBuilder sb = new StringBuilder();
    StringUtils.appendNoNull(sb, null);
    assertEquals("", sb.toString());
    StringUtils.appendNoNull(sb, "value");
    assertEquals("value", sb.toString());
    StringUtils.appendNoNull(sb, "value");
    assertEquals("value value", sb.toString());
  }

  public void testAppendNoNullWithSuffix() {
    StringBuilder sb = new StringBuilder();
    StringUtils.appendNoNullWithSuffix(sb, null, "suffix");
    assertEquals("", sb.toString());
    StringUtils.appendNoNullWithSuffix(sb, "value", "suffix");
    assertEquals("value suffix", sb.toString());
    StringUtils.appendNoNullWithSuffix(sb, "value", "suffix");
    assertEquals("value suffix value suffix", sb.toString());
  }

  public void testMakeMD5Hex() {
    assertEquals("0800fc577294c34e0b28ad2839435945", StringUtils.makeMD5Hex("hash"));
  }

  public void testMakeSHA256Hex() {
    assertEquals("d04b98f48e8f8bcc15c6ae5ac050801cd6dcfd428fb5f9e65c4e16e7807340fa", StringUtils.makeSHA256Hex("hash"));
  }
}
