package com.epmrdpt.regression.home;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_ONLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PLANNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_SOON;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34305_VerifyTheAttributesUsedForQuickFilters",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34305_VerifyTheAttributesUsedForQuickFilters {

  private TrainingBlockScreen trainingBlockScreen;
  private List<String> listAttributesForQuickFilters = new ArrayList<>();

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void fillArrayListWithObjects() {
    trainingBlockScreen = new TrainingBlockScreen().waitTrainingCardsVisibility();
    listAttributesForQuickFilters.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_SOON));
    listAttributesForQuickFilters.add(
        LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PLANNED));
    listAttributesForQuickFilters.add(
        LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_ONLINE));
    listAttributesForQuickFilters.add(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_PAID));
  }

  @Test(priority = 1)
  public void checkAttributesForQuickFiltersIsCorrectOnHomePage() {
    assertEquals(trainingBlockScreen.getQuickFilterTabsText(), listAttributesForQuickFilters,
        "Attributes for quick filters isn't correct on the Home page!");
  }

  @Test(priority = 2)
  public void checkAttributesForQuickFiltersIsCorrectOnTrainingListPage() {
    new HeaderScreen().clickTrainingListNavigationLink().waitSkillCardsForVisibility();
    assertEquals(trainingBlockScreen.getQuickFilterTabsText(), listAttributesForQuickFilters,
        "Attributes for quick filters isn't correct on the Training List page!");
  }
}
