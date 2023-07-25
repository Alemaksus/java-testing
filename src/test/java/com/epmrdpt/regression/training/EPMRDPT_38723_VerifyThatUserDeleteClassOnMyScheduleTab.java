package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckDeletingClassFromScheduleTab;
import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38723_VerifyThatUserDeleteClassOnMyScheduleTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38723_VerifyThatUserDeleteClassOnMyScheduleTab {

  private ReactTrainingScreen reactTrainingScreen;
  private ReactTrainingService reactTrainingService;
  private TrainingClass trainingClass;

  private final String trainingName = "AutoTest_React";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewClass() {
    reactTrainingScreen = new ReactTrainingScreen();
    reactTrainingService = new ReactTrainingService();
    trainingClass = forCheckDeletingClassFromScheduleTab();
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer());
    reactTrainingService
        .searchGroupByNameAndClickTrainingByName(trainingClass.getGroup(), trainingName)
        .waitAddClassButtonForVisibility()
        .clickAddClassButton();
    reactTrainingService
        .fillAllRequiredFieldsWithCurrentDateToAddClassInSchedule(trainingClass);
  }

  @Test
  public void checkThatUserCanDeleteClassOnMyScheduleTab() {
    reactTrainingScreen.clickMyScheduleTab();
    reactTrainingService.deleteClassInSchedule(trainingClass.getName());
    assertFalse(reactTrainingScreen.isClassTicketInScheduleByNameDisplayed(trainingClass.getName()),
        "Class doesn't deleted!");
  }
}
