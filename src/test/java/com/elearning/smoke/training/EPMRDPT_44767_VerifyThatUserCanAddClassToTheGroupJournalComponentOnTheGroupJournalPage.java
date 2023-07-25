package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckCreatingClassOnTheGroupJournalPage;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingClassService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44767_VerifyThatUserCanAddClassToTheGroupJournalComponentOnTheGroupJournalPage",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_44767_VerifyThatUserCanAddClassToTheGroupJournalComponentOnTheGroupJournalPage {

  private TrainingClass expectedTrainingClass;
  private ReactLoginService reactLoginService;
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactLoginService = new ReactLoginService();
    reactGroupJournalService = new ReactGroupJournalService();
    expectedTrainingClass = forCheckCreatingClassOnTheGroupJournalPage();
  }

  @Test(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public void checkThatCreatedClassOnTheGroupJournalPageHasExpectedData(User user) {
    ReactTrainingClassService reactTrainingClassService = new ReactTrainingClassService();
    reactLoginService.loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(expectedTrainingClass.getGroup());
    deleteClassIfNeeded();
    reactGroupJournalScreen.clickAddClassButton();
    reactGroupJournalService
        .fillAllRequiredFieldsToAddClassInGroupJournal(expectedTrainingClass)
        .openEditClassPopUpByDate(expectedTrainingClass.getStartDate());
    assertEquals(reactTrainingClassService.getTrainingClassFromEditClassPopUp().toString(),
        expectedTrainingClass.toString(),
        "Data of training class doesn't equals expected data!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteCreatedClassAndSignOutUser() {
    new ReactEditClassPopUpScreen().clickCloseEditClassPopUp();
    reactGroupJournalService.deleteClassInTableByDate(expectedTrainingClass.getStartDate());
    reactLoginService.signOut();
  }

  private void deleteClassIfNeeded() {
    if (reactGroupJournalScreen.isClassInTableByDateDisplayed(
        expectedTrainingClass.getStartDate())) {
      reactGroupJournalService.deleteClassInTableByDate(expectedTrainingClass.getStartDate());
    }
  }
}
