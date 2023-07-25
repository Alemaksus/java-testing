package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckMarkAndCommentsInTheGroupJournal;
import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;
import static java.lang.String.format;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_44769_VerifyThatUserCanRateStudentKnowledgeAndSaveCommentsUsingMarkPopUpInTheGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44769_VerifyThatUserCanRateStudentKnowledgeAndSaveCommentsUsingMarkPopUpInTheGroupJournal {

  private static final int MARK_ONE = 1;
  private static final int MARK_TEN = 10;
  private final String studentName = "student LastName204134";
  private final String studentCommentPattern = "Test comment for student by %s";
  private final String trainerCommentPattern = "Test comment for trainer by %s";

  private TrainingClass trainingClass;
  private ReactLoginService reactLoginService;
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactLoginService = new ReactLoginService();
    reactGroupJournalService = new ReactGroupJournalService();
    trainingClass = forCheckMarkAndCommentsInTheGroupJournal();
  }

  @Test(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public void checkThatMarkAndCommentsAreDisplayedInTheGroupJournal(User user) {
    ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen();
    reactLoginService.loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(trainingClass.getGroup());
    deleteClassIfNeeded();
    reactGroupJournalScreen.clickAddClassButton();
    reactGroupJournalService
        .fillAllRequiredFieldsToAddClassInGroupJournal(trainingClass);
    int studentMarkValue = getRandomNumberInInterval(MARK_ONE, MARK_TEN);
    String commentForStudent = format(studentCommentPattern, user.getFirstName());
    String commentForTrainer = format(trainerCommentPattern, user.getFirstName());
    reactGroupJournalScreen.clickCellMarkByName(studentName)
        .waitMarkPopUpForVisibility()
        .clickMarkButtonByValue(studentMarkValue)
        .typeCommentForStudent(commentForStudent)
        .typeCommentForTrainer(commentForTrainer);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactJournalMarkPopUpScreen.getBackgroundColorMarkButtonByValue(studentMarkValue),
        ReactJournalMarkPopUpScreen.COLOR_CHOSEN_BUTTON_ACTIVE,
        "The chosen mark does not become blue!");
    softAssert.assertEquals(
        reactJournalMarkPopUpScreen.getCommentForStudentTextareaText(),
        commentForStudent,
        "The comment for student is not displayed in the Class mark pop-up!");
    softAssert.assertEquals(
        reactJournalMarkPopUpScreen.getCommentForTrainerTextareaText(),
        commentForTrainer,
        "The comment for trainer is not displayed in the Class mark pop-up!");
    softAssert.assertEquals(Integer.parseInt(
            reactJournalMarkPopUpScreen
                .clickSaveButton()
                .clickCloseNotificationButton()
                .getCellMarkByNameText(studentName)), studentMarkValue,
        "Expected mark is not displayed in group journal!");
    softAssert.assertTrue(
        reactGroupJournalScreen.isCommentIconByStudentNameDisplayed(studentName),
        "Comment icon is not displayed in group journal!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteCreatedClassAndSignOutUser() {
    reactGroupJournalService.deleteClassInTableByDate(trainingClass.getStartDate());
    reactLoginService.signOut();
  }

  private void deleteClassIfNeeded() {
    if (reactGroupJournalScreen.isClassInTableByDateDisplayed(trainingClass.getStartDate())) {
      reactGroupJournalService.deleteClassInTableByDate(trainingClass.getStartDate());
    }
  }
}
