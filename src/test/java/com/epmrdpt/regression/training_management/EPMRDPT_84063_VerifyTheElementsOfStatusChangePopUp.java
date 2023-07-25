package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_LEARNING_STATUS_CHANGE_BODY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(
    description = "EPMRDPT_84063_VerifyTheElementsOfStatusChangePopUp",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84063_VerifyTheElementsOfStatusChangePopUp {

  private String trainingName = "IT QA";
  private String groupName = "Automated Testing 1";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToGroup(trainingName, groupName);
  }

  @Test(priority = 1)
  public void verifyStatusChangePopUpIsDisplayed() {
    reactGroupDetailsScreen.clickLearningStatusButton();
    Assert.assertTrue(reactGroupDetailsScreen.isStatusChangePopUpDisplayed(),
        "Status change pop-up isn't displayed!");
  }

  @Test(priority = 2)
  public void verifyStatusChangePopUpHeader() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpHeaderDisplayed(),
        "Pop-up header isn't displayed!");
    softAssert.assertEquals(reactGroupDetailsScreen.getLearningStatusChangePopUpHeaderText(),
        getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE),
        "Pop-up header text isn't equal to the expected one!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyStatusChangePopUpCloseButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isCloseLearningStatusChangePopUpButtonDisplayed(),
        "Pop-up Close button isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isCloseLearningStatusChangePopUpButtonClickable(),
        "Pop-up Close button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyStatusChangePopUpBodyText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpBodyTextDisplayed(),
        "Pop-up body text isn't displayed!");
    softAssert.assertEquals(reactGroupDetailsScreen.getLearningStatusChangePopUpBodyText(),
        getValueOf(REACT_GROUP_DETAILS_SCREEN_LEARNING_STATUS_CHANGE_BODY),
        "Pop-up body text isn't equal to the expected one!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyStatusChangePopUpCancelButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpCancelButtonDisplayed(),
            "Pop-up Cancel button isn't displayed!");
    softAssert
        .assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpCancelButtonClickable(),
            "Pop-up Cancel button isn't clickable!");
    softAssert.assertEquals(reactGroupDetailsScreen.getLearningStatusChangePopUpCancelButtonText(),
        getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON),
        "Pop-up Cancel button isn't equal to the expected one!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyStatusChangePopUpYesButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpYesButtonDisplayed(),
            "Pop-up Yes button isn't displayed!");
    softAssert
        .assertTrue(reactGroupDetailsScreen.isLearningStatusChangePopUpYesButtonClickable(),
            "Pop-up Yes button isn't clickable!");
    softAssert.assertEquals(reactGroupDetailsScreen.getLearningStatusChangePopUpYesButtonText(),
        getValueOf(PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON),
        "Pop-up Yes button isn't equal to the expected one!");
    softAssert.assertAll();
  }
}
