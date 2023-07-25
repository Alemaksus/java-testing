package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34276_VerifyIfTrainingHasStatusPlannedOrRegistrationOpenTheCopyRegistrationLinkButtonShouldIsAvailableForDepartmentAffiliate",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_34276_VerifyIfTrainingHasStatusPlannedOrRegistrationOpenTheCopyRegistrationLinkButtonShouldIsAvailableForDepartmentAffiliate {

  private final String departmentAffiliateTrainingName = "AutoTestDA";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager());
  }

  @Test(priority = 1)
  public void checkCopyRegistrationLinkDisplaying() {
    new HeaderScreen().clickReactTrainingManagementLink()
        .waitAllTrainingNameDisplayed();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(departmentAffiliateTrainingName);
    assertTrue(reactDetailTrainingScreen.isCopyRegistrationLinkButtonDisplayed(),
        "'Copy registration link' button isn't available for Department affiliate training!");
  }

  @Test(priority = 2)
  public void checkLinkCopiedSuccessfullyPopUpDisplaying() {
    assertEquals(reactDetailTrainingScreen.clickCopyRegistrationLinkButton().getStatusMessageText(),
        getValueOf(DETAIL_TRAINING_SCREEN_LINK_COPIED_SUCCESSFULLY_POP_UP),
        "Registration link didn't copied");
  }
}
