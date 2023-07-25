package com.epmrdpt.smoke.profile;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_CONTACT;
import static com.epmrdpt.framework.properties.UserPropertyConst.USER_LOGIN_WITH_RANDOM_DATES;
import static com.epmrdpt.framework.properties.UserPropertyConst.USER_PASSWORD_WITH_RANDOM_DATES;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ProfileService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13159_VerifyThatUserCanEditProfile",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13159_VerifyThatUserCanEditProfile {

  private ProfileService profileService;
  private ProfileEditionScreen profileEditionScreen;
  private User usedUser;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileService = new ProfileService();
    profileEditionScreen = new ProfileEditionScreen();
    usedUser = UserFactory.userWithRandomData();
    usedUser.setUserName(UserProperty.getValueOf(USER_LOGIN_WITH_RANDOM_DATES));
    usedUser.setPassword(UserProperty.getValueOf(USER_PASSWORD_WITH_RANDOM_DATES));
    usedUser.setPreferredMethodOfCommunication(getValueOf(EDIT_PROFILE_OPEN_INFORMATION_CONTACT));
  }

  @Test(priority = 1)
  public void checkUserLogIn() {
    assertTrue(new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(usedUser)
        .isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 2)
  public void checkChangesSaving() {
    SoftAssert softAssert = new SoftAssert();
    new HeaderScreen()
        .clickProfileMenu()
        .clickProfileButton()
        .clickEditProfileLink()
        .waitCityOfResidenceNotEmpty()
        .waitCityOfStudyNotEmpty();
    User actualUser = profileService.fillUserFieldsAndReadData(usedUser);
    profileEditionScreen
        .clickSaveProfileButton()
        .waitEditProfileLinkDisplayed()
        .clickEditProfileLink()
        .waitCityOfResidenceNotEmpty()
        .waitCityOfStudyNotEmpty();
    softAssert.assertFalse(profileEditionScreen.getEmailFieldValue().isEmpty(),
        "E-mail isn't pre-filled!");
    softAssert.assertEquals(profileService.readUserFromEditProfile(), actualUser,
        "Changes were not saved!");
    softAssert.assertAll();
  }
}
