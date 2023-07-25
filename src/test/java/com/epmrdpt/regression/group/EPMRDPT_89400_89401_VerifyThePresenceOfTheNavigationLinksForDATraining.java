package com.epmrdpt.regression.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_TO_ClASSES_LIST_LINK;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactClassesDetailsScreen;
import com.epmrdpt.screens.ReactClassesScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_89400_89401_VerifyThePresenceOfTheNavigationLinksForDATraining",
    groups = {"full", "regression"})
public class EPMRDPT_89400_89401_VerifyThePresenceOfTheNavigationLinksForDATraining {

  private static final String TRAINING_NAME = "Department Affiliate";
  private static final String CLASS_NAME = "Department affiliate 2";
  private ReactClassesScreen reactClassesScreen;
  private ReactClassesDetailsScreen reactClassesDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactClassesDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickClassesTabs()
        .waitDataLoading()
        .clickClassesByName(CLASS_NAME)
        .waitTitleForVisibility();
  }

  @Test(priority = 1)
  public void checkPresenceOfLinksForDATraining() {
    assertTrue(reactClassesDetailsScreen.isLinkByNameDisplayed
            (getValueOf(REACT_CLASSES_SCREEN_TO_ClASSES_LIST_LINK)),
        ("'To classes list' link isn't displayed!"));
  }

  @Test(priority = 2)
  public void checkToClassesListLink() {
    reactClassesScreen = reactClassesDetailsScreen.clickClassesListLink().waitDataLoading();
    assertTrue(reactClassesScreen.isScreenLoaded(),
        "'To classes list' link doesn't redirect the user to 'Classes list' page!");
  }
}
