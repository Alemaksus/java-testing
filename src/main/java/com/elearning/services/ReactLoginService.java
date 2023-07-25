package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.framework.exceptions.UnsupportedEnvironmentException;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.screens.ReactTrainingScreen;

public class ReactLoginService {

  private static final String REACT_PAGE = "/Platform";
  private static final String PATH_TO_REACT = getEnv() + REACT_PAGE;
  private static final int STATUS_CODE_OK = 200;

  private ReactLoginService loginAndGoToReact(User user) {
    if (isReactVersionEnabled()) {
      new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
          .clickReactTrainingScreenLink();
    } else {
      throw new UnsupportedEnvironmentException(
          format("React version isn't available on %s env!", getEnv()));
    }
    return this;
  }

  public ReactTrainingScreen loginAndGoToReactTrainingScreen(User user) {
    loginAndGoToReact(user);
    return new ReactTrainingScreen();
  }

  public ReactTrainingScreen loginAndGoToReactTrainingScreenGroupsTab(User user) {
    loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    return new ReactTrainingScreen();
  }

  public static boolean isReactVersionEnabled() {
    return getResponseStatus() == STATUS_CODE_OK;
  }

  public static int getResponseStatus() {
    return given().relaxedHTTPSValidation().when().get(PATH_TO_REACT).getStatusCode();
  }

  public HeaderScreen signOut() {
    return new ReactHeaderScreen().clickUserInfoToggle().signOut();
  }

  public ReactTrainingManagementScreen loginAndGoToReactTrainingManagement(User user) {
    return new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
  }

  public ReactEventsManagementScreen loginAndGoToReactEventsManagementScreen(User user) {
    return new LoginService()
        .loginAndSetLanguage(user)
        .clickEventsManagementLink();
  }
}
