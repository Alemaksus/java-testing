package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_86400_VerifyThatEducationPracticePresentInTypeDDL",
    groups = {"full", "smoke", "manager"})
public class EPMRDPT_86400_VerifyThatEducationPracticePresentInTypeDDL {

  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingCreationGeneralInfoTab() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager())
        .clickCreateNewButton()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void verifyOptionInTypeDDL() {
    Assert.assertTrue(reactGeneralInfoTabScreen
            .getTypeDdlOptions()
            .contains(getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE)),
        String.format("Training type '%s' is not displayed!",
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_EDUCATIONAL_PRACTICE)));
  }

  @Test(priority = 2)
  public void verifyTypeDDLIsDisplayed() {
    Assert.assertTrue(reactGeneralInfoTabScreen
            .clickTrainingTypeDropDown()
            .isDDLDisplayed(),
        "Training type DDL is not displayed!");
  }
}
