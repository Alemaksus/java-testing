package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CareerTestBlockScreen;
import com.epmrdpt.screens.CareerTestResultScreen;
import com.epmrdpt.services.CareerTestService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_92327_VerifyCareerTestResultsPageIsDisplayedWhenUserFinishesTheTest",
    groups = {"full", "smoke"})
public class EPMRDPT_92327_VerifyCareerTestResultsPageIsDisplayedWhenUserFinishesTheTest {

  private CareerTestResultScreen careerTestResultScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void passCareerTestAndGoToTestResultPage() {
    new CareerTestBlockScreen()
        .clickCareerTestButton()
        .clickStartTestButton()
        .waitScreenLoading();
    careerTestResultScreen = new CareerTestService().takeCareerTest();
  }

  @Test
  public void checkCareerTestResultIsDisplayed() {
    assertTrue(careerTestResultScreen.isScreenLoaded(),
        "Career test result screen is not displayed");
  }
}
