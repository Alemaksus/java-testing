package com.epmrdpt.regression.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CLASSES_STATUS_CHANGE_POP_UP_UNSAVED_CHANGES_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactStatusChangePopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84066_VerifyThatModalDialogAppearsWhenTryingToSwitchStatusGroupWithUnsavedChanges",
    groups = {"full", "regression"})
public class EPMRDPT_84066_VerifyThatModalDialogAppearsWhenTryingToSwitchStatusGroupWithUnsavedChanges {

  private final String trainingName = "AutoTestDepartmentAffiliateFormGroup";
  private final String className = "Department affiliate 1";
  private final String newClassName = "New Class Name";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactStatusChangePopUpScreen reactStatusChangePopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupDetailsScreen() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToClass(trainingName, className)
        .waitAllSpinnersAreNotDisplayed()
        .typeNewGroupName(newClassName)
        .clickLearningStatusButton();
    reactStatusChangePopUpScreen = new ReactStatusChangePopUpScreen();
  }

  @Test(priority = 1)
  public void checkEnteredClassName() {
    assertEquals(
        reactGroupDetailsScreen.getGroupNameText(),
        newClassName,
        "New class name has not been entered!"
    );
  }

  @Test(priority = 2)
  public void checkModalDialog() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactStatusChangePopUpScreen.isScreenLoaded(),
        "Modal window dialog has not been loaded!"
    );
    softAssert.assertEquals(
        reactStatusChangePopUpScreen.getStatusChangeTitleText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP),
        "Status change title text is not correct!"
    );
    softAssert.assertTrue(
        reactStatusChangePopUpScreen.isCrossButtonDisplayed(),
        "Cross button is not displayed!"
    );
    softAssert.assertEquals(
        reactStatusChangePopUpScreen.getStatusChangeMessageAreaText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_CLASSES_STATUS_CHANGE_POP_UP_UNSAVED_CHANGES_TEXT),
        "Status change message text is not correct!"
    );
    softAssert.assertEquals(
        reactStatusChangePopUpScreen.getCancelButtonText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON),
        "Cancel button text is not correct!"
    );
    softAssert.assertEquals(
        reactStatusChangePopUpScreen.getConfirmationOfChangingStatusButtonText(),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON),
        "Save changes button text is not correct!"
    );
    softAssert.assertAll();
  }
}
