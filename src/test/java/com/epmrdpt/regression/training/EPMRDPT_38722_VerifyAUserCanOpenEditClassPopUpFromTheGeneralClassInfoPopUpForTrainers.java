package com.epmrdpt.regression.training;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.LessonDetails;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.LessonDetailPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLessonInfoPopUpService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38722_VerifyAUserCanOpenEditClassPopUpFromTheGeneralClassInfoPopUpForTrainers",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38722_VerifyAUserCanOpenEditClassPopUpFromTheGeneralClassInfoPopUpForTrainers {

  private final String classLocation = "Location" + LocalTime.now();
  private final String classDescription = "Class description " + LocalTime.now();
  private final String classTopic = "AutoTest_EditClass";
  private final String classChangedTopic = "AutoTest_EditClass" + LocalTime.now();
  private ReactTrainingService reactTrainingService;
  private ReactLessonInfoPopUpService reactLessonInfoPopUpService;
  private ReactEditClassPopUpScreen reactEditClassPopUpScreen;
  private LessonDetails lessonDetailsFromEditPopUpWithChanges;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_38722_VerifyAUserCanOpenEditClassPopUpFromTheGeneralClassInfoPopUpForTrainers(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactTrainingScreenIsLoading() {
    reactEditClassPopUpScreen = new ReactEditClassPopUpScreen();
    reactTrainingService = new ReactTrainingService();
    reactLessonInfoPopUpService = new ReactLessonInfoPopUpService();
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test(priority = 1)
  public void checkThatTheEditClassPopUpCorrespondingToTheDesiredClassOpensTab() {
    LessonDetails lessonDetailsFromLessonInfoPopUp = reactTrainingService.getLessonDetailsFromLessonInfoPopUp(
        classTopic);
    LessonDetails lessonDetailsFromEditPopUp = reactLessonInfoPopUpService.getLessonDetailsFromEditPopUpText();
    assertEquals(lessonDetailsFromLessonInfoPopUp, lessonDetailsFromEditPopUp,
        "The Edit pop-up doesn't correspond to the Lesson info pop-up opens in My schedule tab!");
  }

  @Test(priority = 2)
  public void checkThatAllChangesAreSavedAndDisplayedOnTheClassInfoPopUp() {
    reactLessonInfoPopUpService.chooseRandomClassTypeFromEditPopUpText();
    reactEditClassPopUpScreen
        .typeClassTopic(classChangedTopic)
        .typeClassLocation(classLocation)
        .typeClassDescription(classDescription)
        .waitTasksButtonChangedColor()
        .clickSaveChangesButton()
        .clickCloseNotificationButton();
    LessonDetails lessonDetailsFromLessonInfoPopUpWithChanges = reactTrainingService.getLessonDetailsFromLessonInfoPopUp(
        classChangedTopic);
    lessonDetailsFromEditPopUpWithChanges = reactLessonInfoPopUpService.getLessonDetailsFromEditPopUpText();
    new ReactEditClassPopUpScreen().clickCloseEditClassPopUp();
    assertEquals(lessonDetailsFromLessonInfoPopUpWithChanges, lessonDetailsFromEditPopUpWithChanges,
        "The Edit pop-up with changes doesn't correspond to the Lesson info pop-up with changes opens in My schedule tab!");
    new ReactHeaderScreen().clickUserInfoToggle().signOut().waitScreenLoaded();
  }

  @Test(priority = 3)
  public void checkThatAllChangesAreSavedAndDisplayedOnTheLearningTabClassDetailsPopUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asStudentWithOnlineTasks())
        .clickLearningButton()
        .clickTopicByName(classChangedTopic);
    LessonDetails lessonDetailsFromLearningTabClassDetailsPopUpWithChanges =
        new LessonDetailPopUpScreenOnLearningPageScreen().getLessonDetailsFromPopUp();
    new LessonDetailPopUpScreenOnLearningPageScreen().clickCrossButtonOfPopUp();
    assertEquals(lessonDetailsFromEditPopUpWithChanges,
        lessonDetailsFromLearningTabClassDetailsPopUpWithChanges,
        "The Edit pop-up with changes doesn't correspond to the Tab class details pop-up opens on the learning tab!");
    new HeaderScreen().clickProfileMenu().signOut().waitScreenLoaded();
    returnChanges();
  }

  private void returnChanges() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility()
        .clickInScheduleClassCardByTopic(classChangedTopic)
        .waitLessonInfoForVisibility()
        .clickEditIcon()
        .waitClassTopicFromEditNotEmpty()
        .typeClassTopic(classTopic)
        .waitTasksButtonChangedColor()
        .clickSaveChangesButton()
        .clickCloseNotificationButton();
  }
}
