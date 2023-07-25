package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SEARCH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_TITLE_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_INITIATED_BY_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_REJECTION_REASON_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_CANCEL_BUTTON_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_OK_BUTTON_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ADD_PARTICIPANT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ADD_PARTICIPANT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ADD_PARTICIPANT_INPUT_SEARCH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_APPLY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ASSIGN_AC_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_ALL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_INFORMATION_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_UNCHECK_ALL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_DOCUMENTS_ARCHIVE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_EDIT_STATUS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ELEMENTS_FOUND_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_MOVE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_PARTICIPANTS_LIST_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_PUBLIC_TRAINEE_ACCOUNT_COLUMN_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUBSCRIBERS_TO_EXCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUCCESSFULLY_ADDED_PARTICIPANT_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SURVEY_RESULTS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_GROUP_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PAGINATION_SHOW_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PAGINATION_TOTAL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_RATING_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_MANAGEMENT_FULL_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_MANAGEMENT_RESET;
import static com.epmrdpt.framework.ui.element.Element.ATTRIBUTE_CHECKED;
import static com.epmrdpt.framework.ui.element.Element.ATTRIBUTE_CLASS;
import static com.epmrdpt.utils.StringUtils.getStringValueByRegex;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.TimeoutException;

public class ReactParticipantsTrainingScreen extends AbstractScreen {

  private static final String SUCCESSFUL_STATUS = "English test has been successfully assigned.";
  private static final String ASSIGN_ENGLISH_TEST = "Assign English test";
  private static final String TEXT_LOCATOR_PATTERN = "//*[contains(text(),'%s')]";
  private static final String STUDENT_STATUS_PATTERN = "//div[text()='%s']/ancestor::div[contains(@style, 'center')]/following-sibling::div//div[contains(@class, 'status-name')]/div";
  private static final String STUDENT_NAME_BY_INDEX_PATTERN = "(//div[@class='fullName']/div/div/div/div/div/div)[%s]";
  private static final String APPLICANT_STATUS_PATTERN = "//div[@class='react-tiny-popover-container']//div[text()][%s]";
  private static final String ENGLISH_LEVEL_PATTERN = "(//div[contains(@class,'-clickable')]//div[contains(@style,'min-width') and contains(@style,'auto')]/div/div/div)[%s]";
  private static final String APPLICANT_STATUS_PATTERN_BY_NAME_FROM_DROP_DOWN_LIST ="//div[@class='react-tiny-popover-container']//div[text()='%s']";
  private static final String APPLICATION_STATUS_FROM_DROP_DOWN_XPATH_PATTERN
      = "(//input[@placeholder='%s']//following::*[contains(@class, 'clickable')])[%d]";
  private static final String FILTER_INPUT_PATTERN
      = "//div[contains(@class, 'input') and text()='%s']";
  private static final String REGISTRATION_DATE_INPUT_PATTERN
      = "//input[contains(@class, 'input') and @placeholder='%s']";
  private static final String BUTTON_PATTERN =
      "//div[contains(@class,'button')]" + ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN;
  private static final String PARTICIPANT_TABLE_HEADERS_PATTERN
      = "//div[contains(@class,'table')]" + ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN;
  private static final String FIND_PARTICIPANT_BY_NAME_PATTERN = "//div[contains(text(),'%s')]";
  private static final String RATING_OF_STUDENT_XPATH_PATTERN
      = "(//div[text()='%s']/ancestor::div[@class='fullName']/parent::div/following-sibling::div//a/div)[last()]";
  private static final String ENGLISH_TEST_RESULT_OF_STUDENT_XPATH_PATTERN
      = "//div[text()='%s']/ancestor::div[@class='fullName']/../following-sibling::*/div/div/div[2]//*[contains(@class, 'text')]";
  private static final String EXCEL_CHOICE_POP_UP_PATTERN = "//div[@class='uui-popper']";
  private static final String CHECKBOX_BY_STUDENT_NAME_PATTERN = FIND_PARTICIPANT_BY_NAME_PATTERN
      + "/ancestor::div[contains(@class,'table-row-container') and not(contains(@class, 'header'))]//div[@class='uui-checkbox']";
  private static final String SURVEY_ICON_BY_NAME_PATTERN
      = "//div[text()='%s']/ancestor::div[@class='fullName']/../following-sibling::*/div/div/div//*[name()='svg']";
  private static final String ESCAPED_TEXT_SEARCH_PATTERN = "//div[text()=\"%s\"]";
  private static final String CONFIGURE_COLUMNS_POP_UP_PATTERN = "//div[contains(@class,'uui-modal-window')]";
  private static final String CONFIGURE_COLUMNS_POP_UP_CHECK_BOX_PATTERN =
      "//div[text()='%s']/../../..//div[contains(@class, 'uui-checkbox')]";
  private static final String SEARCH_INPUT_BLOCK_PATTERN = "//div[@id='groups-search-bar']//";
  private static final String APPLICANT_STATUS_PATTERN_BY_NAME =
      "//div[text()='%s']/ancestor::div[contains(@style,'center')]"
          + "/following-sibling::div/descendant::div[text()='%s']";
  private static final String FIRST_STUDENT_IN_ADD_PARTICIPANT_DDL_BY_PARTIAL_NAME =
      "//div[contains(@class,'uui-focus')]//div[contains(text(),'%s')]";
  private static final String PARTICIPANTS_LIMIT_IN_DDL_PATTERN =
      "//div[not(contains(@class,'uui-input')) and text()='%s']";
  private final String LABEL_PATTERN = "/../../../preceding-sibling::div//*[@class='uui-label']";
  private final String RESET_BUTTON_LABEL_PATTERN = "//div[contains(@class,'reset-filter-button')]";
  private final String LABEL_PLACEHOLDER_PATTERN = "(//*[contains(@placeholder, '%s')])[1]";
  private final String STUDENT_CV_PATTERN =
      "//div[text()='%s']/ancestor::div[@class='fullName']//*[name()='svg']";
  private final String STUDENT_CHEATING_ICON_PATTERN =
      "//div[text()='%s']/ancestor::div[@class='fullName']/parent::div/following-sibling::div//*[name()='svg']";
  private final String PARTICIPANT_COGWHEEL_CONTEXT_MENU_PATTERN =
      "//div[@class='react-tiny-popover-container']//div[text()='%s']";
  private final String GROUP_OF_STUDENT_PATTERN =
      "//div[contains(text(),'%s')]//ancestor::div[contains(@class, 'uui-table-row-container')]//div[text()='%s']";
  private final String CHANGES_POP_UP_PATTERN =
      "//div[@class='uui-snackbar-item-self']//div[text()='%s']";
  private final String ELEMENT_BY_TEXT_PATTERN = "//*[text()='%s']";
  private final String ITEM_BY_DROPDOWN_IN_SEARCH = "//div[@class='uui-popper']//div[text()='%s']";

