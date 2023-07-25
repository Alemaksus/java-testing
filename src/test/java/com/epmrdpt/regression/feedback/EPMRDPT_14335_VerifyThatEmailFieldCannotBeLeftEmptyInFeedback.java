package com.epmrdpt.regression.feedback;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.FeedbackScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14335_VerifyThatEmailFieldCannotBeLeftEmptyInFeedback",
    groups = {"full", "regression"})
public class EPMRDPT_14335_VerifyThatEmailFieldCannotBeLeftEmptyInFeedback {

  private FeedbackScreen feedbackScreen;
  private SoftAssert softAssert;

  private static final String MESSAGE = "Automation Testing for feedback submission!";
  private static final String RED_EXCLAMATORY_MARK_ICON = "reg-error.svg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    feedbackScreen = new FeedbackScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().isScreenLoaded(), "Home page hasn't loaded!");
  }

  @Test(priority = 2)
  public void checkFeedbackPopupLoading() {
    assertTrue(feedbackScreen.clickFeedbackButton().clickIConfirmCheckbox().isScreenLoaded(),
        "Feedback form hasn't appeared after clicking feedback button!");
  }

  @Test(priority = 3)
  public void checkEmailFieldCannotBeLeftEmpty() {
    softAssert = new SoftAssert();
    feedbackScreen.clickCategoryDropdown()
        .clickCategoryDDLItemByIndex(
            (int) Math.random() * feedbackScreen.getCategoryDDLItemsCount())
        .typeMessage(MESSAGE)
        .clickSendButton();
    softAssert
        .assertTrue(
            feedbackScreen.getEmailSpanBackgroundOfBefore().contains(RED_EXCLAMATORY_MARK_ICON),
            "Field isn't highlighted with red '!' sign!");
    softAssert.assertEquals(feedbackScreen.getEmailAlertMessage(),
        LocaleProperties.getValueOf(LocalePropertyConst.FEEDBACK_EMAIL_ALERT),
        "Appropriate hint message isn't displayed!");
    softAssert.assertAll();
  }
}
