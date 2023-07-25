package com.epmrdpt.utils;

import java.time.LocalDate;

public class IntegerUtils {

  public static int getCurrentDayOfMonth() {
    return LocalDate.now().getDayOfMonth();
  }

  public static int parseStringToInt(String string) {
    return string.equals("-") ? 0 : Integer.parseInt(string);
  }
}
