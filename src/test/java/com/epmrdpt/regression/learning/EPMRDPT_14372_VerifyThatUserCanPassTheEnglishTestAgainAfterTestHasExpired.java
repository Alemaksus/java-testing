package com.epmrdpt.regression.learning;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TEST_EXPIRED_WARNING_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TEST_PASSED_INFO_TEXT;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14372_VerifyThatUserCanPassTheEnglishTestAgainAfterTestHasExpired",
    groups = {"full", "regression", "student"})
public class EPMRDPT_14372_VerifyThatUserCanPassTheEnglishTestAgainAfterTestHasExpired {

  private static String datePattern = "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2})";
  private static final String infoIconTextRegex =
      String.format(LocaleProperties.getValueOf(PROFILE_TEST_PASSED_INFO_TEXT), datePattern);
  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickProfileMenu()
        .clickProfileButton()
        .waitScreenLoading();
    profileScreen = new ProfileScreen();
  }

  @Test(priority = 1)
  public void checkTakeTestButtonIsDisplayed() {
    assertTrue(profileScreen.isEnglishTestButtonDisplayed());
  }

  @Test(priority = 2)
  public void checkEnglishTestInfoForExpiredResultsUser() {
    SoftAssert softAssert = new SoftAssert();
    profileScreen.hoverOnInfoIconOfEnglishTest();
    softAssert
        .assertTrue(Pattern.compile(infoIconTextRegex)
                .matcher(profileScreen.getInfoIconPopUpOfEnglishTest()).find(),
            "Info Icon Of English test isn't correct!");
    softAssert.assertEquals(profileScreen.getWarningExpiredEnglishTestTooltipText(),
        LocaleProperties.getValueOf(PROFILE_TEST_EXPIRED_WARNING_TEXT),
        "Warning icon text isn't correct!");
    softAssert.assertAll();
  }
}
