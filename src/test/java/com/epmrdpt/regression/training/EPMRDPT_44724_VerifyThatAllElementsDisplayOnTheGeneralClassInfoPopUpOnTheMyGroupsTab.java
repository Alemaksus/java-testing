package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_DESCRIPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TOPIC;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GO_TO_JOURNAL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_LOCATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TYPE_OF_CLASS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44724_VerifyThatAllElementsDisplayOnTheGeneralClassInfoPopUpOnTheMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44724_VerifyThatAllElementsDisplayOnTheGeneralClassInfoPopUpOnTheMyGroupsTab {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String classTopic = "AutoTest_ClassDurationMore60Min";

  private User user;
  private ReactLessonInfoPopUpScreen reactLessonInfoPopUpScreen;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44724_VerifyThatAllElementsDisplayOnTheGeneralClassInfoPopUpOnTheMyGroupsTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactLessonInfoPopUpScreenInitialization() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitScheduleForVisibility();
    reactLessonInfoPopUpScreen = new ReactTrainingService()
        .openGroupScheduleByName(groupName, classTopic)
        .clickInScheduleClassCardByTopic(classTopic)
        .waitLessonInfoForVisibility();
  }

  @Test
  public void checkThatDateOnGeneralClassInfoPopUpDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isLessonDateDisplayed(),
        "Date on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatEditIconButtonOnGeneralClassInfoPopUpDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isEditIconButtonDisplayed(),
        "Edit icon button on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatDeleteIconButtonOnGeneralClassInfoPopUpDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isDeleteIconButtonDisplayed(),
        "Delete icon button on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatTimeOnGeneralClassInfoPopUpDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isLessonTimeDisplayed(),
        "Time on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatGroupNameOnGeneralClassInfoPopUpDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonGroupNameText().isEmpty(),
        "Group name on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatLocationTitleOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonLocationTitleText(),
        getValueOf(REACT_TRAINING_LOCATION),
        "Location title on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatLocationOnGeneralClassInfoPopUpDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonLocationText().isEmpty(),
        "Location on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatTypeTitleOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTypeTitleText(),
        getValueOf(REACT_TRAINING_TYPE_OF_CLASS),
        "Type title on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatTypeOnGeneralClassInfoPopUpDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonTypeText().isEmpty(),
        "Type on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatTopicTitleOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTopicTitleText(),
        getValueOf(REACT_TRAINING_CLASS_TOPIC),
        "Topic title on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatTopicOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTopicText(), classTopic,
        "Topic on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatDescriptionTitleOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonDescriptionTitleText(),
        getValueOf(REACT_TRAINING_CLASS_DESCRIPTION),
        "Description title on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatDescriptionOnGeneralClassInfoPopUpDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonDescriptionText().isEmpty(),
        "Description on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatTrainerTitleOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTrainerTitleText(),
        getValueOf(REACT_TRAINING_CLASS_TRAINER),
        "Trainer title on general class info pop-up has incorrect text!");
  }

  @Test
  public void checkThatTrainerImageOnGeneralClassInfoPopUpDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isLessonTrainerImageDisplayed(),
        "Trainer image on general class info pop-up doesn't displayed!");
  }

  @Test
  public void checkThatGoToJournalButtonOnGeneralClassInfoPopUpDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getGoToJournalButtonText(),
        getValueOf(REACT_TRAINING_GO_TO_JOURNAL),
        "'Go to journal' button on general class info pop-up has incorrect text!");
  }
}
