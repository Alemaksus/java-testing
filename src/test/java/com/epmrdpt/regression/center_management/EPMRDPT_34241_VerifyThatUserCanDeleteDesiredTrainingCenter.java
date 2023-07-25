package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34241_VerifyThatUserCanDeleteDesiredTrainingCenter",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34241_VerifyThatUserCanDeleteDesiredTrainingCenter {

  private final String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity;
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private TrainingCenter trainingCenter;
  private TrainingCenterManagementService trainingCenterManagementService;
  private User user;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34241_VerifyThatUserCanDeleteDesiredTrainingCenter
      (User user, String trainingCenterCity) {
    this.user = user;
    this.trainingCenterCity = trainingCenterCity;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenter() {
    new LoginService()
        .loginAndSetLanguage(user)
        .clickCenterManagementLink();
    trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry, trainingCenterCity);
    trainingCenterManagementService = new TrainingCenterManagementService();
    trainingCenterManagementService
        .deleteTrainingCenterIfPresent(trainingCenter);
    trainingCenterManagementService
        .createTrainingCenter(trainingCenter);
    trainingCenterManagementScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
  }

  @Test(priority = 1)
  public void checkListOfActionsDisplayed() {
    assertTrue(
        trainingCenterManagementScreen
            .clickCountryDropDown()
            .selectCountryFromDDLByName(trainingCenterCountry)
            .clickApplyButton()
            .clickCenterThreeDotsContextMenuByCityName(trainingCenterCity)
            .isListOfActionsForTrainingCenterContextMenuButtonDisplayed(),
        "List of actions are not appeared after clicking 'three dots' context menu!");
  }

  @Test(priority = 2)
  public void checkConfirmationPopUpAppearsAfterDeletingCenter() {
    assertTrue(trainingCenterManagementScreen
            .mouseOverDeleteTrainingCenterButton()
            .clickDeleteTrainingCenterButton()
            .isConfirmationPopUpDisplayed(),
        "Confirmation pop-up is not appeared after deleting center!");
  }

  @Test(priority = 3)
  public void checkConfirmationPopUpIsClosedAfterOkClicking() {
    assertFalse(trainingCenterManagementScreen
            .mouseOverModalWindowConfirmationButton()
            .clickModalWindowConfirmationButton()
            .waitSuccessfulNotificationPopUpInvisibility()
            .isConfirmationPopUpDisplayed(),
        "Confirmation pop-up is not closed after clicking 'ok' button!");
  }

  @Test(priority = 4)
  public void checkTrainingCenterIsDeleted() {
    assertFalse(trainingCenterManagementService
            .isTrainingCenterPresent(trainingCenter),
        "Training center is not deleted!");
  }
}
