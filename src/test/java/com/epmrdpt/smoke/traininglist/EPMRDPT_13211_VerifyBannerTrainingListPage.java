package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingListScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13211_VerifyBannerTrainingListPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13211_VerifyBannerTrainingListPage {

  private TrainingListScreen trainingListScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingListScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitBannerTitleVisibility();
  }

  @Test(priority = 1)
  public void checkTrainingScreenLoading() {
    assertTrue(trainingListScreen.isTrainingCardsLoaded(), "Training screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkBannerTitleDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        trainingListScreen.isBannerTitleLabelDisplayed(),
        "Banner's title of Training List screen isn't displayed!");
    softAssert.assertEquals(
        trainingListScreen.getBannerTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_BANNER_TITLE),
        "Banner's title of Training list screen isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkBannerDescriptionTextDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        trainingListScreen.isBannerTextLabelDisplayed(),
        "Banner's description text of Training List screen isn't displayed!");
    softAssert.assertEquals(
        trainingListScreen.getBannerTextLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_BANNER_DESCRIPTION_TEXT),
        "Banner's description text of Training list screen isn't correct!");
    softAssert.assertAll();
  }
}
