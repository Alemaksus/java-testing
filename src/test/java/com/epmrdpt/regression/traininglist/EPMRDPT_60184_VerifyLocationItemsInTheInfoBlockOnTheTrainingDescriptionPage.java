package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_ASTRAVYETS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutCityTrainingCenterScreen;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_60184_VerifyLocationItemsInTheInfoBlockOnTheTrainingDescriptionPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_60184_VerifyLocationItemsInTheInfoBlockOnTheTrainingDescriptionPage {

  private String trainingName = "Vk auto-assessment";
  private String сountryName = getValueOf(COUNTRY_NAME_BELARUS);
  private String сityWithTrainingCenter = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private String сityWithoutTrainingCenter = getValueOf(CITY_NAME_BELARUS_ASTRAVYETS);
  private TrainingDescriptionScreen trainingDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void moveToTrainingDescriptionPage() {
    trainingDescriptionScreen = new TrainingBlockScreen()
        .clickViewMoreTrainingsLink()
        .waitTrainingCardsVisibility()
        .clickTrainingCardByName(trainingName);
  }

  @Test(priority = 1)
  public void checkThatClickOnCityWithoutTrainingCenterWillNotOpenOtherPage() {
    trainingDescriptionScreen.tryClickOnCityWithoutTrainingCenterOnContactsShortInfo(
        сityWithoutTrainingCenter);
    assertEquals(trainingName, trainingDescriptionScreen.getTrainingDescriptionTitleText(),
        "Click on the city without a Training center doesn't open other page");
  }

  @Test(priority = 2)
  public void checkThatAfterClickOnCountryOpenedPageAboutWithFocusOnTrainingCenterInDesiredCountry() {
    AboutScreen aboutScreen = trainingDescriptionScreen.clickOnCountryOnContactsShortInfo(
        сountryName);
    trainingDescriptionScreen.switchToLastWindow();
    assertEquals(сountryName.toUpperCase(),
        aboutScreen.getTrainingCenterActiveCountryName(),
        "About page isn't opened - with a focus on the training center carousel in the desired country");
  }

  @Test(priority = 3)
  public void checkThatAfterClickOnCityWithTrainingCentrePageInDesiredLocationIsOpened() {
    trainingDescriptionScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
    AboutCityTrainingCenterScreen aboutCityTrainingCenterScreen = trainingDescriptionScreen
        .clickOnCityWithTrainingCenterOnContactsShortInfo(сityWithTrainingCenter);
    trainingDescriptionScreen.switchToLastWindow();
    assertTrue(aboutCityTrainingCenterScreen.isCityTrainingCenterTitleDisplayed(
            getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_BELARUS_GOMEL)),
        "The Training center page in the desired location isn't opened");
  }
}
