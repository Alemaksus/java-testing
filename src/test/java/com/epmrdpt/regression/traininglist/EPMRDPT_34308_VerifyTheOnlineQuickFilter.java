package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_BLENDED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_FACE_TO_FACE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_ONLINE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34308_VerifyTheOnlineQuickFilter",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34308_VerifyTheOnlineQuickFilter {

  private int registerTrainingCount;

  private TrainingBlockScreen trainingBlockScreen;
  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void trainingBlockOnStartScreenInitialization() {
    trainingCardsSectionService = new TrainingCardsSectionService();
    trainingBlockScreen = trainingCardsSectionService.setOnlineQuickFilter();
    registerTrainingCount = trainingBlockScreen.getListOfTrainingCardButtonText()
        .lastIndexOf(LocaleProperties.getValueOf(TRAINING_DESCRIPTION_REGISTER_BUTTON));
  }

  @Test(priority = 1)
  public void checkThatOnlineQuickFilterIsCorrectOnHomePage() {
    assertTrue(isOnlineQuickFilterCorrect(registerTrainingCount),
        "'Online' quick filter isn't correct on the Home page!");
  }

  @Test(priority = 2)
  public void checkThatOnlineQuickFilterIsCorrectOnTrainingListPage() {
    new HeaderScreen()
        .clickTrainingListNavigationLink()
        .waitSkillCardsForVisibility();
    trainingCardsSectionService.setOnlineQuickFilter();
    assertTrue(isOnlineQuickFilterCorrect(registerTrainingCount),
        "'Online' quick filter isn't correct on the Training List page!");
  }

  private boolean isOnlineQuickFilterCorrect(int registerTrainingCount) {
    List<String> registerTrainingFormats = trainingBlockScreen
        .getListOfTrainingCardFormatText()
        .subList(0, registerTrainingCount);
    return registerTrainingFormats.lastIndexOf(
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_ONLINE)) <
        registerTrainingFormats.indexOf(
            LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_BLENDED)) &&
        registerTrainingFormats.lastIndexOf(
            LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_BLENDED)) <
            registerTrainingFormats.indexOf(
                LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_FACE_TO_FACE));
  }
}
