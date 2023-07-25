package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_CHOOSE_THIS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_HOW_TO_GET_STARTED_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_REQUIRED_SKILLS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_SKILL_DESCRIPTION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_TRAINING_DETAILS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_TRAINING_PROGRAM_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_VIDEO_BLOCK_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_USEFUL_LINKS_LABEL;

import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_82086_VerifyThatTextBlockSectionContainsNecessaryFields",
    groups = {"full", "regression"})
public class EPMRDPT_82086_VerifyThatTextBlockSectionContainsNecessaryFields {

  private final String trainingName = "AutoTestWithCustom";
  private final String recommendedTraining = "Python";
  private final String opacity = "0.3";
  private ReactDescriptionTabScreen reactDescriptionTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTraining() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactDescriptionTabScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickDescriptionTabFromTrainingScreen()
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test(priority = 1)
  public void checkTrainingDetailsBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_DETAILS_LABEL)),
        "Label for Training Details block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_DETAILS_LABEL)),
        "Input for Training Details block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSkillDescriptionBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_SKILL_DESCRIPTION_LABEL)),
        "Label for Skill Description is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_SKILL_DESCRIPTION_LABEL)),
        "Input for Skill Description is not displayed!"
    );
    softAssert.assertEquals(
        reactDescriptionTabScreen
            .getOpacityOfInputBlockByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_SKILL_DESCRIPTION_LABEL)),
        opacity,
        "Opacity for Skill Description is not correct!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputContainerDisabledByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_SKILL_DESCRIPTION_LABEL)),
        "Input for Skill Description is not disabled!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRequiredSkillsBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_REQUIRED_SKILLS_LABEL)),
        "Label for Required Skills block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_REQUIRED_SKILLS_LABEL)),
        "Input for Required Skills block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkWhatIfIDoNotMeetRequirementsBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_USEFUL_LINKS_LABEL)),
        "Label for What If I Do Not Meet Requirements block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_USEFUL_LINKS_LABEL)),
        "Input for What If I Do Not Meet Requirements block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkChooseThisTrainingIfYouBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_CHOOSE_THIS_LABEL)),
        "Label for Choose This Training If You block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_CHOOSE_THIS_LABEL)),
        "Input for Choose This Training If You block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkTrainingProgramBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_PROGRAM_LABEL)),
        "Label for Training Program block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_PROGRAM_LABEL)),
        "Input for Training Program block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkHowToJoinBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_HOW_TO_GET_STARTED_LABEL)),
        "Label for How To Join block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_HOW_TO_GET_STARTED_LABEL)),
        "Input for How To Join block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkPaidConsultationsInfoBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL)),
        "Label for Paid Consultations Info block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL)),
        "Input for Paid Consultations Info block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkVideoBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_VIDEO_BLOCK_LABEL)),
        "Label for Video block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_VIDEO_BLOCK_LABEL)),
        "Input for Video block is not displayed!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkRecommendedTrainingBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        "Label for Recommended Training block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        "Input for Recommended Training block is not displayed!"
    );
    softAssert.assertEquals(
        reactDescriptionTabScreen
            .getInputFieldPlaceholderByLabelName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT),
        "Placeholder for  Recommended Training block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isSecondaryInputLabelDisplayedByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        "Secondary label for Recommended Training block is not displayed!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isSecondaryInputFieldPresentByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        "Secondary input for Recommended Training block is not displayed!");
    softAssert.assertEquals(
        reactDescriptionTabScreen
            .getSecondaryValueByName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)),
        recommendedTraining,
        "Recommended training is not correct!"
    );
    softAssert.assertTrue(
        reactDescriptionTabScreen
            .isIconPresentAfterRecommendedTraining(recommendedTraining),
        "Icon is not present after recommended training!"
    );
    softAssert.assertAll();
  }
}
