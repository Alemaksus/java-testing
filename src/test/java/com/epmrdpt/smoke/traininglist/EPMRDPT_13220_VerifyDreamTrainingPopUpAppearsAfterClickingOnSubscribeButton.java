package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13220_VerifyDreamTrainingPopUpAppearsAfterClickingOnSubscribeButton",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13220_VerifyDreamTrainingPopUpAppearsAfterClickingOnSubscribeButton {

  private HeaderScreen headerScreen;
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void trainingBlockScreenInitialization() {
    headerScreen = new HeaderScreen();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkSubscribeSectionText() {
    assertEquals(
        trainingBlockScreen.getSubscribeOnTrainingUpdatesText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_ON_UPDATES),
        "'Subscribe on updates' text is incorrect!");
  }

  @Test(priority = 2)
  public void checkHighlightedSubscribeButtonWhenHoverOver() {
    assertFalse(
        trainingBlockScreen.getSubscribeButtonColor()
            .contentEquals(trainingBlockScreen.getHoveredSubscribeButtonColor()),
        "Color 'Subscribe' button didn't change!");
  }

  @Test(priority = 3)
  public void checkSubscribePopUpAppearsAfterClickingOnSubscribeButton() {
    assertTrue(
        trainingBlockScreen.clickSubscribeButton().isScreenLoaded(),
        "'Subscribe to training' pop-up hasn't appeared!");
  }
}
