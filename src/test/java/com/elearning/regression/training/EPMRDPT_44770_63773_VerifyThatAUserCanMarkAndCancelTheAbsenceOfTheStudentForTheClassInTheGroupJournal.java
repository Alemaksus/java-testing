package com.epmrdpt.regression.training;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactJournalMarkPopUpScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_44770_63773_VerifyThatAUserCanMarkAndCancelTheAbsenceOfTheStudentForTheClassInTheGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44770_63773_VerifyThatAUserCanMarkAndCancelTheAbsenceOfTheStudentForTheClassInTheGroupJournal {

  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44770_63773_VerifyThatAUserCanMarkAndCancelTheAbsenceOfTheStudentForTheClassInTheGroupJournal(
      User user) {
    this.user = user;
  }

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String studentName = "Student AutoTest";
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;
  private ReactJournalMarkPopUpScreen reactJournalMarkPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactGroupJournalService = new ReactGroupJournalService();
    reactJournalMarkPopUpScreen = new ReactJournalMarkPopUpScreen();
    new ReactLoginService().loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService().openGroupJournalByName(groupName);
    reactGroupJournalService.unmarkAbsenceOfStudentByName(studentName);
    clickJournalCellByNameAndClickAbsenseButtonOnMarkPopUp();
  }

  @Test(priority = 1)
  public void checkThatAbsentButtonBecomeBlueAfterMarkingAbsence() {
    Assert.assertEquals(reactJournalMarkPopUpScreen.getBackgroundColorAbsentButton(),
        ReactJournalMarkPopUpScreen.COLOR_CHOSEN_BUTTON_ACTIVE,
        "The Absent button doesn't become blue!");
  }

  @Test(priority = 2)
  public void checkThatTargetCellHasRedAbsenceMark() {
    reactJournalMarkPopUpScreen.clickSaveButton();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactGroupJournalScreen.isSymbolOfAbsenceInCellByNameDisplayed(studentName),
        "The RED symbol 'X' doesn't appear in the cell in the Group Journal table!");
    softAssert.assertEquals(
        reactGroupJournalScreen.getColorOfSymbolOfAbsenceInCellByName(studentName),
        ReactGroupJournalScreen.COLOR_SYMBOL_OF_ABSENCE,
        "The RED symbol 'X' doesn't have appropriate color!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatAbsentButtonBecomeWhiteAfterUnmarkingAbsence() {
    clickJournalCellByNameAndClickAbsenseButtonOnMarkPopUp();
    Assert.assertEquals(reactJournalMarkPopUpScreen.getBackgroundColorAbsentButton(),
        ReactJournalMarkPopUpScreen.COLOR_CHOSEN_BUTTON_INACTIVE,
        "The Absent button doesn't become white!");
  }

  @Test(priority = 4)
  public void checkThatTargetCellDoesNotHaveAbsenceMark() {
    reactJournalMarkPopUpScreen.clickSaveButton();
    Assert.assertTrue(
        reactGroupJournalScreen.isNotSymbolOfAbsenceInCellByNameDisplayed(studentName));
  }

  private void clickJournalCellByNameAndClickAbsenseButtonOnMarkPopUp() {
    reactGroupJournalScreen.clickCellMarkByName(studentName)
        .waitMarkPopUpForVisibility()
        .clickAbsentButton();
  }
}
