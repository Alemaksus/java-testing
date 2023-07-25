package com.epmrdpt.framework.listeners;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static com.epmrdpt.tokens_storage.TokensStorage.setAdminCredentials;
import static com.epmrdpt.tokens_storage.TokensStorage.setTokens;
import static com.epmrdpt.tokens_storage.TokensStorage.setUpAdminRestAssuredCredentials;
import static com.epmrdpt.framework.listeners.OpenCloseBrowserEachClassListener.openHomePage;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import com.epmrdpt.services.LoginService;
import io.restassured.http.Header;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.IClassListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;

public class RetrieveAuthorizationTokenListener implements ISuiteListener, IClassListener {

  private static final String ACCESS_TOKEN = "AccessToken";
  private static final String CSRF_TOKEN = "CSRFToken";
  private static final String BEARER = "Bearer ";
  private static String adminLoginToken;
  private static String adminAccessToken;
  private boolean isRequestSpecificationSet = false;
  private static boolean isAdminRequestSpecificationSet = false;

  /**
   * 1.Retrieve SuperAdmin and LearningStudent tokens from UI
   * 2.Save them in TokensStorage
   */
  @Override
  public void onStart(ISuite suite) {
    openHomePage();
    new LoginService().loginWithoutWaiting(asSuperAdmin());
    adminLoginToken = retrieveUserTokenFromLocalStorage(CSRF_TOKEN);
    adminAccessToken = retrieveUserTokenFromLocalStorage(ACCESS_TOKEN);
    setAdminCredentials(adminLoginToken, adminAccessToken);
    new LoginService().logout().loginWithoutWaiting(asLearningStudent());
    String studentLoginToken = retrieveUserTokenFromLocalStorage(CSRF_TOKEN);
    String studentAccessToken = retrieveUserTokenFromLocalStorage(ACCESS_TOKEN);
    setTokens(adminLoginToken, adminAccessToken, studentLoginToken, studentAccessToken);
  }

  /**
   * Set request specification with SuperAdmin credentials before the first API test
   */
  @Override
  public void onBeforeClass(ITestClass testClass) {
    if (!isRequestSpecificationSet) {
      requestSpecification = setAdminRequestSpecification();
      isRequestSpecificationSet = true;
    }
  }

  /**
   * Set up SuperAdmin credentials to the request specification before each API test
   */
  @Override
  public void onAfterClass(ITestClass testClass) {
    setUpAdminRestAssuredCredentials();
    ((RequestSpecificationImpl) requestSpecification).removeCookies();
  }

  @Override
  public void onFinish(ISuite suite) {
    Log.logInfoMessage("Close browser");
    WebDriverSingleton.getInstance().closeDriver();
  }

  private static String retrieveUserTokenFromLocalStorage(String key) {
    return (String) ((JavascriptExecutor) WebDriverSingleton
        .getInstance()
        .getDriver())
        .executeScript(String.format("return window.localStorage.getItem('%s');", key));
  }

  private static RequestSpecification setAdminRequestSpecification() {
    return given()
        .relaxedHTTPSValidation()
        .and()
        .header(new Header("x-csrf-token", adminLoginToken))
        .header(new Header("Authorization", BEARER + adminAccessToken))
        .baseUri(getEnv());
  }

  /**
   * 1.Retrieve SuperAdmin tokens from UI
   * 2.Save them in TokensStorage
   * 3.Set request specification with SuperAdmin credentials in the first UI test with API call
   */
  public static synchronized void setUpRestAssuredForUI() {
    if (!isAdminRequestSpecificationSet) {
      isAdminRequestSpecificationSet = true;
      openHomePage();
      new LoginService().login(asSuperAdmin());
      adminLoginToken = retrieveUserTokenFromLocalStorage(CSRF_TOKEN);
      adminAccessToken = retrieveUserTokenFromLocalStorage(ACCESS_TOKEN);
      setAdminCredentials(adminLoginToken, adminAccessToken);
      requestSpecification = setAdminRequestSpecification();
      Log.logInfoMessage("Close browser for API");
      WebDriverSingleton.getInstance().closeDriver();
    }
  }
}
