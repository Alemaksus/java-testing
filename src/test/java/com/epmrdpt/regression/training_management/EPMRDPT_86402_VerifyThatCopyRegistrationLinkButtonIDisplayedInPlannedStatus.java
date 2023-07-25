package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_COPY_REGISTRATION_LINK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_86402_VerifyThatCopyRegistrationLinkButtonIDisplayedInPlannedStatus",
    groups = {"full", "regression"})
public class EPMRDPT_86402_VerifyThatCopyRegistrationLinkButtonIDisplayedInPlannedStatus {

  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private String trainingType = getValueOf(
      REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUpPage() {
    ReactTrainingManagementScreen reactTrainingManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .clickTrainingTypeDropDownTypeName(trainingType)
        .clickShowOnlySelectedButton()
        .clickApplyButton()
        .waitAllTrainingNameDisplayed();
    reactTrainingManagementScreen.clickTrainingNameByIndex(1);
    reactDetailTrainingScreen = new ReactDetailTrainingScreen()
        .waitScreenLoaded();
  }

  @Test
  public void checkCopyRegistrationLinkButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isCopyRegistrationLinkButtonDisplayed(),
        "The Copy Registration Link Button is not present in the screen");
    softAssert.assertEquals(reactDetailTrainingScreen.getCopyRegistrationLinkButtonText(),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_COPY_REGISTRATION_LINK_BUTTON),
        "The Text in the Button is incorrect");
    softAssert.assertAll();
  }
}
