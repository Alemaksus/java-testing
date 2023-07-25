package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26564_VerifyTheContentOfTableGroupsSectionOfHomeTabOfLearningPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26564_VerifyTheContentOfTableGroupsSectionOfHomeTabOfLearningPage {

  private HeaderScreen headerScreen;
  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;
  private SoftAssert softAssert;
  private String locationAutoTestLearning1Group = "--";
  private Pattern datePattern = Pattern.compile(
      "(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.((19|2[0-9])[0-9]{2})");
  private String patternDate = "dd.mm.yyyy";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(headerScreen.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatStudentHasAccessLearningScreen() {
    assertTrue(new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asStudentForLearningPage())
        .clickLearningButton().isScreenLoaded(), "Learning screen isn't loaded!");
  }

  @Test(priority = 3)
  public void checkThatLearningScreenHasGroupNameCellWithSortItem() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isGroupNameTitleIsDisplayed(),
        "Group name cell isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isGroupNameHasSortArrowItem(),
        "Group name cell hasn't sort item 'arrow!'");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatLearningScreenHasLocationCellWithSortItem() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLocationTitleIsDisplayed(),
        "Group name cell isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isLocationHasSortArrowItem(),
        "Group name cell hasn't sort item 'arrow!'");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatLearningScreenStartDateCellWithSortItem() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isStartDateTitleIsDisplayed(),
        "Group name cell isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isStartDateHasSortArrowItem(),
        "Group name cell hasn't sort item 'arrow!'");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatLearningScreenHasEndDateCellWithSortItem() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEndDateTitleIsDisplayed(),
        "Group name cell isn't displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEndDateHasSortArrowItem(),
        "Group name cell hasn't sort item 'arrow!'");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatAllGroupsIsDisplayed() {
    assertTrue(homeTabOnLearningPageScreen.isAllGroupDisplayed(), "Not all groups are displayed!");
  }

  @Test(priority = 3)
  public void checkThatAllGroupHasName() {
    softAssert = new SoftAssert();
    for (int groupIndex = 0; groupIndex < homeTabOnLearningPageScreen.getQuantityGroup();
        groupIndex++) {
      softAssert.assertTrue(homeTabOnLearningPageScreen.isGroupHasNameByIndex(groupIndex),
          String.format("Group - %d hasn't name!", groupIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatAllGroupHasSquare() {
    softAssert = new SoftAssert();
    for (int groupIndex = 0; groupIndex < homeTabOnLearningPageScreen.getQuantityGroup();
        groupIndex++) {
      softAssert.assertTrue(homeTabOnLearningPageScreen.isGroupHasSquareByIndex(groupIndex),
          String.format("Group - %d hasn't square!", groupIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkLocationGroupForFirstGroup() {
    assertEquals(homeTabOnLearningPageScreen.getGroupLocationTextByIndex(0).trim(),
        locationAutoTestLearning1Group, "Location's group isn't equals!");
  }

  @Test(priority = 3)
  public void checkStartDateForFirstGroup() {
    assertTrue(
        datePattern.matcher(homeTabOnLearningPageScreen.getGroupStartDateTextByIndex(0).trim())
            .find(),
        "Location's group isn't equals!");
  }

  @Test(priority = 3)
  public void checkEndDateForFirstGroup() {
    assertTrue(datePattern.matcher(homeTabOnLearningPageScreen.getGroupEndDateTextByIndex(0).trim())
            .find(),
        "Location's group isn't equals!");
  }

  @Test(priority = 3)
  public void checkDateFormat() {
    assertTrue(StringUtils
        .isDateMatchExpectedPattern(homeTabOnLearningPageScreen.getGroupStartDateTextByIndex(0),
            patternDate), "Date pattern isn't correct! Expected pattern " + patternDate);
  }
}
