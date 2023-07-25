package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.CONFIRMATION_POPUP_MESSAGE_CREATE_EDIT_TRAINING_SCREEN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_34250_VerifyThatIfTheUserFillsInOnlyTheEnglishTabOnCreateEditTrainingCenterPageTheWarningPopUpAppears",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34250_VerifyIfFillsOnlyEnglishTabOnCreateEditTrainingCenterPagePopUpAppears {

  private final String expectedConfirmationPopupText = LocaleProperties
      .getValueOf(CONFIRMATION_POPUP_MESSAGE_CREATE_EDIT_TRAINING_SCREEN);
  private final String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private TrainingCenterManagementService trainingCenterManagementService;
  private TrainingCenter trainingCenter;
  private User user;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34250_VerifyIfFillsOnlyEnglishTabOnCreateEditTrainingCenterPagePopUpAppears
      (User user, String trainingCenterCity) {
    this.user = user;
    this.trainingCenterCity = trainingCenterCity;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenter() {
    trainingCenterManagementService = new TrainingCenterManagementService();
    trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry, trainingCenterCity);
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
    trainingCenterManagementService.deleteTrainingCenterIfPresent(trainingCenter);
    createOrEditTrainingCenterScreen = trainingCenterManagementService
        .fillTheFieldsCreateTrainingCenterScreen(trainingCenter);
  }

  @Test(priority = 1)
  public void checkConfirmationPopupDisplayedOnCreateTrainingCenterPage() {
    assertTrue(createOrEditTrainingCenterScreen
            .clickCreateOrSaveTrainingCenterTopButton()
            .isConfirmationPopupDisplayed(),
        "The confirmation pop-up after click on the 'Create' button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkConfirmationPopUpTextOnCreateTrainingCenterPage() {
    assertEquals(createOrEditTrainingCenterScreen.getConfirmationPopupMessageText(),
        expectedConfirmationPopupText,
        "The confirmation pop-up after click on the 'Create' button has incorrect text!");
  }

  @Test(priority = 3)
  public void checkConfirmationPopUpDisplayedOnEditTrainingCenterPage() {
    assertTrue(createOrEditTrainingCenterScreen.mouseOverCreateConfirmationButton()
            .clickCreateConfirmationButton()
            .clickCreateOrSaveTrainingCenterTopButton()
            .isConfirmationPopupDisplayed(),
        "The confirmation pop-up after click on the 'Save' button isn't displayed!");
  }

  @Test(priority = 4)
  public void checkConfirmationPopUpTextOnEditTrainingCenterPage() {
    assertEquals(createOrEditTrainingCenterScreen.getConfirmationPopupMessageText(),
        expectedConfirmationPopupText,
        "The confirmation pop-up after click on the 'Save' button has incorrect text!");
  }
}
