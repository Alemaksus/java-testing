package com.epmrdpt.regression.home;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34306_VerifyThePaidQuickFilter",
    groups = {"full", "regression"})
public class EPMRDPT_34306_VerifyThePaidQuickFilter {

  private TrainingBlockScreen trainingBlockScreen;
  private List<String> actualElementValuesList;
  private List<String> expectedElementValuesList;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupHomePage() {
    trainingBlockScreen = new TrainingBlockScreen();
    trainingBlockScreen.clickPaidButton();
    actualElementValuesList = trainingBlockScreen.getAllTrainingPaymentStatusesList();
    expectedElementValuesList = new ArrayList<>(actualElementValuesList);
    trainingBlockScreen.sortStatusesValuesByPaymentType(expectedElementValuesList);
  }

  @Test
  public void verifySortingAfterClickButtonPaid() {
    assertEquals(actualElementValuesList, expectedElementValuesList,
        "Training cards are displayed incorrectly after sorting");
  }
}
