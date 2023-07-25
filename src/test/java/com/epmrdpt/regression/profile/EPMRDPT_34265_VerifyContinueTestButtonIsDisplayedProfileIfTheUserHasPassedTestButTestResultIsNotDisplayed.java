package com.epmrdpt.regression.profile;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34265_VerifyContinueTestButtonIsDisplayedProfileIfTheUserHasPassedTestButTestResultIsNotDisplayed",
    groups = {"full", "regression", "student"})
public class EPMRDPT_34265_VerifyContinueTestButtonIsDisplayedProfileIfTheUserHasPassedTestButTestResultIsNotDisplayed {

  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    profileScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.userForRegistrationWizard())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickProfileButton()
        .waitTrainingInfoDisplayed();
  }

  @Test
  public void checkContinueTestButtonDisplayed() {
    assertTrue(profileScreen.isContinueEnglishTestButtonDisplayed(),
        "'Continue test' button isn't displayed!");
  }

  @Test
  public void checkFollowingMessageCorrectUnderContinueTestButton() {
    assertEquals(profileScreen.getEnglishResultMessageText(),
        getValueOf(LocalePropertyConst.PROFILE_FOLLOWING_MESSAGE_UNDER),
        "Following message isn't correct!");
  }
}
