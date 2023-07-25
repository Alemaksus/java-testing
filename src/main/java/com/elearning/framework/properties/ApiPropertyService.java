package com.epmrdpt.framework.properties;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class ApiPropertyService {

  private static final ResourceBundle resourceBundle = setUpBundle();

  private static ResourceBundle setUpBundle() {
    return ResourceBundle
        .getBundle("api_" + System.getProperty("env"));
  }

  public static String getValueOf(ApiProperty apiProperty) {
    String bundleValue = resourceBundle.getString(apiProperty.getPropertyName());
    return new String(bundleValue.getBytes(StandardCharsets.UTF_8));
  }
}
