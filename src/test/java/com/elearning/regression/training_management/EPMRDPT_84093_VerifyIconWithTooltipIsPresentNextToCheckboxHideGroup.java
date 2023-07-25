package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DESCRIPTION_SCREEN_TOOLTIP_TEXT_NEXT_TO_HIDE_GROUP;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84093_VerifyIconWithTooltipIsPresentNextToCheckboxHideGroup",
    groups = {"full", "regression"})
public class EPMRDPT_84093_VerifyIconWithTooltipIsPresentNextToCheckboxHideGroup {

  private final String trainingName = "AutoTestWithGroupInLearning";
  private final String groupName = "Android 1";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTraining() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToGroup(trainingName, groupName)
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test
  public void checkTooltipText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactGroupDetailsScreen.isIconNextToHideGroupDisplayed(),
        "Icon next to 'Hide group' is not displayed!"
    );
    reactGroupDetailsScreen
        .mouseOverIconNextToHideGroup();
    softAssert.assertEquals(
        reactGroupDetailsScreen.getTooltipTextOfIconNextToHideGroup(),
        getValueOf(GROUP_DESCRIPTION_SCREEN_TOOLTIP_TEXT_NEXT_TO_HIDE_GROUP),
        "Tooltip text is not correct!"
    );
    softAssert.assertAll();
  }
}
