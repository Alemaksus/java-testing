package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38724_VerifyAUserCanOpenCorrespondedGroupJournalFromTheGeneralClassInfoPopUp",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38724_VerifyAUserCanOpenCorrespondedGroupJournalFromTheGeneralClassInfoPopUp {

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenIsLoading() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test
  public void checkThatGeneralClassInfoPopUpDisplayed() {
    String groupNameFromLessonInfoPopUp = reactTrainingScreen
        .clickClassCard()
        .waitLessonInfoForVisibility()
        .getLessonGroupNameText();
    String groupNameFromGroupJournal = new ReactLessonInfoPopUpScreen()
        .clickGoToJournalButton()
        .waitTableHeaderForVisibility()
        .getGroupNameFromBredCrumbs();
    assertEquals(groupNameFromLessonInfoPopUp, groupNameFromGroupJournal);
  }
}
