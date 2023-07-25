package com.epmrdpt.utils;

import static com.epmrdpt.framework.ui.AbstractScreen.ONE_DECIMAL_PLACE;
import static com.epmrdpt.framework.ui.AbstractScreen.TWO_DECIMAL_PLACE;

import org.apache.commons.math3.util.Precision;

public class DoubleUtils {

  public static Double roundToOneDecimalPlaceDouble(double inputDouble) {
    return Precision.round(inputDouble, ONE_DECIMAL_PLACE);
  }

  public static double parseStringToDouble(String string) {
    return string.equals("-") ? 0.0 : Double.parseDouble(string);
  }

  public static Double roundToTwoDecimalPlaceDouble(double inputDouble) {
    return Precision.round(inputDouble, TWO_DECIMAL_PLACE);
  }
}
