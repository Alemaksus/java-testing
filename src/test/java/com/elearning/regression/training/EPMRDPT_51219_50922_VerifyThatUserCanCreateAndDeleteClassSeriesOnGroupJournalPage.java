package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckCreatingClassSeries;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_NOTIFICATION_CLASS_SERIES_DELETED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_WARNING_DELETE_CLASS_SERIES;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_51219_50922_VerifyThatUserCanCreateAndDeleteClassSeriesOnGroupJournalPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_51219_50922_VerifyThatUserCanCreateAndDeleteClassSeriesOnGroupJournalPage {

  private TrainingClass expectedTrainingClass;
  private ReactLoginService reactLoginService;
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactLoginService = new ReactLoginService();
    reactGroupJournalService = new ReactGroupJournalService();
    expectedTrainingClass = forCheckCreatingClassSeries();
  }

  @Test(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public void checkThatUserCanCreateClassSeries(User user) {
    SoftAssert softAssert = new SoftAssert();
    reactLoginService.loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(expectedTrainingClass.getGroup());
    deleteSeriesClassesIfNeeded();
    reactGroupJournalScreen.clickAddClassButton();
    reactGroupJournalService
        .fillFieldsAddSeriesClassForEndAfter(expectedTrainingClass);
    reactGroupJournalScreen.clickAllPeriodButton();
    for (int dateIndex = 0; dateIndex < expectedTrainingClass.getRepeatedDaysCounter();
        dateIndex++) {
      softAssert.assertTrue(
          reactGroupJournalScreen
              .isClassInTableByDateDisplayed(
                  expectedTrainingClass.getStartDate().plusDays(dateIndex)),
          String.format("The class with date %s isn't created on Group Journal.",
              expectedTrainingClass.getStartDate().plusDays(dateIndex)));
    }

    reactGroupJournalScreen.clickInTableClassCardByDate(expectedTrainingClass.getStartDate())
        .clickDeleteIcon()
        .clickTheEntireSeries()
        .clickConfirmDeleteButton();
    softAssert.assertEquals(reactGroupJournalScreen.getDeleteSeriesWarningMessageText(),
        String.format(
            LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_WARNING_DELETE_CLASS_SERIES),
            expectedTrainingClass.getName()), "Warning has incorrect notification message.");
    reactGroupJournalScreen.clickConfirmDeleteButton();
    softAssert.assertEquals(reactGroupJournalScreen.getDescriptionNotificationText(),
        LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_NOTIFICATION_CLASS_SERIES_DELETED),
        "Notification has incorrect text.");
    reactGroupJournalScreen.clickCloseNotificationButton();
    for (int dateIndex = 0; dateIndex < expectedTrainingClass.getRepeatedDaysCounter();
        dateIndex++) {
      softAssert.assertFalse(
          reactGroupJournalScreen
              .isClassInTableByDateDisplayed(
                  expectedTrainingClass.getStartDate().plusDays(dateIndex)),
          String.format("The class with date %s isn't deleted from Group Journal.",
              expectedTrainingClass.getStartDate().plusDays(dateIndex)));
    }
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void signOutUser() {
    reactLoginService.signOut().waitScreenLoaded();
  }

  private void deleteSeriesClassesIfNeeded() {
    if (reactGroupJournalScreen.isClassInTableByDateDisplayed(
        expectedTrainingClass.getStartDate())) {
      reactGroupJournalService
          .deleteClassAndSeriesClassesInTableByDate(expectedTrainingClass.getStartDate())
          .waitTableHeaderForVisibility();
    }
  }
}
