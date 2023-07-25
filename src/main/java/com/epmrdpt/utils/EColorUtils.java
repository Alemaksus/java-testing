package com.epmrdpt.utils;

public enum EColorUtils {
  WITHOUT_COLOR("0, 0, 0", 0),
  WHITE_COLOR("255, 255, 255", 1),
  GREEN_ACTIVE("103, 163, 0", 1),
  GRAY_LIGHT("217, 219, 227", 1),
  BLUE_COLOUR("0, 138, 189", 1),
  BLUE_ACTIVE_COLOUR("0, 158, 204", 1);

  private final String rgbValue;
  private final int alphaParameter;

  EColorUtils(String rgbValue, int alphaParameter) {
    this.rgbValue = rgbValue;
    this.alphaParameter = alphaParameter;
  }

  public String getColorRgbFormat() {
    return "rgb(" + rgbValue + ")";
  }

  public String getColorRgbaFormat() {
    return "rgba(" + rgbValue + ", " + alphaParameter + ")";
  }
}
