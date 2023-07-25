package com.epmrdpt.smoke.hometraining;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_ON_UPDATES;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22272_VerifyThatDreamTrainingPopupAppearsAfterClickingOnSubscribeButton",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_22272_VerifyThatDreamTrainingPopupAppearsAfterClickingOnSubscribeButton {

  private static final String EXPECTED_BACKGROUND_COLOR = "rgba(115, 183, 195, 1)";
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUpTrainingBlockOnStartScreen() {
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkSubscribeOnUpdateText() {
    assertEquals(trainingBlockScreen.getSubscribeOnTrainingUpdatesText(),
        LocaleProperties.getValueOf(TRAINING_LIST_SUBSCRIBE_ON_UPDATES),
        "'Subscribe on updates' text is incorrect!");
  }

  @Test(priority = 2)
  public void checkSubscribeButtonText() {
    assertEquals(trainingBlockScreen.getSubscribeButtonText(),
        LocaleProperties.getValueOf(TRAINING_LIST_SUBSCRIBE_BUTTON),
        "Button text is incorrect!");
  }

  @Test(priority = 3)
  public void checkSubscribeButtonIsHighlightedWhenHovered() {
    assertEquals(trainingBlockScreen.getHoveredSubscribeButtonColor(),
        EXPECTED_BACKGROUND_COLOR,
        "Subscribe button is not highlighted!");
  }

  @Test(priority = 4)
  public void checkDreamTrainingPopUpIsAppeared() {
    assertTrue(trainingBlockScreen
            .clickSubscribeButton()
            .isScreenLoaded(),
        "Pop-up 'Dream training' is not appeared!");
  }
}
