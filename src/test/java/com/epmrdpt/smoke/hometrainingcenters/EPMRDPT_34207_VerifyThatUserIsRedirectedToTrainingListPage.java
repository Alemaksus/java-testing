package com.epmrdpt.smoke.hometrainingcenters;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34207_VerifyThatUserIsRedirectedToTrainingListPageAfterClickingAvailableTrainingLinkOnTheTrainingCenterPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34207_VerifyThatUserIsRedirectedToTrainingListPage {

  private TrainingBlockScreen trainingBlockScreen;
  private final String country = getValueOf(COUNTRY_NAME_BELARUS);
  private final String city = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private List<String> trainingsCardImagePath;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    TrainingCenterOnStartScreen trainingCenterScreen = new TrainingCenterOnStartScreen();
    trainingCenterScreen.isScreenLoaded();
    trainingCenterScreen
        .clickTrainingCenterCountryByName(country)
        .clickTrainingCenterCityByName(city)
        .clickAvailableTrainingsFirstLink();
    trainingBlockScreen = new TrainingBlockScreen();
    trainingsCardImagePath =
        trainingBlockScreen.getTrainingCardSrcAttributeList();
  }

  @Test(priority = 1)
  public void checkUserRedirectedToTrainingListPage() {
    assertTrue(trainingBlockScreen.isScreenLoaded(),
        "User isn't redirected to ‘Training list’ page!");
  }

  @Test(priority = 2)
  public void checkTrainingCardsAreFilteredByTheLocation() {
    assertTrue(
        trainingBlockScreen.isTrainingCardsFilteredByCountryAndCity(country, city),
        "Training cards aren't filtered by location!");
  }

  @Test(priority = 2)
  public void checkTrainingCardsAreFilteredBySkill() {
    assertTrue(trainingsCardImagePath.stream()
            .anyMatch(anObject -> trainingsCardImagePath.iterator().next().equals(anObject)),
        "Training cards aren't filtered by skill!");
  }
}
