package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34249_VerifyThatUserCanCreateTrainingCenterWithAllOptionalData",
    groups = {"full", "regression", "general"})

public class EPMRDPT_34249_VerifyThatUserCanCreateTrainingCenterWithAllOptionalData {

  private TrainingCenterManagementService trainingCenterManagementService;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private CenterScreen centerScreen;
  private TrainingCenter trainingCenter;
  private User user;
  private SoftAssert softAssert;
  private String trainingCenterCity;
  private final String trainingCenterCountry = "AutoTestCountry";

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34249_VerifyThatUserCanCreateTrainingCenterWithAllOptionalData(User user,
      String trainingCenterCity) {
    this.user = user;
    this.trainingCenterCity = trainingCenterCity;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateTrainingCenterScreen() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
    trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithUniversityAndFeedback(trainingCenterCountry, trainingCenterCity);
    trainingCenterManagementService = new TrainingCenterManagementService();
    trainingCenterManagementService.deleteTrainingCenterIfPresent(trainingCenter);
    trainingCenterManagementService.createTrainingCenterWithUniversityAndFeedback(trainingCenter);

  }

  @Test(priority = 1)
  public void checkThatWarningPopupAppears() {
    createOrEditTrainingCenterScreen = new CreateOrEditTrainingCenterScreen();
    assertTrue(createOrEditTrainingCenterScreen.isConfirmationPopupDisplayed(),
        "Warning popup is not appeared!");
  }

  @Test(priority = 2)
  public void checkThatTrainingCenterIsCreated() {
    createOrEditTrainingCenterScreen.mouseOverCreateConfirmationButton()
        .clickCreateConfirmationButton()
        .waitConfirmationPopupForInVisibility()
        .waitSaveChangesOrCreatePopUpForVisibility()
        .waitSaveChangesOrCreatePopUpForInVisibility();
    new HeaderScreen().clickCenterManagementLink();
    assertTrue(new TrainingCenterManagementService()
            .isTrainingCenterPresent(trainingCenter),
        "Training center is not created!");
  }

  @Test(priority = 3)
  public void checkThatUniversityAndFeedbackFieldsAreFilled() {
    new TrainingCenterManagementService()
        .openPreviewCenterScreen(trainingCenterCountry, trainingCenterCity);
    centerScreen = new CenterScreen().clickUniversityCard();
    softAssert = new SoftAssert();
    softAssert.assertEquals(centerScreen.getUniversityShortNameText(),
        trainingCenter.getUniversityShortName()
        , "University short name is wrong!");
    softAssert.assertEquals(centerScreen.getUniversityNameText().toLowerCase(),
        trainingCenter.getUniversityName().toLowerCase(),
        "University name is wrong!");
    softAssert.assertEquals(centerScreen.getUniversityDescriptionText(),
        trainingCenter.getUniversityDescription(),
        "University text is wrong!");
    softAssert.assertEquals(centerScreen.getFeedbackDescriptionText(),
        trainingCenter.getFeedbackDescription(),
        "Feedback text is wrong!");
    softAssert.assertEquals(centerScreen.getFeedbackAuthorsNameText(),
        trainingCenter.getFeedbackAuthorsName(),
        "Authors name is wrong!");
    softAssert.assertEquals(centerScreen.getFeedbackJobFunctionalText(),
        trainingCenter.getFeedbackJobFunctional(),
        "Job functional is wrong!");
    softAssert.assertAll();
  }
}
