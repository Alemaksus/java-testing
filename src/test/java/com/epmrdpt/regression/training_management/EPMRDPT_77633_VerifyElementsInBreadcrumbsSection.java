package com.epmrdpt.regression.training_management;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77633_VerifyElementsInBreadcrumbsSection",
    groups = {"full", "regression"})
public class EPMRDPT_77633_VerifyElementsInBreadcrumbsSection {

  private String trainingName = "Autotest with Registration Test";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77633_VerifyElementsInBreadcrumbsSection(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTraining() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitTitleForVisibility();
  }

  @Test(priority = 1)
  public void checkHomeIcon() {
    Assert.assertTrue(
        reactDetailTrainingScreen.isHomeButtonDisplayed(),
        "Home icon is not displayed!"
    );
  }

  @Test(priority = 2)
  public void checkPlanningTitle() {
    Assert.assertTrue(
        reactDetailTrainingScreen.isPlanningTitleDisplayed(),
        "Planning title is not displayed!"
    );
  }

  @Test(priority = 3)
  public void checkTrainingName() {
    Assert.assertEquals(
        reactDetailTrainingScreen.getTrainingTitleText(),
        trainingName,
        "Training's name is not correct!"
    );
  }
}
