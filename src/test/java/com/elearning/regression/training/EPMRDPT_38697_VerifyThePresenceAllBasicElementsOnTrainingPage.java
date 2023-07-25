package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_SCHEDULE_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_SCHEDULE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASKS_BUTTON;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactFooterScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_38697_VerifyThePresenceAllBasicElementsOnTrainingPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38697_VerifyThePresenceAllBasicElementsOnTrainingPage {

  private SoftAssert softAssert;
  private ReactTrainingScreen reactTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_38697_VerifyThePresenceAllBasicElementsOnTrainingPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingScreen() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility();
  }

  @Test
  public void checkThatHeaderPresentOnTrainingPage() {
    assertTrue(new ReactHeaderScreen().isEpamLogoDisplayed(),
        "Header doesn't display on training page!");
  }

  @Test
  public void checkThatCalendarPresentOnTrainingPage() {
    assertTrue(reactTrainingScreen.isCalendarDisplayed(),
        "Calendar doesn't display on training page!");
  }

  @Test
  public void checkMyScheduleTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingScreen.isMyScheduleTabDisplayed(),
        "My Schedule Tab doesn't display on training page!");
    softAssert.assertEquals(reactTrainingScreen.getMyScheduleTabText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MY_SCHEDULE_TAB),
        "My Schedule Tab on the Training page has incorrect text!");
    softAssert.assertAll();

  }

  @Test
  public void checkMyGroupsTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingScreen.isMyGroupsTabDisplayed(),
        "My Groups Tab doesn't display on training page!");
    softAssert.assertEquals(reactTrainingScreen.getMyGroupsTabText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB),
        "My Groups Tab on the Training page has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkScheduleButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingScreen.isScheduleButtonDisplayed(),
        "Schedule Button doesn't display on training page!");
    softAssert.assertEquals(reactTrainingScreen.getScheduleButtonText(),
        LocaleProperties.getValueOf(REACT_TRAINING_SCHEDULE_BUTTON),
        "Schedule Button on the Training page has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkTasksButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingScreen.isTasksButtonDisplayed(),
        "Tasks Button doesn't display on training page!");
    softAssert.assertEquals(reactTrainingScreen.getTasksButtonText(),
        LocaleProperties.getValueOf(REACT_TRAINING_TASKS_BUTTON),
        "Tasks Button on the Training page has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkThatScheduleComponentPresentOnTrainingPage() {
    assertTrue(reactTrainingScreen.isScheduleForDayDisplayed(),
        "Schedule Component doesn't display on training page!");
  }

  @Test
  public void checkThatFooterPresentOnTrainingPage() {
    assertTrue(new ReactFooterScreen().isEpamLogoDisplayed(),
        "Footer doesn't display on training page!");
  }
}
