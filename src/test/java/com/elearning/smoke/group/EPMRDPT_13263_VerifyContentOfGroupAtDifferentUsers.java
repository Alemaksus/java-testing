package com.epmrdpt.smoke.group;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DESCRIPTION_SCREEN_REQUIRED_NOTE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DESCRIPTION_SCREEN_ROOM_NUMBER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_END_DATE_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_GROUP_HEADER_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_START_DATE_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FINISHED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_LEARNING;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13263_VerifyContentOfGroupAtDifferentUsers",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13263_VerifyContentOfGroupAtDifferentUsers {

  private String TAB_BORDER_COLOR = "rgba(0, 158, 204, 1)";
  private String trainingName = "AutoTest_RegistrationOpenStatus";
  private String groupName = "Android 3";
  private ReactGroupScreen reactGroupScreen;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private SoftAssert softAssert;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13263_VerifyContentOfGroupAtDifferentUsers(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupScreen = new ReactGroupScreen();
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
  }

  @Test(priority = 1)
  public void checkTrainingManagementScreenLoading() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
            .clickReactTrainingManagementLink()
            .isScreenLoaded(),
        "'Training management' screen doesn't loaded!");
  }

  @Test(priority = 2)
  public void checkDetailsScreenLoading() {
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    assertTrue(reactDetailTrainingScreen
            .waitAllSpinnersAreNotDisplayed()
            .isPreviewButtonDisplayed(),
        "'Details' screen doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkGroupScreenLoading() {
    reactDetailTrainingScreen.clickGroupsTabs();
    assertEquals(reactDetailTrainingScreen.getGroupsTabBorderColor(),
        TAB_BORDER_COLOR, "'Groups' screen doesn't loaded!");
  }

  @Test(priority = 4)
  public void checkGroupDetailScreenLoading() {
    assertTrue(reactGroupScreen.clickGroupByName(groupName)
        .waitAllSpinnersAreNotDisplayed()
        .isScreenLoaded(), "'Group detail' screen doesn't loaded!");
  }

  @Test(priority = 5)
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

  @Test(priority = 6)
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

  @Test(priority = 7)
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

  @Test(priority = 8)
  public void checkRequiredNoteHasCorrectText() {
    assertTrue(reactGroupDetailsScreen.getRequiredNoteText()
            .contains(getValueOf(GROUP_DESCRIPTION_SCREEN_REQUIRED_NOTE)),
        "Required note has incorrect text!");
  }

  @Test(priority = 9)
  public void checkDetailElementsHeadersDisplayed() {
    assertEquals(reactGroupDetailsScreen.getDetailElementHeaderTitlesText(),
        detailElementHeaderTitles,
        "Detail elements headers have incorrect text!");
  }

  @Test(priority = 9)
  public void checkGroupNameInputDisplayed() {
    assertTrue(reactGroupDetailsScreen.isGroupNameInputDisplayed(),
        "Group name input isn't displayed");
  }

  @Test(priority = 9)
  public void checkRoomNumberInputDisplayed() {
    assertTrue(reactGroupDetailsScreen.isRoomNumberInputDisplayed(),
        "'Room number' input isn't displayed!");
  }

  @Test(priority = 9)
  public void checkStartDateSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isStartDateInputDisplayed(),
        "Start date input isn't displayed");
    softAssert.assertTrue(reactGroupDetailsScreen.isCalendarStartDateIconDisplayed(),
        "Start date calendar icon isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkEndDateSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isEndDateInputDisplayed(),
        "End date input isn't displayed");
    softAssert.assertTrue(reactGroupDetailsScreen.isCalendarEndDateIconDisplayed(),
        "End date calendar icon isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkThatCheckboxesDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isOngoingGroupToggleDisplayed(),
        "'Ongoing group' toggle isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isHideGroupCheckboxDisplayed(),
        "'Hide group' checkbox isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkThatSaveChangesButtonDisplayed() {
    assertTrue(reactGroupDetailsScreen.isSaveChangesButtonDisplayed(),
        "'Save changes' button isn't displayed");
  }

  @Test(priority = 10)
  public void checkTrainerSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isTrainerBlockTitleDisplayed(),
        "Trainers block title isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isTrainerBlockInformationDisplayed(),
        "Trainers block information isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isAddTrainersInputDisplayed(),
        "Select trainers DDl input isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isAddTrainerButtonDisplayed(),
        "'Add trainer' button isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isFullTrainerNameColumnDisplayed(),
        "'Full trainer name' column isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isTrainersSkillsColumnDisplayed(),
        "'Trainers skills' column isn't displayed!");
    softAssert.assertTrue(reactGroupDetailsScreen.isReviewerForColumnDisplayed(),
        "'Reviewer for' column isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 11)
  public void checkThatToGroupsListLinkDisplayed() {
    assertTrue(reactGroupDetailsScreen.isToGroupsListLinkDisplayed(),
        "'To groups list' link isn't displayed");
  }

  private List<String> detailElementHeaderTitles = new ArrayList<>(Arrays.asList(
      getValueOf(REACT_GROUP_DETAILS_SCREEN_GROUP_HEADER_TITLE),
      getValueOf(GROUP_DESCRIPTION_SCREEN_ROOM_NUMBER),
      getValueOf(REACT_GROUP_DETAILS_SCREEN_START_DATE_TITLE),
      getValueOf(REACT_GROUP_DETAILS_SCREEN_END_DATE_TITLE)));
}
