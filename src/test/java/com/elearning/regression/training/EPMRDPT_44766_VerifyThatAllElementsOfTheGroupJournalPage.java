package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_HEADER_ABSENCE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_HEADER_AVERAGE_MARK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_HEADER_FIRST_COLUMN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_GRADUATE_REPORT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_GROUP_JOURNAL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_SCHEDULE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_44766_VerifyThatAllElementsOfTheGroupJournalPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44766_VerifyThatAllElementsOfTheGroupJournalPage {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private ReactGroupJournalScreen reactGroupJournalScreen;
  private User user;

  private final List<String> navigationMenuButtonsList = new ArrayList<>(Arrays.asList(
      LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_MENU_GROUP_JOURNAL_BUTTON),
      LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON),
      LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_MENU_SCHEDULE_BUTTON),
      LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_MENU_GRADUATE_REPORT_BUTTON)));

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44766_VerifyThatAllElementsOfTheGroupJournalPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signInAndWaitTrainingScreenToLoad() {
    ReactTrainingScreen reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
    new ReactTrainingService().navigateToMyGroupsTabAndSearchGroup(groupName);
    reactGroupJournalScreen = reactTrainingScreen.clickGroupJournalButton()
        .waitTableHeaderForVisibility();
  }

  @Test(priority = 1)
  public void checkBreadCrumbsDisplayed() {
    assertTrue(reactGroupJournalScreen.isBreadCrumbsDisplayed(),
        "'Bread Crumbs' in the Group Journal page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkTrainersIconsDisplayed() {
    assertTrue(reactGroupJournalScreen.isTrainersIconsDisplayed(),
        "'Trainers Icons' in the Group Journal page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkNavigationMenuButtonsHasCorrectText() {
    assertEquals(reactGroupJournalScreen.getListOfNavigationMenuButtonsText(),
        navigationMenuButtonsList,
        "'Navigation Menu Buttons' in the Group Journal page has incorrect text!");
  }

  @Test(priority = 1)
  public void checkAddClassButtonHasCorrectText() {
    assertEquals(reactGroupJournalScreen.getAddClassButtonText(),
        LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON),
        "'Add Class Button' in the Group Journal page has incorrect text!");
  }

  @Test(priority = 1)
  public void checkGroupTableGridDisplayed() {
    assertTrue(reactGroupJournalScreen.isGroupTableGridDisplayed(),
        "'Group Table' grid in the Group Journal page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkHeaderOfTheFirstColumnHasCorrectText() {
    assertEquals(reactGroupJournalScreen.getHeaderOfTheFirstColumnText(),
        LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_HEADER_FIRST_COLUMN),
        "Header Of The First Column on the Group Journal page has incorrect text!");
  }

  @Test(priority = 1)
  public void checkStudentsListDisplayed() {
    assertTrue(reactGroupJournalScreen.isStudentsListDisplayed(),
        "'Students List' in Group Table on Group Journal page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkHeaderAverageMarkHasCorrectText() {
    assertEquals(reactGroupJournalScreen.getHeaderAverageMarkText(),
        LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_HEADER_AVERAGE_MARK),
        "'Header Average Mark' in Group Table on Group Journal page has incorrect text!");
  }

  @Test(priority = 1)
  public void checkHeaderAbsenceHasCorrectText() {
    assertEquals(reactGroupJournalScreen.getHeaderAbsenceText(),
        LocaleProperties.getValueOf(REACT_GROUP_JOURNAL_HEADER_ABSENCE),
        "'Header Absence' in Group Table on Group Journal page has incorrect text!");
  }

  @Test(priority = 2)
  public void checkDatesAndClassTypesHeadersDisplayed() {
    reactGroupJournalScreen.moveScrollBarToBeginning();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupJournalScreen.isHeaderDatesDisplayed(),
        "'Dates Headers' in Group Table on Group Journal page isn't displayed!");
    softAssert.assertTrue(reactGroupJournalScreen.isHeaderClassTypeDisplayed(),
        "'Class Type Headers' in Group Table on Group Journal page isn't displayed!");
    softAssert.assertAll();
  }
}
