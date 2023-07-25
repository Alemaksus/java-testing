package com.epmrdpt.framework.properties;

import com.epmrdpt.bo.LanguageEnum;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import org.apache.commons.lang3.LocaleUtils;

public class LocaleProperties {

  private static final ResourceBundle RESOURCE_BUNDLE = setUpBundle();

  private LocaleProperties() {
  }

  private static ResourceBundle setUpBundle() {
    return ResourceBundle
        .getBundle("localization", LocaleUtils.toLocale(System.getProperty("locale")));
  }

  public static String getValueOf(String key) {
    String bundleValue = RESOURCE_BUNDLE.getString(key);
    return new String(bundleValue.getBytes(StandardCharsets.UTF_8));
  }

  public static String getValueOf(String key, LanguageEnum languageEnum) {
    String bundleValue = ResourceBundle.getBundle("localization",
        LocaleUtils.toLocale(languageEnum.getName())).getString(key);
    return new String(bundleValue.getBytes(StandardCharsets.UTF_8));
  }
}