  private Element spinnerOfLoading = Element.byXpath("//div[contains(@class,'uui-table-row-container')]"
      + "/following::div//div[contains(@class,'uui-spinner-animation')]");
  private Element studentsFullName = Element.byClassName("fullName");
  private Element excelButton = Element
      .byXpath("//div[contains(@class,'button')]//div[text()='Excel']");
  private Element downloadDocumentsArchiveButton = Element.byXpath(
      EXCEL_CHOICE_POP_UP_PATTERN + ESCAPED_TEXT_SEARCH_PATTERN,
      getValueOf(PARTICIPANTS_DOCUMENTS_ARCHIVE_BUTTON));
  private Element downloadSurveyResultsButton = Element.byXpath(
      EXCEL_CHOICE_POP_UP_PATTERN + ESCAPED_TEXT_SEARCH_PATTERN,
      getValueOf(PARTICIPANTS_SURVEY_RESULTS_BUTTON));
  private Element downloadParticipantsListButton = Element.byXpath(
      EXCEL_CHOICE_POP_UP_PATTERN + ESCAPED_TEXT_SEARCH_PATTERN,
      getValueOf(PARTICIPANTS_PARTICIPANTS_LIST_BUTTON));
  private Element excelChoicePopUp = Element.byXpath(EXCEL_CHOICE_POP_UP_PATTERN);
  private Element confirmActionWindow = Element.byXpath("//div[contains(@class, 'modal-window')]");
  private Element confirmActionButton = Element.byXpath(
      "//div[contains(@class, 'modal-window')]//div[contains(@class,'button')]/div[text()='%s']",
      getValueOf(PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON));
  private Element searchInput = Element.byXpath(SEARCH_INPUT_BLOCK_PATTERN + "input");
  private Element searchIcon = Element.byXpath(SEARCH_INPUT_BLOCK_PATTERN +
      "*[name()='svg']");
  private Element registrationDateLabel = Element.byXpath(
      "//*[@placeholder='%s']/ancestor::div[@class='uui-label-top']//*[@class='uui-label']",
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM));
  private Element applyButton = Element.byXpath(BUTTON_PATTERN,
      getValueOf(PARTICIPANTS_APPLY_LABEL));
  private Element applyButtonLabel = Element.byXpath(RESET_BUTTON_LABEL_PATTERN +
      "/following-sibling::div/*");
  private Element resetButtonLabel = Element.byXpath(RESET_BUTTON_LABEL_PATTERN +
      "/descendant::div[contains(@class,'uui-caption')]");
  private Element groupBlockInput = Element.byXpath(LABEL_PLACEHOLDER_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_GROUP_DROPDOWN_DEFAULT_VALUE));
  private Element groupBlockLabel = Element.byXpath(LABEL_PLACEHOLDER_PATTERN + LABEL_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_GROUP_DROPDOWN_DEFAULT_VALUE));
  private Element resetButton = Element.byXpath(BUTTON_PATTERN,
      getValueOf(USER_MANAGEMENT_RESET));
  private Element foundElementsLabel = Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN,
      getValueOf(PARTICIPANTS_ELEMENTS_FOUND_LABEL));
  private Element fullNameParticipantText = Element
      .byXpath("//div[@class='fullName']");
  private Element fullNameParticipantElements = Element.byXpath(
      "//div[div[div[div[@class='fullName']]]]");
  private Element firstStudentStatus = Element.byXpath(
      "(//div[@color]//div[@font-size]/div)[1]");
  private Element englishResultForFirstStudent = Element.byXpath(
      "//div[text()]/ancestor::div[@class='fullName']/../../div[3]/div/div/div[2]//*[contains(@class, 'text')]");
  private Element englishLevelForFirstStudent = Element.byXpath(
      "(//div[text()]/ancestor::div[@class='fullName']/../../div[3]/div/div/div[3]//*[contains(@class, 'text')])[1]");
  private Element surveyColumn = Element.byXpath(
      "(//div[contains(@class, 'table-header')]/div[3]//div/div/div)[1]");
  private Element englishTestColumn = Element.byXpath(
      "(//div[contains(@class, 'table-header')]/div[3]//div/div/div)[10]");
  private Element englishlevelColumn = Element.byXpath(
      "(//div[contains(@class, 'table-header')]/div[3]//div/div/div)[20]");
  private Element firstStudentRating = Element
      .byXpath("//div[text()]/ancestor::div[@class='fullName']/../../div[3]/div/div//a/div");
  private Element firstNameOfTheStudentInTheList = Element.byXpath(
      "(//div[text()]/ancestor::div[@class='fullName']//div[text()])[1]");
  private Element englishTestResultInUserProfile = Element.byXpath(
      TEXT_LOCATOR_PATTERN + "/following-sibling::div",
      getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE));
  private Element firstStudentCheckBox = Element.byXpath(
      "(//div[contains(@class, 'uui-checkbox')])[2]");
  private Element applicantStatusInChangeStatusWindow = Element.byXpath(
      "//div[@class='react-tiny-popover-container']//div[text()]");
  private Element englishLevelWindow = Element.byXpath(
      "//div[contains(@class,'-clickable')]//div[contains(@style,'min-width') and contains(@style,'auto')]/div/div/div");
  private Element changeStatusWindow = Element.byClassName("react-tiny-popover-container");
  private Element successfulStatusChangedPopUp = Element.byXpath(
      CHANGES_POP_UP_PATTERN,
      getValueOf(PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP));
  private Element statusPopUpCrossButton = Element.byXpath(
      "//div[@class='uui-snackbar-item-self']//div[contains(@class,'button')]//*[name()='svg']/..");
  private Element cogwheelButton = Element.byXpath(
      "//div[contains(text(), '%s')]/following::div/*[name()='svg']",
      getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT));
  private Element titlePopUpInApplicantStatus = Element.byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN+
      TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_APPLICANT_STATUS_TITLE_POP_UP));
  private Element initiatedByFieldPopUpInApplicantStatus = Element.byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_APPLICANT_STATUS_INITIATED_BY_POP_UP));
  private Element rejectionReasonPopUpInApplicantStatus = Element.byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_APPLICANT_STATUS_REJECTION_REASON_POP_UP));
  private Element cancelButtonPopUpInApplicantStatus = Element.byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_APPLICANT_STATUS_CANCEL_BUTTON_POP_UP));
  private Element OKButtonPopUpInApplicantStatus = Element.byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_APPLICANT_STATUS_OK_BUTTON_POP_UP));
  private Element editApplicantStatusButton = Element.byXpath(
      PARTICIPANT_COGWHEEL_CONTEXT_MENU_PATTERN,
      getValueOf(PARTICIPANTS_EDIT_STATUS_BUTTON));
  private Element moveApplicantButton = Element.byXpath(
      PARTICIPANT_COGWHEEL_CONTEXT_MENU_PATTERN,
      getValueOf(PARTICIPANTS_MOVE_BUTTON));
  private Element statusParticipantElement = Element
      .byXpath(
          "//div[@class='fullName']//following::*/div/div/div[6]//div[contains(@class, 'text')]");
  private Element selectApplicationStatusInput = Element.byXpath(FILTER_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE));
  private Element selectApplicationRatingInput = Element.byXpath(FILTER_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_RATING_DEFAULT_VALUE));
  private Element selectApplicationEnglishInput = Element.byXpath(FILTER_INPUT_PATTERN,
      getValueOf(PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT));
  private Element selectApplicationCountryInput = Element.byXpath(FILTER_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE));
  private Element selectApplicationCityInput = Element.byXpath(FILTER_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE));
  private Element selectRegistrationDateFromInput = Element.byXpath(REGISTRATION_DATE_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_FROM));
  private Element selectRegistrationDateToInput = Element.byXpath(REGISTRATION_DATE_INPUT_PATTERN,
      getValueOf(REACT_TRAINING_TASK_JOURNAL_TAB_CALENDAR_PLACEHOLDER_TO));
  private Element staffingDeskButton = Element
      .byXpath("//div[contains(@class, 'button')]//div[text()='Staffing Desk']");
  private Element addParticipantInput = Element
      .byXpath(
          "//div[contains(@class,'uui-input') and contains(@class, 'uui-placeholder') and contains(text(),'%s')]",
          getValueOf(PARTICIPANTS_ADD_PARTICIPANT_INPUT));
  private Element addParticipantInputSearchInput = Element
      .byXpath("//input[@placeholder='%s']", getValueOf(PARTICIPANTS_ADD_PARTICIPANT_INPUT_SEARCH));
  private Element addParticipantButton = Element.byXpath(BUTTON_PATTERN,
      getValueOf(PARTICIPANTS_ADD_PARTICIPANT_BUTTON));
  private Element fullNameColumnHeader = Element
      .byXpath(String.format("//div[contains(@class,'table')]//div[text()=\"%s\"]",
          getValueOf(USER_MANAGEMENT_FULL_NAME)));
  private Element emailColumnHeader = Element.byXpath(
      "//div[contains(@class,'table')]//div[text()='E-mail']");
  private Element publicTraineeAccountHeader = Element
      .byXpath("//div[contains(@class,'table')]//div[text()='%s']",
          getValueOf(PARTICIPANTS_PUBLIC_TRAINEE_ACCOUNT_COLUMN_HEADER));
  private Element applicantStatusFromFilterBlock = Element.byXpath(
      "//div[contains(@class, 'uui-input-box')]//div[@class='uui-caption']");
  private Element checkedStudentList = Element.byXpath(
      "//div[contains(@class, 'checked')]/ancestor::div[contains(@class,'table-row-container')]//div[@class='fullName']//div[text()]");
  private Element participantPopUpProfileButton = Element
      .byXpath("(//a[@rel='noopener noreferrer'])[2]");
  private Element editButton = Element
      .byXpath("(//div[contains(@class,'table-header-row')]//*[name()='svg'])[last()]");
  private Element configureColumnsTabHeader = Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN,
      getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_HEADER));
  private Element configureColumnsTabCheckAllButton = Element.byXpath(
      ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_ALL_BUTTON));
  private Element configureColumnsTabUncheckAllButton = Element.byXpath(
      ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_UNCHECK_ALL_BUTTON));
  private Element configureColumnsTabApplyButton = Element.byXpath("(//div[text()='%s'])[2]",
      getValueOf(PARTICIPANTS_APPLY_LABEL));
  private Element configureColumnsTabButton = Element.byCss("path[fill*='ACE']");
  private Element horizontalScrollBar = Element.byClassName("uui-table-scroll-bar");
  private Element numberOfParticipants = Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_PAGINATION_TOTAL_LABEL));
  private Element shownElementsLabel = Element.byXpath(
      ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_PAGINATION_SHOW_LABEL));
  private Element paginationOnLeftSide = Element.byXpath("(//div[text()='2']/../..//div//div)[2]");
  private Element paginationOnRightSide = Element
      .byXpath("(//div[text()='2']/../..//div//div)[last()]");
  private Element studentDDL = Element.byXpath("(//div[contains(@style,'scroll')])[last()]");
  private Element paginationDDLElements = Element.byXpath(EXCEL_CHOICE_POP_UP_PATTERN
      + "//div[contains(@class,'clickable')]//*//div//div//div//div//div");
  private Element paginationDDLButton = Element
      .byXpath(
          "(//div[contains(@class,'input-box')]//div//div[contains(@class,'uui-icon')])[last()]");
  private Element statusChangePopUpButton = Element
      .byXpath(
          "(//div[contains(@class, 'uui-modal-window')]//div[contains(@class, 'uui-caption')])[last()]");
  private Element modalWindow = Element
      .byXpath("//div[contains(@class, 'uui-modal-window')]/div/div");
  private Element crossButtonPopUpInApplicantStatus  = Element
      .byXpath("//*[name()='use' and @*[contains(.,'close')]]");
  private Element infoIcon = Element
      .byXpath(String.format("(%s//*[name()='svg'])[2]", CONFIGURE_COLUMNS_POP_UP_PATTERN));
  private Element informationMessage = Element.byXpath(String.format(
      ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_INFORMATION_MESSAGE)));
  private Element configureColumnsPopUpCheckbox = Element
      .byXpath(CONFIGURE_COLUMNS_POP_UP_PATTERN + "//div[@id]");
  private Element configureColumnsPopUpCheckBoxValue = Element.byXpath(String.format(
      CONFIGURE_COLUMNS_POP_UP_PATTERN + "//div[@font-size]/div[not (contains(text(),'%s'))]",
      getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_INFORMATION_MESSAGE)));
  private Element homeIcon = Element.byXpath("//a[@href='/']/div");
  private Element confirmActionPopUpBody = Element.byXpath(
      "//div[contains(@class, 'modal-window')]/div[2]//div[text()]");
  private Element participantsSelectedLabel = Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN,
      getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT));
  private Element bulkCheckbox = Element.byXpath(ESCAPED_TEXT_SEARCH_PATTERN
          + "/ancestor::div[contains(@class,'uui-table-header-row')]//div[@class='uui-checkbox']",
      getValueOf(USER_MANAGEMENT_FULL_NAME));
  private Element tooltip = Element.byXpath("//div[@class='uui-tooltip-body']");
  private Element epamButtonPopUpInApplicantStatus = Element.byXpath(
      "//div[contains(@class, 'uui-modal-window')]//div[contains(@class, 'uui-caption')][1]");
  private Element candidateButtonPopUpInApplicantStatus = Element.byXpath(
      "//div[contains(@class, 'uui-modal-window')]//div[contains(@class, 'uui-caption')][text()='CANDIDATE']");
  private Element rejectionReasonInput = Element.byXpath(
      "(//div[contains(@class,'uui-input-box')])[last()]");
  private Element listOfCountryInDDL = Element.byXpath("//div[@class = 'uui-popper']");
  private final Element assignACButton = Element.byXpath(BUTTON_PATTERN,
      getValueOf(PARTICIPANTS_ASSIGN_AC_BUTTON));
  private final Element assignEnglishTestButton = Element.byXpath(BUTTON_PATTERN,
      ASSIGN_ENGLISH_TEST);
  private final Element notificationPopUp = Element.byXpath(
      "//div[@class='uui-snackbar-item-self']");
  private final Element addParticipantInputValue = Element.byXpath("(//div[contains(@class,'uui-input-box')]"
      + "//div[contains(@class,'uui-input') and not(contains(@class, 'placeholder'))])[1]");
  private final Element searchInputClearButton = Element.byXpath(
      "//div[@id='groups-search-bar']//div[contains(@class,'uui-icon')]");
  private final Element successfullyAddedParticipantPopUp = Element.byXpath(CHANGES_POP_UP_PATTERN,
      getValueOf(PARTICIPANTS_SUCCESSFULLY_ADDED_PARTICIPANT_POP_UP));
  private final Element paginationArrowButton = Element.byXpath(
      "//div[contains(@class,'uui-input ') and not(contains(@class,'placeholder'))]"
          + "//ancestor::div[contains(@class,'uui-input-box')]//div[contains(@class,'uui-icon')]");
  private final Element checkAllCheckbox = Element.byXpath("(//div[@class='uui-checkbox'])[1]");
  private Element listCheckboxPopupScreen = Element.byXpath
          ("//div[contains(@class,'uui-modal-window')]//div[contains(@class, 'uui-checkbox')]");
  private Element listNameOfColumnsPopupScreen = Element.byXpath
          ("//div[contains(@class , 'uui-modal-window')]/div[3]/div//div[@display = 'grid']/div");
  private Element applyPopUpScreenButton = Element.byXpath("//div[contains(@class, 'uui-modal-window')]//div[text()='%s']",
          getValueOf(PARTICIPANTS_APPLY_LABEL));
  private Element listNameOfTableColumns = Element.byXpath("//div[contains(@class, 'uui-table-header-row')]//div[@display='grid']/div");
 private Element confirmationToasterText = Element.byXpath("//div[contains(@class, 'uui-snackbar-item-self')]/div/div/div[2]/div");
 private Element crossButtonPopUp = Element.byXpath("//div[@class='uui-snackbar-item-self']//div[contains(@class,'-clickable')]/div");
  private Element studentEmail = Element.byXpath(
      "//div[contains(@class,'container') and contains(@class,'uui-table-row ')]//div[contains(@class,'scrolled')]//div[8]/div/div/div");
  private Element cityFromCityDropDown = Element.byXpath(
      "//div[contains(@class,'-clickable')]//div[contains(@style,'flex-basis') and contains(@style,'auto')]/div/div/div");
  private Element chosenCountryFromCountryDropDown = Element.byXpath(
      "//div[contains(@class,'uui-button-box')]/div[@class='uui-caption']");
  private Element yesButtonInPopUpWindow = Element.byXpath(ELEMENT_BY_TEXT_PATTERN, "Yes");
  private Element confirmedPopUpWindow = Element.byXpath(ELEMENT_BY_TEXT_PATTERN,
      SUCCESSFUL_STATUS);
  private Element assignedStatusField = Element.byXpath(ELEMENT_BY_TEXT_PATTERN,
      "Assigned");
  private final String INPUT_BY_LABEL_PATTERN = ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN +
      "/../../../following-sibling::div/div[1]/div[1]/div";
  private Element confirmActionPopUp = Element.byXpath(
      ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN, "Confirm action");
  private String labelText = "English level";
  private Element englishLevelLabel = Element.byXpath(
      String.format(ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN, labelText));
  private Element englishLevelInput = Element.byXpath(
      String.format(INPUT_BY_LABEL_PATTERN, labelText));
  private Element participantsCountry = Element.byXpath(
      "(//div[contains(@style, 'top')])[1]//following-sibling::div[3]//div[3]//div[4]");
  private Element statusesInGeneralInformationTab = Element
      .byXpath("//*[@id='app']//div[contains(text(),'General information')]/../following-sibling::div/div[@display]");
  private final Element detailsTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB));
  private final Element subscribersToExcelButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(PARTICIPANTS_SUBSCRIBERS_TO_EXCEL_BUTTON));

  @Override
  public boolean isScreenLoaded() {
    return studentsFullName.isDisplayed();
  }

  public List<String> getAllStatusParticipants() {
    return statusParticipantElement.getElementsText();
  }

  public ReactParticipantsTrainingScreen clickEditButton() {
    editButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen waitConfigureColumnsTabHeaderVisibility() {
    configureColumnsTabHeader.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen clickConfiguresColumnsTabCheckAllButton() {
    configureColumnsTabCheckAllButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickConfiguresColumnsTabUncheckAllButton() {
    configureColumnsTabUncheckAllButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickConfigureColumnsTabApplyButton() {
    configureColumnsTabApplyButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickConfigureColumnsButton() {
    configureColumnsTabButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickAfterScrollBarToTheCenter() {
    horizontalScrollBar.mouseOverCoordinatesAndClick(horizontalScrollBar.getWidth() / 6, 0);
    return this;
  }

  public ReactParticipantsTrainingScreen clickAfterScrollBarToTheEnd() {
    horizontalScrollBar.mouseOverCoordinatesAndClick(horizontalScrollBar.getWidth() / 3 - 2, 0);
    return this;
  }

  public ReactStudentCardPopUpScreen clickStudentByName(String name) {
    Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN, name).click();
    return new ReactStudentCardPopUpScreen();
  }

  public ReactParticipantsTrainingScreen waitSpinnerOfLoadingInvisibility() {
    spinnerOfLoading.waitForDisappear();
    return this;
  }

  public ReactParticipantsTrainingScreen waitScreenLoading() {
    waitSpinnerOfLoadingInvisibility();
    firstStudentCheckBox.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen waitChangeStatusWindowVisibility() {
    changeStatusWindow.waitForVisibility();
    return this;
  }

  public String getApplicantStatusByParticipantName(String studentName) {
    return Element.byXpath(STUDENT_STATUS_PATTERN, studentName).getText();
  }

  public ReactParticipantsTrainingScreen typeSearchPhraseToSearchInput(String searchPhrase) {
    searchInput.clearInputUsingBackspace();
    searchInput.type(searchPhrase);
    return this;
  }

  public ReactParticipantsTrainingScreen clickApplyButton() {
    applyButton.waitForClickableAndClick();
    return this;
  }

  public int getNumberOfFoundedParticipants() {
    String numberOfElements = foundElementsLabel.getText();
    return Integer.parseInt(numberOfElements.substring(numberOfElements.lastIndexOf(" ") + 1));
  }

  public String getFoundElementsLabelText() {
    String label = foundElementsLabel.getText();
    return label.substring(0, label.indexOf(":") + 1);
  }

  public String getApplyButtonLabelText() {
    return applyButtonLabel.getText();
  }

  public ReactParticipantsTrainingScreen clickApplyButtonlabel() {
    applyButtonLabel.click();
    return this;
  }

  public String getResetButtonLabelText() {
    return resetButtonLabel.getText();
  }

  public int getNumberOfParticipantsFromTab() {
    return Integer.parseInt(numberOfParticipants.getText().replaceAll("\\D+", ""));
  }

  public ReactParticipantsTrainingScreen clickFirstStudentStatus() {
    firstStudentStatus.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickFirstStudentCheckBox() {
    firstStudentCheckBox.clickJs();
    return this;
  }

  public List<String> getApplicantStatusInChangeStatusWindowText() {
    return applicantStatusInChangeStatusWindow.getElementsText();
  }

  public ReactParticipantsTrainingScreen clickApplicantStatusInChangeStatusWindowByName(
      String applicantName) {
    Element.byXpath(APPLICANT_STATUS_PATTERN_BY_NAME_FROM_DROP_DOWN_LIST, applicantName).waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickApplicantStatusInChangeStatusWindowByIndex(
      int index) {
    Element.byXpath(APPLICANT_STATUS_PATTERN, index).waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickEnglishLevelInWindowByIndex(
      int index) {
    Element.byXpath(ENGLISH_LEVEL_PATTERN,index).click();
    return this;
  }

  public ReactParticipantsTrainingScreen waitSuccessfulStatusChangedPopUpVisibility() {
    successfulStatusChangedPopUp.waitForVisibility();
    waitScreenLoading();
    return this;
  }

  public String getFirstParticipantApplicantStatus() {
    return firstStudentStatus.getText();
  }

  public ReactParticipantsTrainingScreen waitFirstParticipantStatusToBe(String status) {
    firstStudentStatus.waitTextToBePresentInElement(status);
    return this;
  }

  public ReactParticipantsTrainingScreen clickStatusPopUpCrossButton() {
    statusPopUpCrossButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickCogwheelButton() {
    cogwheelButton.waitForClickableAndClick();
    return this;
  }

  public boolean isCogwheelButtonEnabled() {
    return cogwheelButton.isEnabled();
  }

  public ReactParticipantsTrainingScreen clickEditApplicantStatusButton() {
    editApplicantStatusButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickMoveApplicantButton() {
    moveApplicantButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationStatusInput() {
    selectApplicationStatusInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationRatingInput() {
    selectApplicationRatingInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationEnglishInput() {
    selectApplicationEnglishInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationCountryInput(){
    selectApplicationCountryInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickEnglishTestColumn() {
    englishTestColumn.click();
    waitSpinnerOfLoadingInvisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen clickEnglishLevelColumn() {
    englishlevelColumn.click();
    waitSpinnerOfLoadingInvisibility();
    return this;
  }

  public String getEnglishTestResultInUserProfileText() {
    firstNameOfTheStudentInTheList.clickJs();
    return englishTestResultInUserProfile.getText();

  }

  public String getEnglishResultForFirstStudenText() {
    return englishResultForFirstStudent.getText();
  }

  public String getEnglishlevelForFirstStudentText() {
    return englishLevelForFirstStudent.getText();
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationCityInput(){
    selectApplicationCityInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen selectSortingStudentStatusByIndex(int index) {
    Element.byXpath(String.format(APPLICATION_STATUS_FROM_DROP_DOWN_XPATH_PATTERN,
        getValueOf(HEADER_CONTAINER_SEARCH), index)).click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickSearchByNameInput() {
    searchInput.clickJs();
    return this;
  }

  public ReactParticipantsTrainingScreen typeSearchByNameInput(String selectedName) {
    searchInput.click();
    searchInput.type(selectedName);
    searchInputClearButton.waitForVisibility();
    return this;
  }

  public boolean isParticipantFindByNameDisplayed(String selectedName) {
    return Element.byXpath(String.format(FIND_PARTICIPANT_BY_NAME_PATTERN, selectedName))
        .isDisplayed();
  }

  public List<String> getParticipantNames() {
    return fullNameParticipantElements.getElementsText();
  }

  public ReactParticipantsTrainingScreen waitParticipantsTableElementsForVisibility() {
    fullNameParticipantText.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen mouseOverFoundElementsLabel() {
    foundElementsLabel.mouseOver();
    return this;
  }

  public String getParticipantNameTextByIndex(int index) {
    return Element.byXpath(STUDENT_NAME_BY_INDEX_PATTERN, index).getText();
  }

  public List<Element> getParticipantNamesList() {
    return fullNameParticipantText.getElements();
  }

  public String getApplicantStatusText() {
    return applicantStatusFromFilterBlock.getText();
  }

  public ReactParticipantsTrainingScreen clickOnApplicantStatus() {
    applicantStatusFromFilterBlock.click();
    return this;
  }

  public boolean isTitlePopUpInApplicantStatusDisplayed() {
    return titlePopUpInApplicantStatus.isDisplayed();
  }

  public boolean isInitiatedByFieldPopUpInApplicantStatusDisplayed() {
    return initiatedByFieldPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isRejectionReasonPopUpInApplicantStatusDisplayed() {
    return rejectionReasonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isCancelButtonPopUpInApplicantStatusDisplayed() {
    return cancelButtonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isOKButtonPopUpInApplicantStatusDisplayed() {
    return OKButtonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isCandidateButtonPopUpInApplicantStatusDisplayed() {
    return candidateButtonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isEpamButtonPopUpInApplicantStatusDisplayed() {
    return epamButtonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isEnglishResultsColumnDisplayed() {
    return englishResultForFirstStudent.isDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isRatingColumnDisplayed() {
    return firstStudentRating.isDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isSurveyColumnDisplayed() {
    return surveyColumn.isDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isShownElementsLabelDisplayed() {
    return shownElementsLabel.isDisplayed();
  }

  public String getStudentEnglishTestResultText(String studentName) {
    return Element.byXpath(ENGLISH_TEST_RESULT_OF_STUDENT_XPATH_PATTERN, studentName).getText();
  }

  public String getStudentRatingText(String studentName) {
    return Element.byXpath(RATING_OF_STUDENT_XPATH_PATTERN, studentName).getText();
  }

  public boolean isSearchInputDisplayed() {
    return searchInput.isDisplayed();
  }

  public boolean isSearchIconDisplayed() {
    return searchIcon.isDisplayed();
  }

  public boolean isFilterBlockInputDisplayed(String name) {
    return Element.byXpath(String.format(FILTER_INPUT_PATTERN, name)).isDisplayed();
  }

  public boolean isGroupBlockInputDisplayed() {
    return groupBlockInput.isDisplayed();
  }

  public String getGroupLabelText() {
    return groupBlockLabel.getText();
  }

  public String getFilterBlockLabelText(String name) {
    return Element.byXpath(String.format(ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN + LABEL_PATTERN, name)).getText();
  }

  public String getRegistrationDateLabelText() {
    return registrationDateLabel.getText();
  }

  public boolean isSelectRegistrationDateFromInputDisplayed() {
    return selectRegistrationDateFromInput.isDisplayed();
  }

  public boolean isSelectRegistrationDateToInputDisplayed() {
    return selectRegistrationDateToInput.isDisplayed();
  }

  public boolean isFoundElementsLabelDisplayed() {
    return foundElementsLabel.isDisplayed();
  }

  public boolean isApplyButtonDisplayed() {
    return applyButton.isDisplayed();
  }

  public boolean isResetButtonDisplayed() {
    return resetButton.isDisplayed();
  }

  public ReactParticipantsTrainingScreen clickRestButton(){
    resetButton.waitForClickableAndClick();
    return this;
  }

  public boolean isInformationBlockButtonDisplayed(String name) {
    return Element.byXpath(String.format(BUTTON_PATTERN, name)).isDisplayed();
  }

  public boolean isExcelButtonDisplayed() {
    return excelButton.isDisplayed();
  }

  public boolean isStaffingDeskButtonDisplayed() {
    return staffingDeskButton.isDisplayed();
  }

  public boolean isEmailColumnHeaderDisplayed() {
    return emailColumnHeader.isDisplayed();
  }

  public boolean isFullNameColumnHeaderDisplayed() {
    return fullNameColumnHeader.isDisplayed();
  }

  public String getFullNameColumnHeaderText() {
    return fullNameColumnHeader.getText();
  }

  public boolean isAddParticipantInputDisplayed() {
    return addParticipantInput.isDisplayed();
  }

  public ReactParticipantsTrainingScreen typeTextInAddParticipantInput(String name) {
    addParticipantInput.click();
    addParticipantInputSearchInput.waitForClickableAndClick();
    addParticipantInputSearchInput.type(name);
    return this;
  }

  public ReactParticipantsTrainingScreen clickItemInDropDownByName(String name) {
    Element.byXpath(ITEM_BY_DROPDOWN_IN_SEARCH, name).click();
    return this;
  }

  public boolean isStudentDDLDisplayed() {
    return studentDDL.isDisplayed();
  }

  public ReactParticipantsTrainingScreen clickSelectStudentDropDown(String studentName) {
    Element.byXpath(String.format(ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN, studentName))
        .waitForPresence()
        .click();
    return this;
  }

  public List<String> getTextOfElementsInTableOrDDL() {
    return listOfCountryInDDL.getElementsText();
  }

  public List<String> getAllCountriesAndCitiesOfParticipants() {
    return participantsCountry.getElementsText();
  }

  public ReactParticipantsTrainingScreen clickAddParticipantButton() {
    addParticipantButton.click();
    return this;
  }

  public boolean isAddParticipantButtonDisplayed() {
    return addParticipantButton.isDisplayed();
  }

  public boolean isParticipantTableHeadersDisplayed(String name) {
    Element tableHeader = Element.byXpath(String.format(PARTICIPANT_TABLE_HEADERS_PATTERN, name));
    tableHeader.mouseOver();
    return tableHeader.isDisplayed();
  }

  public String getParticipantTableHeaderText(String name) {
    return Element.byXpath(String.format(PARTICIPANT_TABLE_HEADERS_PATTERN, name)).getText();
  }

  public boolean isPublicTraineeAccountHeaderDisplayed() {
    publicTraineeAccountHeader.mouseOver();
    return publicTraineeAccountHeader.isDisplayed();
  }

  public boolean isPaginationOnLeftSideDisplayed() {
    return paginationOnLeftSide.isDisplayed();
  }

  public boolean isPaginationOnRightSideDisplayed() {
    return paginationOnRightSide.isDisplayed();
  }

  public String getPlanId() {
    return getStringValueByRegex("/\\d+/", getCurrentWindowUrl())
        .replaceAll("/", "");
  }

  public ReactParticipantsTrainingScreen clickExcelButton() {
    excelButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen waitExcelChoicePopUpVisibility() {
    excelChoicePopUp.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen clickDownloadDocumentsArchiveButton() {
    downloadDocumentsArchiveButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickDownloadParticipantsListButton() {
    downloadParticipantsListButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickDownloadSurveyResultsButton() {
    downloadSurveyResultsButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen waitForConfirmActionWindowVisibility() {
    confirmActionWindow.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen clickConfirmActionButton() {
    confirmActionButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickOnSeveralStudentsCheckboxElements(
      int numberOfStudents) {
    Element.byXpath(CHECKBOX_BY_STUDENT_NAME_PATTERN, "")
        .getElements()
        .stream()
        .limit(numberOfStudents)
        .forEach(Element::clickOnInvisibleElement);
    return this;
  }

  public List<String> getCheckedStudentsNamesList() {
    return checkedStudentList.getElements().stream()
        .map(Element::getText)
        .collect(Collectors.toList());
  }

  public boolean isParticipantHasSurveyIcon(String participantName) {
    return Element.byXpath(String.format(SURVEY_ICON_BY_NAME_PATTERN,
        participantName)).isDisplayed();
  }

  public ReactSurveyPopUpScreen clickSurveyIconOfParticipantByName(String participantName) {
    Element.byXpath(String.format(SURVEY_ICON_BY_NAME_PATTERN, participantName)).click();
    return new ReactSurveyPopUpScreen();
  }

  public String getFirstStudentRatingText() {
    return firstStudentRating.getText();
  }

  public UserQuestionaryScreen clickParticipantByName(String userName) {
    Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN, userName).mouseOver();
    Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN, userName).waitForClickableAndClick();
    return new UserQuestionaryScreen();
  }

  public ProfileScreen clickParticipantPopUpProfileButton() {
    participantPopUpProfileButton.click();
    return new ProfileScreen();
  }

  public List<String> getPaginationDDLElementsText() {
    return paginationDDLElements.getElementsText();
  }

  public ReactParticipantsTrainingScreen clickPaginationDDLButton() {
    paginationDDLButton.waitForClickableAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickStatusChangePopUpButton() {
    statusChangePopUpButton.click();
    return this;
  }

  public boolean isConfigureColumnsPopUpHeaderDisplayed() {
    return configureColumnsTabHeader.isDisplayed();
  }

  public boolean isCrossButtonPopUpInApplicantStatusDisplayed() {
    return crossButtonPopUpInApplicantStatus.isDisplayed();
  }

  public boolean isCrossButtonPopUpInApplicantStatusClickable() {
    return crossButtonPopUpInApplicantStatus.isClickable();
  }

  public boolean isInfoIconDisplayed() {
    return infoIcon.isDisplayed();
  }

  public boolean isInformationMessageDisplayed() {
    return informationMessage.isDisplayed();
  }

  public boolean isConfigureColumnsPopUpApplyButtonDisplayed() {
    return configureColumnsTabApplyButton.isDisplayed();
  }

  public boolean isConfigureColumnsPopUpApplyButtonClickable() {
    return configureColumnsTabApplyButton.isClickable();
  }

  public boolean isConfigurationPopUpButtonDisplayedByName(String buttonName) {
    return Element.byXpath(String.format(ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN, buttonName)).isDisplayed();
  }

  public boolean isConfigurationPopUpButtonClickableByName(String buttonName) {
    return Element.byXpath(String.format(ReactDetailTrainingScreen.TEXT_LOCATOR_PATTERN, buttonName)).isClickable();
  }

  public int getNumberOfCheckBoxesInConfigurationPopUp() {
    return configureColumnsPopUpCheckbox.getElements().size();
  }

  public List<String> getConfigurationPopUpCheckboxValues() {
    return configureColumnsPopUpCheckBoxValue.getElementsText();
  }

  public boolean isHomeIconClickable() {
    return homeIcon.isClickable();
  }

  public HeaderScreen clickHomeIcon() {
    homeIcon.click();
    return new HeaderScreen();
  }

  public boolean isDownloadSurveyResultsButtonDisplayedNoWait() {
    return downloadSurveyResultsButton.isDisplayedNoWait();
  }

  public String getConfirmActionWindowBodyText() {
    return confirmActionPopUpBody.getText();
  }

  public int getNumberOfParticipantsFromTable() {
    return fullNameParticipantText.getElements().size();
  }

  public boolean isStudentCVByStudentNameDisplayed(String studentName) {
    return Element.byXpath(STUDENT_CV_PATTERN, studentName).isDisplayedShortTimeOut();
  }

  public ReactParticipantsTrainingScreen clickStudentCVByStudentName(String studentName) {
    Element.byXpath(STUDENT_CV_PATTERN, studentName).click();
    return this;
  }

  public int getNumberOfSelectedParticipantsFromLabel() {
    return Integer.parseInt(participantsSelectedLabel.getText().split(": ")[1]);
  }

  public ReactParticipantsTrainingScreen selectAllParticipantsInTable() {
    bulkCheckbox.clickJs();
    return this;
  }

  public int getNumberOfSelectedCheckboxesInTable() {
    return checkedStudentList.getElements().size();
  }

  public int getNumberOfAllStudentsFromTable() {
    return getParticipantNamesList().size();
  }

  public boolean areAllStudentsCheckboxesChecked() {
    return getNumberOfSelectedCheckboxesInTable() == getNumberOfAllStudentsFromTable();
  }

  public boolean isStudentCheatingIconDisplayed(String studentName) {
    return Element.byXpath(STUDENT_CHEATING_ICON_PATTERN, studentName).isDisplayedShortTimeOut();
  }

  public String getCheatingIconTooltipText(String studentName) {
    return Element.byXpath(STUDENT_CHEATING_ICON_PATTERN, studentName).getTooltipText(tooltip);
  }

  public ReactParticipantsTrainingScreen clickStudentCheckBoxByStudentName(String studentName) {
    Element.byXpath(CHECKBOX_BY_STUDENT_NAME_PATTERN, studentName).click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickEpamButtonPopUpInApplicantStatus() {
    epamButtonPopUpInApplicantStatus.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickInputRejectionReason() {
    rejectionReasonInput.click();
    return this;
  }

  public ReactParticipantsTrainingScreen chooseRejectionReason(String rejectionReason) {
    Element.byXpath(TEXT_LOCATOR_PATTERN, rejectionReason).click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickGroupByNumber(String groupNumber){
    Element.byXpath(PARTICIPANT_COGWHEEL_CONTEXT_MENU_PATTERN, groupNumber)
        .mouseOverAndClick();
    return this;
  }

  public ReactParticipantsTrainingAssignACPopUpScreen clickAssignACButton() {
    assignACButton.waitForClickableAndClick();
    return new ReactParticipantsTrainingAssignACPopUpScreen();
  }

  public ReactParticipantsTrainingScreen clickAssignEnglishTestButton() {
    assignEnglishTestButton.waitForClickableAndClick();
    return new ReactParticipantsTrainingScreen();
  }

  public String getNotificationPopUpText() {
    return notificationPopUp.getText();
  }

  public ReactParticipantsTrainingScreen clickConfigureColumnsCheckBoxByName(String checkBoxName) {
    Element.byXpath(CONFIGURE_COLUMNS_POP_UP_CHECK_BOX_PATTERN, checkBoxName)
        .mouseOverAndClick();
    return this;
  }

  public boolean isSuccessfulChangesPopUpDisplayed() {
    return successfulStatusChangedPopUp.isDisplayed();
  }

  public boolean isStudentsGroupNumberDisplayed(String studentName, String groupNumber) {
    return Element.byXpath(GROUP_OF_STUDENT_PATTERN,
        studentName, groupNumber).isDisplayed();
  }

  public boolean isCogwheelListOfOptionsDisplayed() {
    return moveApplicantButton.isDisplayed();
  }

  public boolean isStatusPopUpCrossButtonDisplayed() {
    return statusPopUpCrossButton.isDisplayed();
  }

  public boolean isSelectApplicationDisplayed(String application) {
    return Element.byXpath(ELEMENT_BY_TEXT_PATTERN,
        application).isDisplayed();
  }

  public int getSizeOfListColumnsConfigurationPopUp() {
    return listNameOfColumnsPopupScreen.getElements().size();
  }

  public ReactParticipantsTrainingScreen clickCheckboxFromConfigurationPopUp(int columnsNumber) {
    listCheckboxPopupScreen
        .getElements()
        .get(columnsNumber)
        .mouseOverAndClick();
    return this;
  }

  public ReactParticipantsTrainingScreen clickApplyPopUpScreenButton() {
    applyPopUpScreenButton.click();
    return this;
  }

  public int getSizeListOfColumnsFromTable() {
    return listNameOfTableColumns.getElements().size();
  }

  public String getNameColumnOfTable(int numberColumn) {
    return listNameOfTableColumns.getElements()
        .get(numberColumn)
        .getText();
  }

  public String getConfirmationToasterText() {
    return confirmationToasterText.getText();
  }

  public ReactParticipantsTrainingScreen moveColumnToAnotherPosition(int numberColumnSource, int numberColumnTarget) {
    List<Element> columns = listNameOfTableColumns.getElements();
    columns.get(numberColumnSource).drugAndDropMouse(columns.get(numberColumnTarget));
    return this;
  }

  public ReactParticipantsTrainingScreen clickCrossButtonPopUpInApplicantStatus() {
    crossButtonPopUpInApplicantStatus.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickCrossButtonPopUp() {
    crossButtonPopUp.click();
    crossButtonPopUp.waitForInvisibility();
    return this;
  }

  public List<String> getSearchResultStudentsEmails() {
    return studentEmail.getElementsText();
  }

  public List<String> getCitiesFromCityDropDown() {
    return cityFromCityDropDown.getElementsText();
  }

  public String getChosenCountryNameFromCountryDropDown() {
    return chosenCountryFromCountryDropDown.getText();
  }

  public boolean isEnglishLevelInputDisplayed() {
    return englishLevelInput.isDisplayed();
  }

  public ReactParticipantsTrainingScreen clickSelectApplicationEnglishLevelInput() {
    englishLevelInput.click();
    return this;
  }

  public List<String> getEnglishLevelWindowText() {
    return englishLevelWindow.getElementsText();
  }

  public boolean isEnglishLevelLabelDisplayed() {
    return englishLevelLabel.isDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public ReactParticipantsTrainingScreen clickYesInEnglishTestPopUpWindow() {
    yesButtonInPopUpWindow.click();
    return this;
  }

  public boolean isEnglishTestConfirmedMessageDisplayed() {
    return confirmedPopUpWindow.isDisplayed();
  }

  public boolean isAssignedStatusFieldDisplayed() {
    return assignedStatusField.isDisplayed();
  }

  public boolean isConfirmActionPopUpDisplayed() {
    return confirmActionPopUp.isDisplayed();
  }

  public String getSearchResultText() {
    return participantsSelectedLabel.getText();
  }

  public boolean isFirstStudentCheckboxChecked() {
    return firstStudentCheckBox.getWrappedWebElement().getAttribute(ATTRIBUTE_CLASS).contains(ATTRIBUTE_CHECKED);
  }

  public ReactParticipantsTrainingScreen waitApplicantStatusUpdate(String participantName,
      String expectedStatus) {
    int attemptsAmount = 8;
    for (int i = 0; i < attemptsAmount; i++) {
      clickRefreshButton();
      if (isApplicantStatusDisplayed(participantName, expectedStatus, LONG_TIME_OUT_IN_SECONDS)) {
        return this;
      }
    }
    throw new TimeoutException(String.format("Applicant status is not updated to '%s', actual "
        + "status: '%s'", expectedStatus, getApplicantStatusByParticipantName(participantName)));
  }

  private boolean isApplicantStatusDisplayed(String participantName, String applicantStatus,
      int timeOut) {
    return Element.byXpath(APPLICANT_STATUS_PATTERN_BY_NAME, participantName, applicantStatus)
        .isDisplayed(timeOut);
  }

  public ReactParticipantsTrainingScreen clickFirstStudentInAddParticipantDDLByPartialName(
      String partParticipantName) {
    Element.byXpath(FIRST_STUDENT_IN_ADD_PARTICIPANT_DDL_BY_PARTIAL_NAME, partParticipantName)
        .waitForPresence()
        .click();
    return this;
  }

  public String getAddParticipantInputValue() {
    return addParticipantInputValue.getText();
  }

  public ReactParticipantsTrainingScreen waitSuccessfullyAddedParticipantPopUpVisibility() {
    successfullyAddedParticipantPopUp.waitForVisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen clickPaginationArrowButton() {
    scrollToBottom();
    paginationArrowButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen chooseParticipantsLimit(String participantsLimit) {
    Element.byXpath(PARTICIPANTS_LIMIT_IN_DDL_PATTERN, participantsLimit).click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickCheckAllCheckbox() {
    checkAllCheckbox.click();
    return this;
  }

  public String getSelectedParticipantsCountText() {
    return Element.byXpath(TEXT_LOCATOR_PATTERN,
            getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT)).getText();
  }

  public List<String> getStatusesFromGeneralInformationTab() {
    return statusesInGeneralInformationTab.getElementsText();
  }

  public ReactDetailTrainingScreen clickDetailsTab() {
    detailsTab.click();
    return new ReactDetailTrainingScreen();
  }

  public boolean isParticipantHeaderColumnDisplayed(String columnName) {
    Element headerColumn = Element.byXpath(
        String.format(PARTICIPANT_TABLE_HEADERS_PATTERN, columnName));
    if (!headerColumn.isDisplayedNoWait()) {
      clickAfterScrollBarToTheEnd();
    }
    return headerColumn.isDisplayedShortTimeOut();
  }

  public boolean isSubscribersToExcelButtonClickable() {
    return subscribersToExcelButton.isClickable(SHORT_TIME_OUT_IN_SECONDS);
  }
}
