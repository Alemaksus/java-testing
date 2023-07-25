package com.epmrdpt.regression.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.screens.ExaminatorScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13140_VerifyPassEnglishTestLinkRedirectsToEnglishTestPage",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13140_VerifyPassEnglishTestLinkRedirectsToEnglishTestPage {

  private final String TRAINING_NAME = "AUTOTEST WITH ENGLISH";

  @Test(priority = 1)
  public void checkApplicationsPageLoaded() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.asLearningStudent())
            .clickProfileMenu()
            .waitDropDownDisplayed()
            .clickApplicationsButton()
            .isScreenLoaded(),
        "Application page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkEnglishTestLinkRedirectsToEnglishTestPage() {
    ExaminatorScreen examinatorScreen = new ExaminatorScreen();
    SoftAssert softAssert = new SoftAssert();
    new ApplicationsScreen()
        .clickPassEnglishTestByTrainingNameLink(TRAINING_NAME)
        .clickEnglishTestPopUpOkButton()
        .switchToLastWindow();
    softAssert.assertTrue(examinatorScreen.isScreenLoaded(),
        "Examinator page isn't opened!");
    softAssert.assertTrue(examinatorScreen.isEnglishTestPageLoaded(),
        "English test link isn't redirected to proper english test page!");
    softAssert.assertAll();
  }
}
