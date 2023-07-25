package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckCreatingClassOnMyGroupsTab;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingClassService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44764_VerifyThatUserCanAddClassToTheScheduleComponentOnMyGroupsTab",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_44764_VerifyThatUserCanAddClassToTheScheduleComponentOnMyGroupsTab {

  private final String trainingName = "AutoTest_React";

  private TrainingClass expectedTrainingClass;
  private ReactLoginService reactLoginService;
  private ReactTrainingService reactTrainingService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenServiceInitialization() {
    reactLoginService = new ReactLoginService();
    reactTrainingService = new ReactTrainingService();
    expectedTrainingClass = forCheckCreatingClassOnMyGroupsTab();
  }

  @Test(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public void checkThatUserCanAddClassAndCreatedClassHasExpectedData(User user) {
    ReactTrainingClassService reactTrainingClassService = new ReactTrainingClassService();
    reactLoginService.loginAndGoToReactTrainingScreen(user);
    ReactTrainingScreen reactTrainingScreen = reactTrainingService
        .searchGroupByNameAndClickTrainingByName(expectedTrainingClass.getGroup(), trainingName);
    if (reactTrainingScreen
        .isClassTicketInScheduleByNameDisplayed(expectedTrainingClass.getName())) {
      reactTrainingService.deleteClassInSchedule(expectedTrainingClass.getName());
    }
    reactTrainingScreen.clickAddClassButton();
    reactTrainingService
        .fillAllRequiredFieldsWithCurrentDateToAddClassInSchedule(expectedTrainingClass)
        .openEditClassPopUpByName(expectedTrainingClass.getName());
    assertEquals(reactTrainingClassService.getTrainingClassFromEditClassPopUp().toString(),
        expectedTrainingClass.toString(),
        "Data of training class doesn't equals expected data!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteCreatedClassAndSignOutUser() {
    new ReactEditClassPopUpScreen().clickCloseEditClassPopUp();
    reactTrainingService.deleteClassInSchedule(expectedTrainingClass.getName());
    reactLoginService.signOut();
  }
}
