package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_ASTRAVYETS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SubscribeScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14531_VerifyThatSubscribeTrainingPopUpAppearsAfterClickingOnSubscribeButton",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14531_VerifyThatSubscribeTrainingPopUpAppearsAfterClickingOnSubscribeButton {

  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private TrainingCardsSectionService trainingCardsSectionService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    trainingListScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
    trainingBlockScreen = new TrainingBlockScreen();
    trainingCardsSectionService = new TrainingCardsSectionService();
  }

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(trainingListScreen
            .isScreenLoaded(),
        "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkSubscribeButtonIsDisplayedAfterSearchingNoAvailableTrainingsBySkills() {
    trainingBlockScreen
        .clickSearchDropDown()
        .waitSkillsFilterTabVisibility();
    assertTrue(trainingCardsSectionService
            .isNoAvailableTrainingsBySkillsFound(),
        "'Subscribe Button' isn't displayed after searching no available trainings By Skills!");
  }

  @Test(priority = 3)
  public void checkSubscribePopUpAppearsAfterClickingOnSubscribeButtonForNoAvailableTrainingsBySkills() {
    assertTrue(trainingBlockScreen
            .clickSubscribeButton()
            .waitEmailInputVisbility()
            .isScreenLoaded(),
        "'Subscribe to training' pop-up hasn't appeared!");
  }

  @Test(priority = 4)
  public void checkSubscribeButtonStillDisplayedAfterAddingSearchByLocation() {
    trainingBlockScreen
        .clickSearchDropDown()
        .waitLocationFilterTabVisibility()
        .clickLocationFiltersTab()
        .clickCountryByNameFromDropDown(getValueOf(COUNTRY_NAME_BELARUS))
        .clickCityByNameFromDropDown(getValueOf(CITY_NAME_BELARUS_ASTRAVYETS))
        .clickSearchDropDown();
    assertTrue(trainingBlockScreen.isSubscribeButtonDisplayed(),
        "'Subscribe Button' isn't displayed after searching no available trainings By Location!");
  }

  @Test(priority = 5)
  public void checkSubscribePopUpAppearsAfterClickingOnSubscribeButtonForNoAvailableTrainingsBySkillsAndLocation() {
    assertTrue(trainingBlockScreen
            .clickSubscribeButton()
            .isScreenLoaded(),
        "'Subscribe to training' pop-up hasn't appeared!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void closeSubscribePopUp(Method method) {
    if (method.getName().equals(
        "checkSubscribePopUpAppearsAfterClickingOnSubscribeButtonForNoAvailableTrainingsBySkills")) {
      new SubscribeScreen()
          .clickCloseSubscribeScreen()
          .waitSubscribeButtonVisibility()
          .scrollToTop();
    }
  }
}
