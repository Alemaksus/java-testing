package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_DURATION_DAYS_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_DURATION_WEEKS_PATTERN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_ONGOING_DURATION;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44717_VerifyTheSpecialInformationAboutTrainingProgramOnTrainingDescriptionPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_44717_VerifyTheSpecialInformationAboutTrainingProgramOnTrainingDescriptionPage {

  private final String trainingName = "STI FOR autotest";
  private final String ongoingTrainingName = "AutoTest_WithFreePricing";
  private final String shortDurationTrainingName = "AutoTest_WithPaidPricing";
  private String patternDate = "LLLL yyyy";
  private String trainingLocationFromCard;

  private TrainingBlockScreen trainingBlockScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private HeaderScreen headerScreen;
  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingDescription() {
    headerScreen = new HeaderScreen();
    trainingCardsSectionService = new TrainingCardsSectionService();
    trainingBlockScreen = new TrainingBlockScreen()
        .waitTrainingCardsVisibility();
    new TrainingCardsSectionService()
        .clearAllFilters();
    if (trainingBlockScreen.isViewMoreTrainingsLinkDisplayedNoWait()) {
      trainingBlockScreen.clickViewMoreTrainingsLink()
          .waitViewMoreTrainingsLinkAbsent();
    }
    trainingLocationFromCard = trainingBlockScreen.getTrainingLocationTextByName(trainingName)
        .replace(", ", "\n");
    trainingDescriptionScreen = trainingBlockScreen.clickTrainingCardByName(trainingName)
        .waitTrainingInformationSectionForVisibility();
  }

  @Test(priority = 1)
  public void checkThatTrainingStartDateHasCorrectFormat() {
    assertTrue((StringUtils.isDateMatchExpectedPattern(
            trainingDescriptionScreen.getTrainingStartDateText(), patternDate)),
        "'Start date' field on Training description page has incorrect format!");
  }

  @Test(priority = 1)
  public void checkThatTrainingDurationHasCorrectWeeksFormat() {
    assertTrue(
        (trainingDescriptionScreen.getTrainingDurationText()
            .matches(getValueOf(TRAINING_DESCRIPTION_DURATION_WEEKS_PATTERN))),
        "Training duration has incorrect weeks format!");
  }

  @Test(priority = 1)
  public void checkTrainingFrequencyField() {
    assertFalse(trainingDescriptionScreen.getTrainingFrequencyText().isEmpty(),
        "'Frequency' field on Training description page is empty!");
  }

  @Test(priority = 1)
  public void checkLocationIcon() {
    assertFalse(trainingDescriptionScreen.getLocationBackgroundImageValue().isEmpty(),
        "'Location' icon on Training description page doesn't present!");
  }

  @Test(priority = 1)
  public void checkLocationField() {
    assertEquals(trainingDescriptionScreen.getLocationOfTrainingText(), trainingLocationFromCard,
        "'Location' field on Training description page isn't appropriate name from training card!");
  }

  @Test(priority = 1)
  public void checkTrainingCenterAddressField() {
    assertFalse(trainingDescriptionScreen.getTrainingCenterAddressText().isEmpty(),
        "'Address' field on Training description page is empty!");
  }

  @Test(priority = 1)
  public void checkLanguageIcon() {
    assertFalse(trainingDescriptionScreen.getLanguageBackgroundImageValue().isEmpty(),
        "'Language' icon on Training description page doesn't present!");
  }

  @Test(priority = 1)
  public void checkThatEnglishLanguageOfTheCourseHasCorrectText() {
    assertEquals(trainingDescriptionScreen.getPlannedLanguageText(),
        getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH),
        "English language of the course has incorrect text!");
  }

  @Test(priority = 2)
  public void checkThatOngoingTrainingDurationDisplayed() {
    headerScreen.clickEpamLogoLabel();
    trainingCardsSectionService.openTrainingDescription(ongoingTrainingName);
    assertEquals(trainingDescriptionScreen.getOngoingDurationText(),
        getValueOf(TRAINING_DESCRIPTION_ONGOING_DURATION),
        "'Ongoing' duration on Training description page isn't displayed!");
  }

  @Test(priority = 3)
  public void checkThatRussianLanguageOfTheCourseHasCorrectText() {
    assertEquals(trainingDescriptionScreen.getPlannedLanguageText(),
        getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN),
        "Russian language of the course has incorrect text!");
  }

  @Test(priority = 4)
  public void checkThatTrainingDurationHasCorrectDaysFormat() {
    headerScreen.clickEpamLogoLabel();
    trainingCardsSectionService.openTrainingDescription(shortDurationTrainingName);
    assertTrue(
        (trainingDescriptionScreen.getTrainingDurationText()
            .matches(getValueOf(TRAINING_DESCRIPTION_DURATION_DAYS_PATTERN))),
        "Training duration has incorrect days format!");
  }

  @Test(priority = 5)
  public void checkThatUkraineLanguageOfTheCourseHasCorrectText() {
    assertEquals(trainingDescriptionScreen.getPlannedLanguageText(),
        getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN),
        "Ukrainian language of the course has incorrect text!");
  }
}
