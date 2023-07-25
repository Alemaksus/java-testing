package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_GRADUATE_REPORT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_GROUP_JOURNAL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50962_50968_50969_VerifyThatUserCanUseSwitcherBetweenGroupsOnTabs",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50962_50968_50969_VerifyThatUserCanUseSwitcherBetweenGroupsOnTabs {

  private final String groupName = "AutoTest_GroupForCreatingSeriesClasses";
  private final int firstStudentIndex = 0;
  private String tabName;
  private ReactGroupJournalScreen reactGroupJournalScreen;
  private ReactGraduateReportScreen reactGraduateReportScreen;

  @Factory(dataProvider = "Provider of tab names")
  public EPMRDPT_50962_50968_50969_VerifyThatUserCanUseSwitcherBetweenGroupsOnTabs(String tabName) {
    this.tabName = tabName;
  }

  @DataProvider(name = "Provider of tab names")
  public static Object[][] provideUsers() {
    return new Object[][]{
        {getValueOf(REACT_GROUP_JOURNAL_MENU_GROUP_JOURNAL_BUTTON)},
        {getValueOf(REACT_GROUP_JOURNAL_MENU_TASK_JOURNAL_BUTTON)},
        {getValueOf(REACT_GROUP_JOURNAL_MENU_GRADUATE_REPORT_BUTTON)}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    reactGroupJournalScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName)
        .clickTabOfNavigationMenuButtonsByName(tabName);
    reactGraduateReportScreen = new ReactGraduateReportScreen();
  }

  @Test(priority = 1)
  public void checkSwitcherBetweenGroups() {
    assertTrue(reactGroupJournalScreen.isArrowsOfSwitcherBetweenGroupsListDisplayed(),
        "The switcher between groups isn't displayed!");
  }

  @Test(priority = 2)
  public void checkInformationForGroups() {
    SoftAssert softAssert = new SoftAssert();
    String studentFromFirstGroup = getStudentNameDependingOnTabName(tabName);
    String nameFromFirstGroup = reactGroupJournalScreen.getGroupNameFromSwitcherText();
    reactGroupJournalScreen.clickOnEnabledArrowSwitcherBetweenGroups();
    softAssert.assertNotEquals(nameFromFirstGroup,
        reactGroupJournalScreen.getGroupNameFromSwitcherText(),
        "Next group isn't opened!");
    softAssert.assertNotEquals(studentFromFirstGroup,
        getStudentNameDependingOnTabName(tabName),
        "Information for the next group isn't opened!");
    reactGroupJournalScreen.clickOnEnabledArrowSwitcherBetweenGroups();
    softAssert.assertEquals(nameFromFirstGroup, reactGroupJournalScreen
            .getGroupNameFromSwitcherText(),
        "Previous group isn't opened!");
    softAssert.assertEquals(studentFromFirstGroup,
        getStudentNameDependingOnTabName(tabName),
        "Information for the previous group isn't opened!");
    softAssert.assertAll();
  }

  private String getStudentNameDependingOnTabName(String tabName) {
    return tabName.equals(getValueOf(REACT_GROUP_JOURNAL_MENU_GRADUATE_REPORT_BUTTON))
        ? reactGraduateReportScreen.getStudentsTextByIndex(firstStudentIndex)
        : reactGroupJournalScreen.getStudentsTextByIndex(firstStudentIndex);
  }
}
