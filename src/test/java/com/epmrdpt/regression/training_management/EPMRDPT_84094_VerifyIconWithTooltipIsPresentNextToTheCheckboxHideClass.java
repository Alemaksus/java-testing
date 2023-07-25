package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_HIDE_CLASS_ICON_TOOLTIP_TEXT;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactClassesDetailsScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84094_VerifyIconWithTooltipIsPresentNextToTheCheckboxHideClass",
    groups = {"full", "regression"})
public class EPMRDPT_84094_VerifyIconWithTooltipIsPresentNextToTheCheckboxHideClass {

  private ReactClassesDetailsScreen reactClassesDetailsScreen;
  private String trainingName = "Classes test";
  private String className = "Department affiliate 2";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager());
    reactClassesDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickClassesTabs()
        .waitDataLoading()
        .clickClassesByName(className)
        .waitTitleForVisibility();
  }

  @Test
  public void verifyHideClassCheckboxIcon() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactClassesDetailsScreen.isHideClassCheckboxIconDisplayed(),
        "'Hide class' checkbox icon isn't displayed!");
    softAssert.assertEquals(reactClassesDetailsScreen.getHideClassCheckboxIconTooltipText(),
        getValueOf(REACT_CLASSES_SCREEN_HIDE_CLASS_ICON_TOOLTIP_TEXT),
        "'Hide class' checkbox icon has incorrect text!");
    softAssert.assertAll();
  }
}
