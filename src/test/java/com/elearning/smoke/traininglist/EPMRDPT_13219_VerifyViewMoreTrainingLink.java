package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13219_VerifyViewMoreTrainingLink",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13219_VerifyViewMoreTrainingLink {

  private static final String EXPECTED_LINK_COLOR = "rgba(115, 183, 195, 1)";
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingBlockScreen = new TrainingBlockScreen();
    new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitSkillCardsForVisibility();
  }

  @Test(priority = 1)
  public void checkChangeColorMoreTrainingLink() {
    assertEquals(trainingBlockScreen
            .hoverOnMoreTrainingLink()
            .getColorMoreTrainingsLink(),
        EXPECTED_LINK_COLOR,
        "Link color wasn't changed after mouse hovering!");
  }

  @Test(priority = 2)
  public void checkAllTrainingCardsPresentedAfterClickingViewMoreTrainingsLink() {
    SoftAssert softAssert = new SoftAssert();
    int amountTrainingsCardBeforeClickingOnLink = trainingBlockScreen.getTrainingsCardsCount();
    int numberHiddenTrainingCards =
        Integer.parseInt(
            trainingBlockScreen.getViewMoreTrainingsNumberLabelText().replaceAll("\\(|\\)", ""));
    trainingBlockScreen.clickViewMoreTrainingsLink();
    int amountTrainingCardsAfterClickingOnLink = trainingBlockScreen.getTrainingsCardsCount();
    softAssert.assertEquals(
        amountTrainingCardsAfterClickingOnLink,
        amountTrainingsCardBeforeClickingOnLink + numberHiddenTrainingCards,
        "The amount of displayed training cards does not match the written amount on the link plus displayed by default!");
    softAssert.assertTrue(
        trainingBlockScreen
            .waitViewMoreTrainingsLinkAbsent()
            .isViewMoreTrainingsLinkAbsent(),
        "View More Trainings link isn't disappeared after click!");
  }
}
