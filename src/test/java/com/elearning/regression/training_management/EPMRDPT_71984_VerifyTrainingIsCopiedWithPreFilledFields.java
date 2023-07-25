package com.epmrdpt.regression.training_management;

import com.epmrdpt.bo.Training;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_71984_VerifyTrainingIsCopiedWithPreFilledFields",
    groups = {"full", "react", "regression"})
public class EPMRDPT_71984_VerifyTrainingIsCopiedWithPreFilledFields {

  private String trainingName = "Auto_Training_With_All_Text_Blocks_Filled_In";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private Training existingTraining;
  private Training newTraining;
  private User user;

  @Factory(dataProvider = "Provider of users with Reports tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_71984_VerifyTrainingIsCopiedWithPreFilledFields(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    existingTraining = reactTrainingManagementService.getTraining();
  }

  @Test
  public void checkTrainingIsCopiedWithPreFilledFields() {
    reactDetailTrainingScreen.clickCreateCopyButton();
    reactGeneralInfoTabScreen.switchToLastWindow();
    newTraining = reactTrainingManagementService.getTraining();
    existingTraining.setName("Copy - " + existingTraining.getName());
    Assert.assertEquals(existingTraining, newTraining,
        "Training isn't copied with pre-filled fields!");
  }
}
