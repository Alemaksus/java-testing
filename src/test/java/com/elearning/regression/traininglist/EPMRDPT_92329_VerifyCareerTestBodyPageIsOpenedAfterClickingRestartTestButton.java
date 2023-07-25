package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CareerTestBlockScreen;
import com.epmrdpt.screens.CareerTestQuestionnaireScreen;
import com.epmrdpt.services.CareerTestService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_92329_VerifyCareerTestBodyPageIsOpenedAfterClickingRestartTestButton",
    groups = {"full", "regression"})
public class EPMRDPT_92329_VerifyCareerTestBodyPageIsOpenedAfterClickingRestartTestButton {

  private CareerTestQuestionnaireScreen careerTestQuestionnaireScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void passCareerTestAndGoToTestResultPage() {
    new CareerTestBlockScreen()
        .clickCareerTestButton()
        .clickStartTestButton();
    careerTestQuestionnaireScreen = new CareerTestService().takeCareerTest()
        .clickRestartTestButton();
  }

  @Test
  public void checkCareerTestQuestionnaireIsLoaded() {
    assertTrue(careerTestQuestionnaireScreen.isScreenLoaded(),
        "Career test questionnaire screen is not loaded!");
  }

  @Test
  public void checkNoAnsweredQuestionOnPage() {
    assertFalse(careerTestQuestionnaireScreen.isAnsweredQuestionPresent(),
        "There are answered questions on page!");
  }
}
