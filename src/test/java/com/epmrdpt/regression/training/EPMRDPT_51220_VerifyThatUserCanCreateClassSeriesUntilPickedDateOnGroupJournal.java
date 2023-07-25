package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckCreatingClassSeriesWithEndBy;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import java.time.LocalTime;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_51220_VerifyThatUserCanCreateClassSeriesUntilPickedDateOnGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_51220_VerifyThatUserCanCreateClassSeriesUntilPickedDateOnGroupJournal {

  private static final LocalTime START_CLASS_TIME = LocalTime.of(14, 0);
  private static final LocalTime END_CLASS_TIME = LocalTime.of(14, 30);

  private TrainingClass expectedTrainingClass;
  private ReactLoginService reactLoginService;
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactLoginService = new ReactLoginService();
    reactGroupJournalService = new ReactGroupJournalService();
    expectedTrainingClass = forCheckCreatingClassSeriesWithEndBy();
  }

  @Test(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public void checkThatUserCanCreateClassSeriesUntilThePickedDate(User user) {
    SoftAssert softAssert = new SoftAssert();
    reactLoginService.loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(expectedTrainingClass.getGroup());
    deleteSeriesClassesIfNeeded();
    reactGroupJournalScreen.clickAddClassButton();
    reactGroupJournalService.fillFieldsAddSeriesClassForEndBy(expectedTrainingClass,
        START_CLASS_TIME,
        END_CLASS_TIME);
    LocalDate currentDate = expectedTrainingClass.getStartDate();
    int countOfDay = 0;
    int dateIndex = currentDate.getDayOfMonth();
    for (; dateIndex <= expectedTrainingClass.getEndDate().getDayOfMonth(); dateIndex++) {
      currentDate = expectedTrainingClass.getStartDate().plusDays(countOfDay);
      softAssert.assertTrue(
          reactGroupJournalScreen.isClassInTableByDateDisplayed(currentDate),
          String
              .format("The class with date %s and before \"End by\" isn't created on Group Journal",
                  currentDate));
      countOfDay++;
    }
    int dateIndexEnd = expectedTrainingClass.getStartDate()
        .plusDays(expectedTrainingClass.getRepeatedDaysCounter() - 1).getDayOfMonth();
    for (; dateIndex <= dateIndexEnd; dateIndex++) {
      currentDate = expectedTrainingClass.getStartDate().plusDays(countOfDay);
      softAssert.assertFalse(
          reactGroupJournalScreen.isClassInTableByDateDisplayed(currentDate),
          String.format("The class with date %s and after \"End by\" is created on Group Journal",
              expectedTrainingClass.getEndDate()));
      countOfDay++;
    }
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteCreatedClassSeriesAndSignOutUser() {
    reactGroupJournalService.deleteClassAndSeriesClassesInTableByDate(
        expectedTrainingClass.getStartDate()).waitTableHeaderForVisibility();
    reactLoginService.signOut().waitScreenLoaded();
  }

  private void deleteSeriesClassesIfNeeded() {
    if (reactGroupJournalScreen
        .isClassInTableByDateDisplayed(expectedTrainingClass.getStartDate())) {
      reactGroupJournalService
          .deleteClassAndSeriesClassesInTableByDate(expectedTrainingClass.getStartDate());
    }
  }
}
