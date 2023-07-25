package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE;

import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_86401_VerifyThatIfSelectedEducationPracticeTrainingTypeTheSystemShallDisplayTwoTabs",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_86401_VerifyThatIfSelectedEducationPracticeTrainingTypeTheSystemShallDisplayTwoTabs {

  private ReactCreateTrainingScreen reactCreateTrainingScreen;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private String trainingType = getValueOf(
      REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactCreateTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager())
        .clickCreateNewButton()
        .waitScreenLoading();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen()
        .clickTrainingTypeDropDown()
        .selectTrainingTypeByName(trainingType)
        .waitScreenLoading();
  }

  @Test
  public void verifyThatTwoTabsAreDisplayedWhenEducationalPracticeIsChosen() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTrainingTypeValue(), trainingType,
        "Chosen training type isn't equal to the expected one!");
    softAssert.assertTrue(reactCreateTrainingScreen.isRegistrationFormTabDisplayedNoWait(),
        "'Registration Form' tab isn't displayed!");
    softAssert.assertTrue(reactCreateTrainingScreen.isGeneralInfoTabDisplayedNoWait(),
        "'General Info' tab isn't displayed!");
    softAssert.assertFalse(reactCreateTrainingScreen.isDescriptionTabDisplayedNoWait(),
        "'Description' tab is displayed!");
    softAssert.assertFalse(reactCreateTrainingScreen.isAutoReplyTabDisplayedNoWait(),
        "'AutoReply' tab is displayed!");
    softAssert.assertFalse(reactCreateTrainingScreen.isSurveyAndTestingTabDisplayedNoWait(),
        "'Survey and Testing' tab is displayed!");
    softAssert.assertFalse(reactCreateTrainingScreen.isIntegrationTabDisplayedNoWait(),
        "'Integration' tab is displayed!");
    softAssert.assertAll();
  }
}
