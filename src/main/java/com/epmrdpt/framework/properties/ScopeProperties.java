package com.epmrdpt.framework.properties;

import com.epmrdpt.framework.exceptions.UnsupportedScopeException;

public class ScopeProperties {

  private static String scope;

  static {
    setScope();
  }

  private ScopeProperties() {
  }

  public static void setScope() {
    switch (System.getProperty("scope").toLowerCase()) {
      case "full":
        scope = ScopePropertyConst.FULL;
        break;
      case "general":
        scope = ScopePropertyConst.GENERAL;
        break;
      case "student":
        scope = ScopePropertyConst.STUDENT;
        break;
      case "manager":
        scope = ScopePropertyConst.MANAGER;
        break;
      case "trainer":
        scope = ScopePropertyConst.TRAINER;
        break;
      case "debug":
        scope = ScopePropertyConst.DEBUG;
        break;
      case "regression":
        scope = ScopePropertyConst.REGRESSION;
        break;
      case "smoke":
        scope = ScopePropertyConst.SMOKE;
        break;
      case "news_management":
        scope = ScopePropertyConst.NEWS_MANAGEMENT;
        break;
      case "admin":
        scope = ScopePropertyConst.ADMIN;
        break;
      case "critical_path":
        scope = ScopePropertyConst.CRITICAL_PATH;
        break;
      case "react":
        scope = ScopePropertyConst.REACT;
        break;
      case "api":
        scope = ScopePropertyConst.API;
        break;
      default:
        throw new UnsupportedScopeException("%s scope is not supported",
            System.getProperty("scope"));
    }
  }

  public static String getScope() {
    return scope;
  }
}
