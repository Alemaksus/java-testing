package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34235_VerifyThatAnotherFeedbackIsOpenedByClickingOnTheCorrespondingDot",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34235_VerifyThatAnotherFeedbackIsOpenedByClickingOnTheCorrespondingDot {

  private String trainingCountryName = "AutoTestCountry";
  private String trainingCityName = "AutoTestCity";
  private CenterScreen centerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupCenterScreen() {
    centerScreen = new CenterScreen();
    new TrainingCenterOnStartScreen().clickTrainingCenterCountryByName(trainingCountryName)
        .clickTrainingCenterCityByName(trainingCityName);
  }

  @Test(priority = 1)
  public void checkFeedbackBlockIsPresent() {
    assertTrue(centerScreen.isFeedbackSectionDisplayed(),
        "Feedback block is not displayed!");
  }

  @Test(priority = 2)
  public void checkAnotherFeedbackIsOpened() {
    String firstFeedbackDescription = centerScreen.getFeedbackDescriptionText();
    centerScreen.clickFeedbackWhiteDot();
    assertNotEquals(firstFeedbackDescription, centerScreen.getFeedbackDescriptionText(),
        "Another Feedback is not opened!");
  }
}
