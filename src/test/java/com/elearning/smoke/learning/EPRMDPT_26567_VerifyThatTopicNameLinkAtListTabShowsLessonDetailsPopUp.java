package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.asStudentForLearningPage;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ListTabScreenOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPRMDPT_26567_VerifyThatTopicNameLinkAtListTabShowsLessonDetailsPopUp",
    groups = {"full", "student", "smoke"})
public class EPRMDPT_26567_VerifyThatTopicNameLinkAtListTabShowsLessonDetailsPopUp {

  private ListTabScreenOnLearningPageScreen listTabScreenOnLearningPageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    listTabScreenOnLearningPageScreen = new ListTabScreenOnLearningPageScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentForLearningPage())
        .clickLearningButton()
        .waitCurrentGroupsTabDisplayed()
        .clickListTab()
        .waitLessonTopicVisibility();
  }

  @Test
  public void checkLessonDetailPopUpAppearsAfterClickTopicName() {
    int index = new Random().nextInt(listTabScreenOnLearningPageScreen.getLessonTopicListSize());
    assertTrue(listTabScreenOnLearningPageScreen
            .clickLessonTopicByIndex(index)
            .isScreenLoaded(),
        "Lesson detail pop -up isn't displayed after clicking Topic name!");
  }
}
