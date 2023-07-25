package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_DETAIL_HIDE_CLASS_LABEL;

import com.epmrdpt.screens.ReactClassesDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84090_VerifyThePresenceOfCheckboxHideClassInClassDetailsSectionInDA",
    groups = {"full", "regression"})
public class EPMRDPT_84090_VerifyThePresenceOfCheckboxHideClassInClassDetailsSectionInDA {

  private static final String TRAINING_NAME = "VM DA test";
  private static final String CLASSES_NAME = "AutoTest_affiliate";

  private ReactClassesDetailsScreen reactClassesDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openClassDetailPageAsTrainingManager() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitAllSpinnersAreNotDisplayed();
    reactClassesDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .waitAllSpinnersAreNotDisplayed()
        .clickClassesTabs()
        .waitDataLoading()
        .clickClassesByName(CLASSES_NAME);
  }

  @Test
  public void checkThatHideClassCheckboxIsPresent() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactClassesDetailsScreen.getHideClassCheckboxLabelText(),
        getValueOf(REACT_CLASSES_DETAIL_HIDE_CLASS_LABEL),
        String.format("Text '%s' is not present!", getValueOf(REACT_CLASSES_DETAIL_HIDE_CLASS_LABEL)));
    softAssert.assertTrue(reactClassesDetailsScreen.isHideClassCheckboxPresent(),
        "Hide class checkbox is not present!");
    softAssert.assertAll();
  }
}
