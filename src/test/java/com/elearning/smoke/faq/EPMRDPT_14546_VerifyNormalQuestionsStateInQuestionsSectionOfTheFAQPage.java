package com.epmrdpt.smoke.faq;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14546_VerifyNormalQuestionsStateInQuestionsSectionOfTheFAQPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14546_VerifyNormalQuestionsStateInQuestionsSectionOfTheFAQPage {

  private FAQScreen faqScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    faqScreen = new HeaderScreen().waitLanguageControlDropdownDisplayed()
        .clickFAQNavigationLink();
  }

  @Test(priority = 1)
  public void checkFAQScreenLoading() {
    assertTrue(faqScreen.isScreenLoaded(), "FAQ screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkThatAllQuestionsHaveTitle() {
    softAssert = new SoftAssert();
    for (int questionIndex = 0; questionIndex < faqScreen.getQuestionsQuantity(); questionIndex++) {
      softAssert.assertTrue(faqScreen.isQuestionsHaveTitle(questionIndex),
          format("Question - %d didn't have title!", questionIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatAllQuestionsHavePlusButton() {
    softAssert = new SoftAssert();
    for (int questionIndex = 0; questionIndex < faqScreen.getQuestionsQuantity(); questionIndex++) {
      softAssert.assertTrue(faqScreen.isQuestionHasPlusButton(questionIndex),
          format("Question - %d didn't have 'plus' button", questionIndex + 1));
    }
    softAssert.assertAll();
  }
}
