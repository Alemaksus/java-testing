package com.epmrdpt.smoke.faq;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13231_VerifyTheQuestionsSectionOfTheFAQPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13231_VerifyTheQuestionsSectionOfTheFAQPage {

  private FAQScreen faqScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    faqScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickFAQNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatFaqPageLoaded() {
    assertTrue(faqScreen.isScreenLoaded(), "FAQ page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkFaqPageContainsQuestions() {
    assertTrue(faqScreen.isQuestionsContainerDisplayed(),
        "'FAQ' page doesn't contain 'Questions' section!");
  }

  @Test(priority = 3)
  public void checkThatQuestionsSectionChangedAfterClickOnQuestionOrPlus() {
    SoftAssert softAssert = new SoftAssert();
    for (int questionIndex = 0; questionIndex < faqScreen.getQuestionsQuantity(); questionIndex++) {
      softAssert.assertFalse(faqScreen.isAnswerDisplayedByIndex(questionIndex),
          format("Answer - %d displayed without click!", questionIndex + 1));
      softAssert.assertFalse(faqScreen.isQuestionCrossElementRotated(questionIndex),
          format("%d cross doesn't have state '+'", questionIndex + 1));
      faqScreen.expandCollapseQuestionElement(questionIndex);
      softAssert.assertTrue(faqScreen.isAnswerDisplayedByIndex(questionIndex),
          format("Answer - %d isn't displayed after click on question - %d", questionIndex + 1,
              questionIndex + 1));
      softAssert.assertTrue(faqScreen.isQuestionCrossElementRotated(questionIndex),
          format("%d cross doesn't rotate after opened answer", questionIndex + 1));
      faqScreen.expandCollapseQuestionElement(questionIndex);
      softAssert.assertFalse(faqScreen.isAnswerDisplayedByIndex(questionIndex),
          format("Answer - %d isn't hidden after click on question - %d!", questionIndex + 1,
              questionIndex + 1));
      softAssert.assertFalse(faqScreen.isQuestionCrossElementRotated(questionIndex),
          format("%d cross doesn't rotate back after closed answer", questionIndex + 1));
    }
    softAssert.assertAll();
  }
}
