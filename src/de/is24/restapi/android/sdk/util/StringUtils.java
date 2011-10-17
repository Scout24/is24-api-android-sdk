package de.is24.restapi.android.sdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.text.TextUtils;
import android.util.Log;


/**
 * Helper for {@link String} operations
 * @author Hasan Hosgel
 *
 */
public class StringUtils {
  public static final String EMPTY_STRING = "";
  public static final String COMMA = ",";
  public static final String SPACE = " ";
  public static final String NEW_LINE = "\n";
  public static final String COLON = ":";
  public static final String ZERO = "0";
  public static final String SLASH = "/";
  public static final String DOT = ".";
  public static final String EQUAL = "=";
  public static final String BR = "<br/>";
  public static final char C_COMMA = ',';
  public static final char C_EQUAL = '=';
  public static final char C_SPACE = ' ';
  public static final char C_NEW_LINE = '\n';
  public static final char C_COLON = ':';
  public static final char C_ZERO = '0';
  public static final char C_SEMICOLON = ';';
  public static final char C_SLASH = '/';
  public static final char C_DOT = '.';
  public static final char C_AMP = '&';
  public static final char C_MINUS = '-';

  private static final char[] DIGITS_LOWER = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
  };
  public static final String REGEX_IS_EMAIL =
    "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
  private static final String TAG = "StringUtils";
  private static MessageDigest shaDigest;
  private static MessageDigest md5Digest;

  //  /**
  //   * returns the corresponding {@link String} from the {@link Resources} by the given id
  //   * @param resId
  //   * @param args
  //   * @return
  //   */
  //  public static String getString(int resId, Object... args) {
  //    for (int i = args.length - 1; i >= 0; i--) {
  //      if (args[i] == null) {
  //        args[i] = EMPTY_STRING;
  //      }
  //    }
  //    return BlueprintApplication.getApp().getString(resId, args);
  //  }

  /**
   * This method returns an empty {@link String} if the value is empty or the given value.
   * @param value of type {@link String}
   * @return
   */
  public static String noNullString(String value) {
    if (TextUtils.isEmpty(value)) {
      return EMPTY_STRING;
    } else {
      return value;
    }
  }

  /**
   * this method appends the value, if it's not empty. If the length of source is not 0 it appends at first
   * a space and than the value.
   * @param source
   * @param value
   */
  public static void appendNoNull(final StringBuilder source, final String value) {
    if (!TextUtils.isEmpty(value)) {
      if (TextUtils.isEmpty(source)) {
        source.append(value);
      } else {
        source.append(SPACE).append(value);
      }
    }
  }

  /**
   * this method appends the value, if it's not empty. If the length of source is not 0 it appends at first
   * a space and than the value. After all it appends the suffix with a space.
   * @param source
   * @param value
   * @param suffix
   */
  public static void appendNoNullWithSuffix(final StringBuilder source, final String value, final String suffix) {
    if (!TextUtils.isEmpty(value)) {
      if (TextUtils.isEmpty(source)) {
        source.append(value);
      } else {
        source.append(SPACE).append(value);
      }
      source.append(SPACE).append(suffix);
    }
  }

  /**
   * Returns a valid SHA256Hex hash String from the parameter
   * @param parameter
   * @return
   */
  public static String makeSHA256Hex(String parameter) {
    try {
      if (null == shaDigest) {
        shaDigest = MessageDigest.getInstance("SHA-256");
      }

      byte[] digest = shaDigest.digest(parameter.getBytes());

      return new String(encodeHex(digest, DIGITS_LOWER));
    } catch (NoSuchAlgorithmException e) {
      Log.e(TAG, "cannot create SHA256 Hex.", e);
    }
    return null;
  }

  /**
   * Returns a valid MD5Hex hash String from the parameter
   * @param parameter
   * @return
   */
  public static String makeMD5Hex(String parameter) {
    try {
      if (null == md5Digest) {
        md5Digest = MessageDigest.getInstance("MD5");
      }

      byte[] digest = md5Digest.digest(parameter.getBytes());

      return new String(encodeHex(digest, DIGITS_LOWER));
    } catch (NoSuchAlgorithmException e) {
      Log.e(TAG, "cannot create md5 Hex.", e);
    }
    return null;
  }

  private static char[] encodeHex(byte[] data, char[] toDigits) {
    int l = data.length;
    char[] out = new char[l << 1];

    // two characters form the hex value.
    for (int i = 0, j = 0; i < l; i++) {
      out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
      out[j++] = toDigits[0x0F & data[i]];
    }
    return out;
  }
}
