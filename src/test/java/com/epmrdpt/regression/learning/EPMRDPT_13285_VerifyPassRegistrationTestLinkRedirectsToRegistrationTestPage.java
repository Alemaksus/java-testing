package com.epmrdpt.regression.learning;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.ExaminatorScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13285_VerifyPassRegistrationTestLinkRedirectsToRegistrationTestPage",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13285_VerifyPassRegistrationTestLinkRedirectsToRegistrationTestPage {

  private final String TRAINING_NAME = "Autotest with Registration Test";

  @Test(priority = 1)
  public void checkApplicationsPageLoaded() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
            .clickProfileMenu()
            .waitDropDownDisplayed()
            .clickApplicationsButton()
            .isScreenLoaded(),
        "Application page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkRegistrationTestLinkRedirectsToRegistrationTestPage() {
    ExaminatorScreen examinatorScreen = new ExaminatorScreen();
    SoftAssert softAssert = new SoftAssert();
    new ApplicationsScreen()
        .clickPassRegistrationTestByTrainingNameLink(TRAINING_NAME)
        .clickRegistrationTestPopUpOkButton()
        .switchToLastWindow();
    softAssert.assertTrue(examinatorScreen
            .isScreenLoaded(),
        "Examinator page isn't opened!");
    softAssert.assertTrue(examinatorScreen
            .isRegistrationTestPageLoaded(),
        "Registration test link isn't redirected to proper registration test page!");
    softAssert.assertAll();
  }
}
