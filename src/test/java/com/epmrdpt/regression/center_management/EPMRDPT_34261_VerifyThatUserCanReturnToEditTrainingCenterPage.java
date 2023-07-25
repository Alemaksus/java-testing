package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34261_VerifyThatUserCanReturnToEditTrainingCenterPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34261_VerifyThatUserCanReturnToEditTrainingCenterPage {

  private final String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCityName;
  private TrainingCenterManagementService trainingCenterManagementService;
  private User user;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34261_VerifyThatUserCanReturnToEditTrainingCenterPage(
      User user, String city) {
    this.user = user;
    this.trainingCenterCityName = city;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    TrainingCenter trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry, trainingCenterCityName);
    trainingCenterManagementService = new TrainingCenterManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
    trainingCenterManagementService.deleteTrainingCenterIfPresent(trainingCenter);
    trainingCenterManagementService.createTrainingCenter(trainingCenter);
    new CreateOrEditTrainingCenterScreen()
        .mouseOverPreviewButton()
        .clickPreviewButton()
        .waitCreatedCenterNameInPreviewVisibility(trainingCenter.getName());
  }

  @Test(priority = 1)
  public void checkThatEditTrainingCenterPageOpened() {
    assertTrue(new CenterScreen().clickBackToEditButton().isScreenLoaded(),
        "Training Center edit page isn't opened!");
  }
}
