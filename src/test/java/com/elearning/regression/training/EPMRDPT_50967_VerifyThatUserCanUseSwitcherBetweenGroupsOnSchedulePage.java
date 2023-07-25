package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactScheduleTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50967_VerifyThatUserCanUseSwitcherBetweenGroupsOnSchedulePage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50967_VerifyThatUserCanUseSwitcherBetweenGroupsOnSchedulePage {

  private final String groupName = "AutoTest_GroupForCreatingSeriesClasses";
  private ReactScheduleTabScreen reactScheduleTabScreen;
  private int countOfGroups;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    reactScheduleTabScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName)
        .clickScheduleTab();
  }

  @Test(priority = 1)
  public void checkSwitcherBetweenGroups() {
    assertTrue(reactScheduleTabScreen.isSwitchBetweenGroupsArrowsDisplayed(),
        "The switcher between groups isn't displayed!");
  }

  @Test(priority = 2)
  public void checkInformationForTheNextGroups() {
    countOfGroups = reactScheduleTabScreen.getCountOfGroupsFromSwitcher();
    SoftAssert softAssert = new SoftAssert();
    for (int groupNumber = 1; groupNumber < countOfGroups; groupNumber++) {
      String nameFromCurrentGroup = reactScheduleTabScreen.getGroupNameFromSwitcherText();
      reactScheduleTabScreen.clickOnEnabledArrowSwitcherBetweenGroups();
      softAssert.assertNotEquals(nameFromCurrentGroup,
          reactScheduleTabScreen.getGroupNameFromSwitcherText(),
          format("The next group %s isn't opened!", groupNumber + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkInformationForThePreviousGroups() {
    SoftAssert softAssert = new SoftAssert();
    for (int groupNumber = countOfGroups; groupNumber > 1; groupNumber--) {
      String nameFromCurrentGroup = reactScheduleTabScreen.getGroupNameFromSwitcherText();
      reactScheduleTabScreen.clickOnEnabledArrowSwitcherBetweenGroups();
      softAssert.assertNotEquals(nameFromCurrentGroup,
          reactScheduleTabScreen.getGroupNameFromSwitcherText(),
          format("The previous group %s isn't closed!", groupNumber));
    }
    softAssert.assertAll();
  }
}
