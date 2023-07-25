package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_TRAINING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_SCHEDULE_TAB;
import static com.epmrdpt.utils.StringUtils.getCurrentMonthValueByLanguageCode;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.screens.SelectLanguageScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44756_VerifyThatItIsPossibleToChangeLanguageOnTheTrainingPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44756_VerifyThatItIsPossibleToChangeLanguageOnTheTrainingPage {

  private String currentLanguageCode;
  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    currentLanguageCode = new SelectLanguageScreen()
        .getCurrentLanguage();
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
  }

  @Test()
  public void checkTrainingTab() {
    assertEquals(new ReactHeaderScreen()
            .waitTrainingTabForVisibility()
            .getTrainingTabText(),
        getValueOf(HEADER_CONTAINER_TRAINING),
        "'Training' tab isn't displayed correctly in appropriate language!");
  }

  @Test()
  public void checkMyScheduleTab() {
    assertEquals(reactTrainingScreen
            .getMyScheduleTabText(),
        getValueOf(REACT_TRAINING_MY_SCHEDULE_TAB),
        "'My schedule' tab isn't displayed correctly in appropriate language!");
  }

  @Test()
  public void checkMyGroupsTab() {
    assertEquals(reactTrainingScreen
            .getMyGroupsTabText(),
        getValueOf(REACT_TRAINING_MY_GROUPS_TAB),
        "'My groups' tab isn't displayed correctly in appropriate language!");
  }

  @Test()
  public void checkCalendar() {
    assertTrue(reactTrainingScreen
            .getMonthAndYearInCalendarText()
            .contains(getCurrentMonthValueByLanguageCode(currentLanguageCode)),
        "Month in calendar isn't displayed correctly in appropriate language!");
  }
}
