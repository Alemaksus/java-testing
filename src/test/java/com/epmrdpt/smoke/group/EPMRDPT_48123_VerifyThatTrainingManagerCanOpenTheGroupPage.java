package com.epmrdpt.smoke.group;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FINISHED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_LEARNING;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_48123_VerifyThatTrainingManagerCanOpenTheGroupPage",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_48123_VerifyThatTrainingManagerCanOpenTheGroupPage {

  private String trainingName = "AutoTest_RegistrationOpenStatus";
  private String TAB_BORDER_COLOR = "rgba(0, 158, 204, 1)";
  private String groupName = "Android 1";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private SoftAssert softAssert;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_48123_VerifyThatTrainingManagerCanOpenTheGroupPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupDescriptionScreen() {
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    new HeaderScreen()
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkDetailsScreenLoading() {
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    assertTrue(reactDetailTrainingScreen
            .waitAllSpinnersAreNotDisplayed()
            .isPreviewButtonDisplayed(),
        "'Details' screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkGroupScreenLoading() {
    reactDetailTrainingScreen.clickGroupsTabs();
    assertEquals(reactDetailTrainingScreen.getGroupsTabBorderColor(),
        TAB_BORDER_COLOR, "'Groups' screen doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkGroupDetailScreenLoading() {
    assertTrue(new ReactGroupScreen().clickGroupByName(groupName)
        .waitAllSpinnersAreNotDisplayed()
        .isScreenLoaded(), "'Group detail' screen doesn't loaded!");
  }

  @Test(priority = 4)
  public void checkBreadcrumbsBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isHomeButtonDisplayed(),
        "Home icon isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isPlanningTitleDisplayed(),
        "Planning title isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen
            .getTrainingTitleText()
            .contains(trainingName),
        "Name of the training does not match with the selected training!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkNavigationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isDetailsTabDisplayed(),
        "Detail tab isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isParticipantsTabDisplayed(),
        "Participants tab isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isGroupsTabDisplayed(),
        "Groups tab isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isAuditTabDisplayed(),
        "Audit tab isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatToGroupsListLinkDisplayed() {
    assertTrue(reactGroupDetailsScreen.isToGroupsListLinkDisplayed(),
        "'To groups list' link isn't displayed");
  }

  @Test(priority = 4)
  public void checkStatusBar() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isStatusNameDisplayed
        (getValueOf(TRAINING_MANAGER_STATUS_FORMATION)), "'Formation' status isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isStatusNameDisplayed
        (getValueOf(TRAINING_MANAGER_STATUS_LEARNING)), "'Learning' status isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isStatusNameDisplayed
        (getValueOf(TRAINING_MANAGER_STATUS_FINISHED)), "'Finished' status isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkGroupDetailsSectionDisplayed() {
    assertTrue(reactGroupDetailsScreen.isGroupDetailsSectionDisplayed(),
        "'Group details' section isn't displayed!");
  }

  @Test(priority = 5)
  public void checkTrainersSectionDisplayed() {
    assertTrue(reactGroupDetailsScreen.isTrainersSectionDisplayed(),
        "'Trainers' section isn't displayed!");
  }

  @Test(priority = 5)
  public void checkParticipantsSectionDisplayed() {
    assertTrue(reactGroupDetailsScreen.isParticipantsSectionDisplayed(),
        "'Participants' section isn't displayed!");
  }
}
