package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_FREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_FREEMIUM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_SUBSCRIBE_BUTTON;
import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREE;
import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREEMIUM;
import static com.epmrdpt.screens.TrainingBlockScreen.BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_PAID;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "38789_VerifyTheContentOfTrainingCardsOnTheTrainingListPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_38789_VerifyTheContentOfTrainingCardsOnTheTrainingListPage {

  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void trainingCardsSectionInitialization() {
    new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitSkillCardsForVisibility();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkTrainingSectionOnTrainingListPageLoading() {
    assertTrue(trainingBlockScreen.isTrainingSectionDisplayed(),
        "Training section on training list page doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkIconsTraining() {
    assertTrue(trainingBlockScreen.isAllIconsOfTrainingCardsDisplayed(),
        "Icons of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkTitlesTraining() {
    assertTrue(trainingBlockScreen.isAllTitlesOfTrainingCardsDisplayed(),
        "Titles of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkTypesTraining() {
    assertTrue(trainingBlockScreen.isAllTypesOfTrainingCardsDisplayed(),
        "Types of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkLevelsTraining() {
    assertTrue(trainingBlockScreen.isAllLevelsOfTrainingCardsDisplayed(),
        "Levels of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkFormatsTraining() {
    assertTrue(trainingBlockScreen.isAllFormatsOfTrainingCardsDisplayed(),
        "Formats of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkLanguagesTraining() {
    assertTrue(trainingBlockScreen.isAllLanguagesOfTrainingCardsDisplayed(),
        "Languages of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkPricesTrainingPresent() {
    assertTrue(trainingBlockScreen.isAllPricesOfTrainingCardsDisplayed(),
        "Prices of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkPricesTrainingText() {
    softAssert = new SoftAssert();
    List<String> listOfPriceText = trainingBlockScreen.getListOfTrainingCardPriceText();
    for (int i = 0; i < listOfPriceText.size(); i++) {
      int trainingCardNumber = i++;
      softAssert.assertTrue(
          listOfPriceText.get(i)
              .equals(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREE)) ||
              listOfPriceText.get(i)
                  .equals(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREEMIUM)) ||
              listOfPriceText.get(i)
                  .equals(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_PAID)),
          format("Training card with number - %s - has incorrect price text", trainingCardNumber));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkLocationsTraining() {
    assertTrue(trainingBlockScreen.isAllLocationsOfTrainingCardsDisplayed(),
        "Locations of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkDateTraining() {
    assertTrue(trainingBlockScreen.isAllDurationsOfTrainingCardsDisplayed(),
        "Dates of training list don't displayed!");
  }

  @Test(priority = 2)
  public void checkTrainingButtonPresent() {
    assertTrue(trainingBlockScreen.isAllButtonOfTrainingCardsDisplayed(),
        "Button of training list don't displayed");
  }

  @Test(priority = 2)
  public void checkTrainingButtonText() {
    softAssert = new SoftAssert();
    List<String> listOfButtonText = trainingBlockScreen.getListOfTrainingCardButtonText();
    for (int i = 0; i < listOfButtonText.size(); i++) {
      int trainingCardNumber = i++;
      softAssert.assertTrue(
          listOfButtonText.get(i)
              .equals(LocaleProperties.getValueOf(TRAINING_DESCRIPTION_REGISTER_BUTTON)) ||
              listOfButtonText.get(i)
                  .equals(LocaleProperties.getValueOf(TRAINING_DESCRIPTION_SUBSCRIBE_BUTTON)),
          format("Training card with number - %s - has incorrect button text", trainingCardNumber));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTrainingCardHeaderColor() {
    softAssert = new SoftAssert();
    List<Element> listOfHeaders = trainingBlockScreen.getListOfTrainingCardHeaders();
    for (int i = 0; i < listOfHeaders.size(); i++) {
      int trainingCardNumber = i++;
      softAssert.assertTrue(
          (listOfHeaders.get(i).getText()
              .contains(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREE)) &&
              listOfHeaders.get(i).getCssValue(Attribute.BACKGROUND_COLOR.toString())
                  .equals(BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREE)) ||
              (listOfHeaders.get(i).getText()
                  .contains(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREEMIUM)) &&
                  listOfHeaders.get(i).getCssValue(Attribute.BACKGROUND_COLOR.toString())
                      .equals(BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREEMIUM)) ||
              (listOfHeaders.get(i).getText()
                  .contains(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_PAID)) &&
                  listOfHeaders.get(i).getCssValue(Attribute.BACKGROUND_COLOR.toString())
                      .equals(BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_PAID)),
          format("Training card with number - %s - has incorrect header color",
              trainingCardNumber));
    }
    softAssert.assertAll();
  }
}
