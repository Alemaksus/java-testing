package com.epmrdpt.framework.properties;

import com.epmrdpt.framework.exceptions.UnsupportedEnvironmentException;

public class EnvironmentProperty {

  private static String env;

  static {
    setEnv();
  }

  private EnvironmentProperty() {
  }

  public static void setEnv() {
    switch (System.getProperty("env")) {
      case "QA_168":
        env = EnvironmentPropertyConst.QA_168;
        break;
      case "QA_174":
        env = EnvironmentPropertyConst.QA_174;
        break;
      case "QA_180":
        env = EnvironmentPropertyConst.QA_180;
        break;
      case "STAGE":
        env = EnvironmentPropertyConst.STAGE;
        break;
      case "QA_TRAINING":
        env = EnvironmentPropertyConst.QA_TRAINING;
        break;
      default:
        throw new UnsupportedEnvironmentException("%s environment is not supported",
            System.getProperty("env"));
    }
  }

  public static String getEnv() {
    return env;
  }
}
