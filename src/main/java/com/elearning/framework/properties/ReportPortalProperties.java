package com.epmrdpt.framework.properties;

import com.epam.ta.reportportal.ws.model.launch.Mode;
import com.epmrdpt.framework.exceptions.UnsupportedLaunchNameException;
import com.epmrdpt.framework.exceptions.UnsupportedModeException;

public class ReportPortalProperties {

  private static Mode mode;
  private static String launchName;

  static {
    setMode();
    setLaunchName();
  }

  private ReportPortalProperties() {
  }

  public static void setMode() {
    switch (System.getProperty("mode").toLowerCase()) {
      case "launch":
        mode = Mode.DEFAULT;
        break;
      case "debug":
        mode = Mode.DEBUG;
        break;
      default:
        throw new UnsupportedModeException("%s mode is not supported",
            System.getProperty("mode"));
    }
  }

  public static void setLaunchName() {
    if (System.getProperty("launchName").contains(" ")) {
      throw new UnsupportedLaunchNameException(
          "'%s' launch name is not supported, cause name contains the spaces",
          System.getProperty("launchName"));
    }
    launchName = System.getProperty("launchName");
  }

  public static Mode getMode() {
    return mode;
  }

  public static String getLaunchName() {
    return launchName;
  }
}
