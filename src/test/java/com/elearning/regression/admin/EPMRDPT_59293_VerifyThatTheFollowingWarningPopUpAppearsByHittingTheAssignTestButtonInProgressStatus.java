package com.epmrdpt.regression.admin;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.bo.user.UserFactory.asRecruiter;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ProfileService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59293_VerifyThatTheFollowingWarningPopUpAppearsByHittingTheAssignTestButtonInProgressStatus",
    groups = {"full", "general", "regression", "admin"})
public class EPMRDPT_59293_VerifyThatTheFollowingWarningPopUpAppearsByHittingTheAssignTestButtonInProgressStatus {

  private String popupHeader = "Training Portal";
  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupUserProfile() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asLearningStudent())
        .clickProfileMenu()
        .clickProfileButton();
    new ProfileService()
        .clickRedirectToExaminatorIfTakeTestButtonExistsAndCloseBrowser();
    new HeaderScreen().openPage(getEnv());
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(asRecruiter())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickSearchLink()
        .enterSearchInputText(asLearningStudent().getUsername())
        .clickFindButton()
        .clickSearchResultByEmail(asLearningStudent().getUsername());
    profileScreen.switchToLastWindow();
  }

  @Test(priority = 1)
  public void checkEnglishTestInProgressPopUpDisplayed() {
    profileScreen.waitForAssignEnglishTestButtonVisibility()
        .clickAssignEnglishTestButton()
        .waitConfirmationPopUpForVisibility();
    assertTrue(profileScreen.isConfirmInformationPopUpDisplayed(),
        "'The English test in progress' pop-up is not displayed!");
  }

  @Test(priority = 2)
  public void checkPopUpHeaderText() {
    assertEquals(profileScreen.getPopupHeaderTitle(),
        popupHeader,
        "'The English test in progress' pop-up has incorrect header!");
  }

  @Test(priority = 3)
  public void checkPopUpOkButtonText() {
    assertEquals(profileScreen.getPopupOkButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_OK),
        "'The English test in progress' pop-up has incorrect text of 'OK' button!");
  }

  @Test(priority = 4)
  public void checkPopUpCancelButtonText() {
    assertEquals(profileScreen.getPopupCancelButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL),
        "'The English test in progress' pop-up has incorrect text of 'CANCEL' button!");
  }

  @Test(priority = 5)
  public void checkPopUpMessageText() {
    assertEquals(profileScreen.getPopupMessageText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_MESSAGE),
        "'The English test in progress' pop-up has incorrect text!");
  }

  @Test(priority = 6)
  public void checkIfPopUpClosesByClickingCross() {
    assertFalse(profileScreen.clickPopupCloseButton()
            .isConfirmInformationPopUpDisplayed(),
        "'The English test in progress' pop-up cross button doesn't work!");
  }
}