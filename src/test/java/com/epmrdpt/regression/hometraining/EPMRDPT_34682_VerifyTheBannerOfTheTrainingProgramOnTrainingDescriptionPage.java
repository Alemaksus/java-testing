package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREE;
import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREEMIUM;
import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_PAID;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34682_VerifyTheBannerOfTheTrainingProgramOnTrainingDescriptionPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34682_VerifyTheBannerOfTheTrainingProgramOnTrainingDescriptionPage {

  private static final String FREE_TRAINING_PROGRAM_NAME = "AutoTest_WithFreePricing";
  private static final String PAID_TRAINING_PROGRAM_NAME = "AutoTest_WithPaidPricing";
  private static final String FREEMIUM_TRAINING_PROGRAM_NAME = "AutoTest_WithFreemiumPricing";

  private String trainingName;
  private String pricingColor;

  private TrainingDescriptionScreen trainingDescriptionScreen;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of trainings with different pricing")
  public EPMRDPT_34682_VerifyTheBannerOfTheTrainingProgramOnTrainingDescriptionPage(
      String trainingName, String pricingTypeColor) {
    this.trainingName = trainingName;
    this.pricingColor = pricingTypeColor;
  }

  @DataProvider(name = "Provider of trainings with different pricing")
  public static Object[][] providerOfTrainingWithPrices() {
    return new Object[][]{
        {FREE_TRAINING_PROGRAM_NAME, BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREE},
        {PAID_TRAINING_PROGRAM_NAME, BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_PAID},
        {FREEMIUM_TRAINING_PROGRAM_NAME, BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREEMIUM}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingProgram() {
    trainingDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName);
  }

  @Test(priority = 1)
  public void checkBannerBackgroundImageDisplayed() {
    assertTrue(trainingDescriptionScreen.isTrainingBannerPresent(),
        "Banner on Training description page isn't displayed correct!");
  }

  @Test(priority = 2)
  public void checkPricingSection() {
    softAssert = new SoftAssert();
    softAssert.assertFalse(trainingDescriptionScreen.getTrainingPricingText().isEmpty(),
        "Field 'Pricing type' on Training description page is empty!");
    if (!trainingName.equals(FREE_TRAINING_PROGRAM_NAME)) {
      softAssert.assertFalse(trainingDescriptionScreen.getPricingBackgroundImageValue().isEmpty(),
          format("'%s' program hasn't pricing icon!", trainingName));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkFormatSection() {
    softAssert = new SoftAssert();
    softAssert.assertFalse(trainingDescriptionScreen.getTrainingFormatText().isEmpty(),
        "Field 'Format type' on Training description page is empty!");
    softAssert.assertFalse(trainingDescriptionScreen.getFormatBackgroundImageValue().isEmpty(),
        "Format type hasn't icon!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTrainingProgramName() {
    assertEquals(trainingDescriptionScreen.getTrainingDescriptionTitleText(), trainingName,
        "Field 'Training program' has incorrect name!");
  }

  @Test(priority = 2)
  public void checkAdditionalInformation() {
    assertFalse(trainingDescriptionScreen.getTrainingAdditionalInfoText().isEmpty(),
        "Field 'Additional info' on Training description page is empty!");
  }

  @Test(priority = 2)
  public void checkRegisterButtonDisplayed() {
    assertTrue(trainingDescriptionScreen.isRegisterButtonDisplayed(),
        "'Register' button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkPriceTrainingProgram() {
    if (trainingName.equals(PAID_TRAINING_PROGRAM_NAME)) {
      assertFalse(trainingDescriptionScreen.getPriceText().isEmpty(),
          "Field 'Price with currency' on Training description page is empty!");
    }
  }

  @Test(priority = 2)
  public void checkPricingBarHasCorrectColor() {
    assertEquals(trainingDescriptionScreen.getPricingBarColor(), pricingColor,
        "Bar in header on Training description page has incorrect color!");
  }
}
