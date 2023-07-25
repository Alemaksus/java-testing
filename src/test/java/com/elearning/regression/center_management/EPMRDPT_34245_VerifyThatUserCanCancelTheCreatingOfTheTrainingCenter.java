package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.WARNING_POPUP_MESSAGE_CREATE_TRAINING_SCREEN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34245_VerifyThatUserCanCancelTheCreatingOfTheTrainingCenter",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34245_VerifyThatUserCanCancelTheCreatingOfTheTrainingCenter {

  private final static int DROP_DOWN_SELECT_INDEX = 1;

  private CreateOrEditTrainingCenterScreen createTrainingCenterScreen;
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34245_VerifyThatUserCanCancelTheCreatingOfTheTrainingCenter(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    trainingCenterManagementScreen = new LoginService()
        .loginAndSetLanguage(user)
        .clickCenterManagementLink();
    createTrainingCenterScreen = new CreateOrEditTrainingCenterScreen();
  }

  @Test(priority = 1)
  public void checkAfterClickingCancelButtonTrainingCenterManagementPageIsOpened() {
    assertTrue(trainingCenterManagementScreen
            .clickCreateTrainingCenterButton()
            .clickFirstSectionCancelButton()
            .isScreenLoaded(),
        "Training center page is not opened!");
  }

  @Test(priority = 2)
  public void checkWarningPopupAppearsIfAnyInformationWasAdded() {
    trainingCenterManagementScreen
        .clickCreateTrainingCenterButton()
        .clickSelectCountryField()
        .selectCountryFromDDL(DROP_DOWN_SELECT_INDEX)
        .clickFirstSectionCancelButton();
    assertTrue(createTrainingCenterScreen
            .isConfirmationPopupDisplayed(),
        "Confirmation pop-up is not appeared!");
  }

  @Test(priority = 3)
  public void checkTextFromWarningPopUpAfterCancelButtonClicking() {
    assertEquals(createTrainingCenterScreen
            .getConfirmationPopupMessageText(),
        getValueOf(WARNING_POPUP_MESSAGE_CREATE_TRAINING_SCREEN),
        "Text from message in confirmation pop-up is not correct!");
  }

  @Test(priority = 4)
  public void checkAfterClickingOkButtonOnPopUpTrainingCenterManagementPageIsOpened() {
    createTrainingCenterScreen
        .clickConfirmationPopupOkButton();
    assertTrue(trainingCenterManagementScreen
            .isScreenLoaded(),
        "Training center page is not opened!");
  }
}
