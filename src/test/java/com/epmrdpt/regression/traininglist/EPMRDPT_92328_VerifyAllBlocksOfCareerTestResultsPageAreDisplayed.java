package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CareerTestBlockScreen;
import com.epmrdpt.screens.CareerTestResultScreen;
import com.epmrdpt.services.CareerTestService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_92328_VerifyAllBlocksOfCareerTestResultsPageAreDisplayed",
    groups = {"full", "regression"})
public class EPMRDPT_92328_VerifyAllBlocksOfCareerTestResultsPageAreDisplayed {

  private CareerTestResultScreen careerTestResultScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void passCareerTestAndGoToTestResultPage() {
    new CareerTestBlockScreen()
        .clickCareerTestButton()
        .clickStartTestButton();
    careerTestResultScreen = new CareerTestService().takeCareerTest();
  }

  @Test
  public void checkPersonalProfileBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isPersonalProfileBlockDisplayed(),
        "Personal profile block is not displayed");
  }

  @Test
  public void checkCompatibleProfessionsBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isCompatibleProfessionsBlockDisplayed(),
        "Compatible professions block is not displayed");
  }

  @Test
  public void checkPsychologicalPortraitBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isPsychologicalPortraitBlockDisplayed(),
        "Psychological portrait block is not displayed");
  }

  @Test
  public void checkPsychologicalProfileDescriptionBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isPsychologicalProfileDescriptionBlockDisplayed(),
        "Psychological profile description block is not displayed");
  }

  @Test
  public void checkPsychologicalReactionsBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isPsychologicalReactionsBlockDisplayed(),
        "Psychological reactions block is not displayed");
  }

  @Test
  public void checkCopyResultLinkBlockIsDisplayed() {
    assertTrue(careerTestResultScreen.isCopyResultLinkBlockDisplayed(),
        "Copy result link block is not displayed");
  }
}
