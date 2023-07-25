package com.epmrdpt.smoke.participants;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_GROUP_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ASSIGN_AC_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_DONE_IN_ELEARN_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_LOCATION_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_RATING_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SD_STATUS_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SD_STATUS_SYNCHRONIZATION_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SD_USER_SYNCHRONIZATION_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SEND_EMAIL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SURVEY_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_INFO_DATE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13261_VerifyThatTrainingManagerCanOpenTheParticipantsPage",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13261_VerifyThatTrainingManagerCanOpenTheParticipantsPage {

  private String trainingNameWithParticipants = "AutoTestLearning 2";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private String TAB_BORDER_COLOR = "rgba(71, 74, 89, 1)";
  private SoftAssert softAssert;
  private User user;
  private String env = System.getProperty("env");
  private static final String ENGLISH_LEVEL_HEADER = "English level";
  private static final String ENGLISH_TEST_HEADER = "English test";

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13261_VerifyThatTrainingManagerCanOpenTheParticipantsPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
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
        .searchTrainingByNameAndRedirectOnIt(trainingNameWithParticipants);
    assertTrue(reactDetailTrainingScreen
            .waitAllSpinnersAreNotDisplayed()
            .isPreviewButtonDisplayed(),
        "'Details' screen doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkParticipantsScreenLoading() {
    reactDetailTrainingScreen.clickReactParticipantsTab();
    assertTrue(reactParticipantsTrainingScreen
            .waitSpinnerOfLoadingInvisibility()
            .isScreenLoaded(),
        "'Participants' screen doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkBreadcrumbsBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isHomeButtonDisplayed(),
        "Home icon isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isPlanningTitleDisplayed(),
        "Planning title isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen.isScreenLoaded(),
        "Training name isn't displayed!");
    softAssert.assertTrue(reactDetailTrainingScreen
            .getTrainingTitleText()
            .contains(trainingNameWithParticipants),
        "Name of the training does not match with the selected training!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
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
    softAssert.assertEquals(reactDetailTrainingScreen.getParticipantsTabBorderColor(),
        TAB_BORDER_COLOR, "Border color of selected participants tab has incorrect color!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkSearchComponents() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSearchInputDisplayed(),
        "'Search' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE)),
        "'Country' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE)),
        "'City' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE)),
        "'Current status' input isn't displayed!");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectRegistrationDateFromInputDisplayed(),
        "'From' input isn't displayed!");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectRegistrationDateToInputDisplayed(),
        "'To' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT)),
        "'English' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFoundElementsLabelDisplayed(),
        "'Found elements' counter isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isResetButtonDisplayed(),
        "'Reset' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isApplyButtonDisplayed(),
        "'Apply' button isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkGeneralInformationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isInformationBlockButtonDisplayed(
        getValueOf(PARTICIPANTS_ASSIGN_AC_BUTTON)), "'Assign AC' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isExcelButtonDisplayed(),
        "'Excel' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isInformationBlockButtonDisplayed(
        getValueOf(PARTICIPANTS_SEND_EMAIL_BUTTON)), "'Send email' button isn't displayed!");
    if (!env.equals("STAGE")) {
      softAssert.assertTrue(reactParticipantsTrainingScreen.isStaffingDeskButtonDisplayed(),
          "'Staffing Desk' button isn't displayed!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkComponentsForAddingParticipants() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isAddParticipantInputDisplayed(),
        "'Add participant' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isAddParticipantButtonDisplayed(),
        "'Add participant' button isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkParticipantsTab() {
    reactParticipantsTrainingScreen
        .clickEditButton()
        .waitConfigureColumnsTabHeaderVisibility()
        .clickConfiguresColumnsTabCheckAllButton()
        .clickConfigureColumnsTabApplyButton();
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFullNameColumnHeaderDisplayed(),
        "'Full name' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        getValueOf(PARTICIPANTS_SURVEY_COLUMN_HEADER)), "'Survey' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        ENGLISH_TEST_HEADER), "'English test' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        ENGLISH_LEVEL_HEADER), "'English level' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        getValueOf(PARTICIPANTS_RATING_COLUMN_HEADER)), "'Rating' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        getValueOf(PARTICIPANTS_LOCATION_COLUMN_HEADER)), "'Location' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        getValueOf(REACT_TRAINING_OFFLINE_TASK_INFO_DATE)), "'Date' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
            getValueOf(PARTICIPANTS_APPLICANT_STATUS_COLUMN_HEADER)),
        "'Applicant status' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
        getValueOf(PARTICIPANTS_SD_STATUS_COLUMN_HEADER)), "'SD status' header isn't displayed!");
    new ReactParticipantsService().moveScrollBarToTheCenter();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isEmailColumnHeaderDisplayed(),
        "'E-mail' header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkParticipantsTabSecondPart() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
            getValueOf(PARTICIPANTS_DONE_IN_ELEARN_COLUMN_HEADER)),
        "'Done in eLearn' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
            getValueOf(LEARNING_TASK_TAB_GROUP_LABEL)),
        "'Group' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
            getValueOf(PARTICIPANTS_SD_USER_SYNCHRONIZATION_COLUMN_HEADER)),
        "'SD User synchronization' header isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantTableHeadersDisplayed(
            getValueOf(PARTICIPANTS_SD_STATUS_SYNCHRONIZATION_COLUMN_HEADER)),
        "'SD Status synchronization' header isn't displayed!");
    reactParticipantsTrainingScreen.clickAfterScrollBarToTheEnd();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isPublicTraineeAccountHeaderDisplayed(),
        "'Public Trainee account' header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkPagination() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isShownElementsLabelDisplayed(),
        "Show <N> elements label isn't displayed!");
    reactParticipantsTrainingScreen
        .clickApplyButton()
        .waitSpinnerOfLoadingInvisibility();
    softAssert.assertEquals(
        reactParticipantsTrainingScreen.getNumberOfFoundedParticipants(),
        reactParticipantsTrainingScreen.getNumberOfParticipantsFromTab(),
        "The number of found students does not match with the number of students from the tab!");
    reactParticipantsTrainingScreen.clickPaginationDDLButton();
    softAssert.assertEquals(reactParticipantsTrainingScreen.getPaginationDDLElementsText(),
        paginationPopUpElements,
        "'Elements of pop up pagination' have incorrect text!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isPaginationOnLeftSideDisplayed(),
        "Pagination on left side isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isPaginationOnRightSideDisplayed(),
        "Pagination on right side isn't displayed!");
    softAssert.assertAll();
  }

  private List<String> paginationPopUpElements = new ArrayList<>(Arrays.asList(
      "10", "25", "50", "100", "300", "500", "1000"));
}
