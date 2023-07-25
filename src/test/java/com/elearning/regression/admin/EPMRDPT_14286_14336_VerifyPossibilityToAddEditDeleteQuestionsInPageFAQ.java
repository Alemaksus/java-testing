package com.epmrdpt.regression.admin;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.AddQuestionFAQScreen;
import com.epmrdpt.screens.EditQuestionFAQScreen;
import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14286_14336_VerifyPossibilityToAddEditDeleteQuestionsInPageFAQ",
    groups = {"full", "regression", "admin"})
public class EPMRDPT_14286_14336_VerifyPossibilityToAddEditDeleteQuestionsInPageFAQ {

  private FAQScreen faqScreen;
  private SoftAssert softAssert;
  private static final String QUESTION_IN_FAQ = "AutoTest_Question to be created";
  private static final String ANSWER_IN_FAQ = "AutoTest_Answer to be created";
  private static final String EDIT_ANSWER_IN_FAQ = "AutoTest_Answer to be edited";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    faqScreen = new FAQScreen();
  }

  @Test(priority = 1)
  public void checkHomePageIsOpenedAfterLogin() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asAdmin());
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkFAQScreenLoaded() {
    assertTrue(new HeaderScreen().clickFAQNavigationLink().isScreenLoaded(),
        "FAQ page isn't opened!");
  }

  @Test(priority = 3)
  public void checkIfAdminCanAddFAQ() {
    softAssert = new SoftAssert();
    AddQuestionFAQScreen addQuestionFAQScreen = new AddQuestionFAQScreen();
    faqScreen
        .closeTimeZonePopUpIfDisplayed()
        .clickAddQuestionButton();
    softAssert.assertTrue(addQuestionFAQScreen.isScreenLoaded(),
        "Add question FAQ page is not loaded!");
    addQuestionFAQScreen
        .clickLanguageInputDropDown()
        .clickUkrainianLanguageDropDownItem()
        .clickLanguageInputDropDown()
        .clickRussianLanguageDropDownItem()
        .enterQuestionInEnglishInput(QUESTION_IN_FAQ)
        .enterAnswerInEnglishInput(ANSWER_IN_FAQ)
        .enterQuestionInRussiaInput(QUESTION_IN_FAQ)
        .enterAnswerInRussiaInput(ANSWER_IN_FAQ)
        .enterQuestionInUkraineInput(QUESTION_IN_FAQ)
        .enterAnswerInUkraineInput(ANSWER_IN_FAQ)
        .clickSaveButton();
    softAssert.assertTrue(faqScreen.isQuestionByNameDisplayed(QUESTION_IN_FAQ),
        format("Question '%s' is not created!", QUESTION_IN_FAQ));
    softAssert.assertEquals(faqScreen
            .clickAnswerExpanderByQuestion(QUESTION_IN_FAQ)
            .getAnswerInputByQuestionName(QUESTION_IN_FAQ), ANSWER_IN_FAQ,
        format("Question '%s' with answer '%s' is not created!", QUESTION_IN_FAQ, ANSWER_IN_FAQ));
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkIfAdminCanEditFAQ() {
    softAssert = new SoftAssert();
    EditQuestionFAQScreen editQuestionFAQScreen = new EditQuestionFAQScreen();
    faqScreen.clickEditButtonByQuestion(QUESTION_IN_FAQ);
    softAssert.assertTrue(editQuestionFAQScreen.isScreenLoaded(),
        "Edit question FAQ page is not loaded!");
    editQuestionFAQScreen
        .enterAnswerInEnglishInput(EDIT_ANSWER_IN_FAQ)
        .enterAnswerInRussiaInput(EDIT_ANSWER_IN_FAQ)
        .enterAnswerInUkraineInput(EDIT_ANSWER_IN_FAQ)
        .clickSaveButton();
    faqScreen.refreshPageUntilElementByTextAppear(EDIT_ANSWER_IN_FAQ);
    softAssert.assertEquals(faqScreen
            .clickAnswerExpanderByQuestion(QUESTION_IN_FAQ)
            .getAnswerInputByQuestionName(QUESTION_IN_FAQ), EDIT_ANSWER_IN_FAQ,
        format("Question '%s' with answer '%s' is not edited properly!", QUESTION_IN_FAQ,
            EDIT_ANSWER_IN_FAQ));
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkIfAdminCanDeleteFAQ() {
    softAssert = new SoftAssert();
    faqScreen.clickDeleteButtonByQuestion(QUESTION_IN_FAQ);
    softAssert.assertTrue(faqScreen.isDeletePopUpDisplayed(),
        "Delete pop up did not appear after clicking on delete button");
    faqScreen.clickDeleteButtonInPopUp();
    faqScreen.refreshPageUntilElementByTextDisappear(QUESTION_IN_FAQ);
    softAssert.assertFalse(faqScreen.isQuestionByNameDisplayed(QUESTION_IN_FAQ),
        format("Question '%s'is not deleted!", QUESTION_IN_FAQ));
    softAssert.assertAll();
  }
}
