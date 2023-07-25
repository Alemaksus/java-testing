package com.epmrdpt.regression.profile;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_24556_VerifyContentOfOpenInformationTabInEditProfile",
    groups = {"full", "regression"})
public class EPMRDPT_24556_VerifyContentOfOpenInformationTabInEditProfile {

  private ProfileEditionScreen profileEditionScreen;
  private SoftAssert softAssert;

  private static final String SKYPE_ICON = "skype-icon-reg.svg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileEditionScreen = new ProfileEditionScreen();
  }

  @Test(priority = 1)
  public void checkProfileEditionScreenLoading() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.asAdmin())
            .clickProfileMenu()
            .clickProfileButton()
            .clickEditProfileLink()
            .isScreenLoaded(),
        "Profile Edition page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkContentOfOpenInformationTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileEditionScreen.isEmailFieldInOpenInformationDisplayed(),
        "'Email' field under 'Open Information' tab isn't displayed!");
    softAssert.assertTrue(profileEditionScreen.isContactFieldInOpenInformationDisplayed(),
        "'Contact' field under 'Open Information' tab isn't displayed!");
    softAssert.assertTrue(profileEditionScreen.isSkypeFieldInOpenInformationDisplayed(),
        "'Skype' field under 'Open Information' tab isn't displayed!");
    softAssert.assertTrue(
        profileEditionScreen.getSkypeBackgroundImageInOpenInformation().contains(SKYPE_ICON),
        "'Skype-icon' in 'Skype-field' under 'Open Information' tab isn't displayed!");
    softAssert.assertTrue(profileEditionScreen.isAllowToShowCheckboxInOpenInformationDisplayed(),
        "'Allow to show' checkbox under 'Open Information' tab isn't displayed!");
    softAssert.assertEquals(profileEditionScreen.getAllowToShowCheckboxLabelInOpenInformationText(),
        LocaleProperties
            .getValueOf(LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_ALLOW_TO_SHOW),
        format("'Allow to show - Label' text isn't displayed as '%s'!",
            LocaleProperties
                .getValueOf(LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_ALLOW_TO_SHOW)));
    softAssert.assertAll();
  }
}
