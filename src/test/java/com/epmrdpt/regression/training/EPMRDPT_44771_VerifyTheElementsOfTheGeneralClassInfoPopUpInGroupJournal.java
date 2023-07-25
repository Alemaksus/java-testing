package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_DESCRIPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TOPIC;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_LOCATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TYPE_OF_CLASS;
import static com.epmrdpt.utils.StringUtils.isDateMatchExpectedPattern;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44771_VerifyTheElementsOfTheGeneralClassInfoPopUpInGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44771_VerifyTheElementsOfTheGeneralClassInfoPopUpInGroupJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final LocalDate todayDate = LocalDate.now();
  private final String dateFormat = "dd.MM.yyyy";
  private final String timeFormat = "HH:mm - HH:mm";
  private String trainerName = "AutoTrainer AutoTrainer";
  private ReactLessonInfoPopUpScreen reactLessonInfoPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactInfoPopUpScreen() {
    ReactTrainingScreen reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
    new ReactTrainingService()
        .navigateToMyGroupsTabAndSearchGroup(groupName);
    reactLessonInfoPopUpScreen = reactTrainingScreen
        .clickGroupJournalButton()
        .waitTableHeaderForVisibility()
        .clickInTableClassCardByDate(todayDate)
        .waitLessonAdditionalInfoForVisibility();
  }

  @Test(priority = 1)
  public void checkThatEditButtonIconDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isEditIconButtonDisplayed(),
        "Edit button icon on class info pop-up in Group Journal doesn't displayed!");
  }

  @Test(priority = 1)
  public void checkThatDeleteButtonIconDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isDeleteIconButtonDisplayed(),
        "Delete button icon on class info pop-up in Group Journal doesn't displayed!");
  }

  @Test(priority = 1)
  public void checkThatDateByPatternFormatDisplayed() {
    assertTrue(isDateMatchExpectedPattern(
            reactLessonInfoPopUpScreen.getDateLessonText(), dateFormat),
        "Date on class info pop-up in Group Journal has incorrect format!");
  }

  @Test(priority = 1)
  public void checkThatTimeByPatternFormatDisplayed() {
    assertTrue(isDateMatchExpectedPattern(
            reactLessonInfoPopUpScreen.getTimeLessonText(), timeFormat),
        "Time on class info pop-up in Group Journal has incorrect format!");
  }

  @Test(priority = 1)
  public void checkThatGroupNameHasText() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonGroupNameText().isEmpty(),
        "Group name on class info pop-up in Group Journal hasn't text!");
  }

  @Test(priority = 1)
  public void checkThatLocationTitleHasCorrectText() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonLocationTitleText(),
        getValueOf(REACT_TRAINING_LOCATION),
        "Location title on class info pop-up in Group Journal has incorrect text!");
  }

  @Test(priority = 1)
  public void checkThatLocationDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonLocationText().isEmpty(),
        "Location on class info pop-up in Group Journal hasn't text!");
  }

  @Test(priority = 1)
  public void checkThatTypeOfClassTitleHasCorrectText() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTypeTitleText(),
        getValueOf(REACT_TRAINING_TYPE_OF_CLASS),
        "Type title on class info pop-up in Group Journal has incorrect text!");
  }

  @Test(priority = 1)
  public void checkThatTypeOfClassDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonTypeText().isEmpty(),
        "Type on class info pop-up in Group Journal hasn't text!");
  }

  @Test(priority = 1)
  public void checkThatTopicOfClassTitleHasCorrectText() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTopicTitleText(),
        getValueOf(REACT_TRAINING_CLASS_TOPIC),
        "Topic title on class info pop-up in Group Journal has incorrect text!");
  }

  @Test(priority = 1)
  public void checkThatTopicOfClassDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonTopicText().isEmpty(),
        "Topic on class info pop-up in Group Journal hasn't text!");
  }

  @Test(priority = 1)
  public void checkThatDescriptionTitleHasCorrectText() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonDescriptionTitleText(),
        getValueOf(REACT_TRAINING_CLASS_DESCRIPTION),
        "Description title on class info pop-up in Group Journal has incorrect text!");
  }

  @Test(priority = 1)
  public void checkThatDescriptionDisplayed() {
    assertFalse(reactLessonInfoPopUpScreen.getLessonDescriptionText().isEmpty(),
        "Description on class info pop-up in Group Journal hasn't text!");
  }

  @Test(priority = 1)
  public void checkThatTrainerTitleDisplayed() {
    assertEquals(reactLessonInfoPopUpScreen.getLessonTrainerTitleText(),
        getValueOf(REACT_TRAINING_CLASS_TRAINER),
        "Trainer title on class info pop-up in Group Journal has incorrect text!");
  }

  @Test(priority = 1)
  public void checkThatTrainerImageDisplayed() {
    assertTrue(reactLessonInfoPopUpScreen.isLessonTrainerImageDisplayed(),
        "Trainer image on class info pop-up in Group Journal doesn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatTooltipWithTrainerNameDisplayed() {
    assertEquals(
        reactLessonInfoPopUpScreen.moveLessonInfoPopUpToTheTopOfTheScreen().mouseOverTrainerImage()
            .getTrainerNameText(), trainerName,
        "Tooltip on class info pop-up in Group Journal has incorrect trainer name!");
  }
}
