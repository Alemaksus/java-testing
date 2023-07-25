package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_ID_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_SCREEN_FINISHED_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_SCREEN_FORMATION_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_SCREEN_LEARNING_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_CHOOSE_THIS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_EXPECTED_FREQUENCY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_HOW_TO_GET_STARTED_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_REQUIRED_SKILLS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_TRAINING_DETAILS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_TRAINING_PROGRAM_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_USEFUL_LINKS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_ADDITIONAL_SKILLS_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_COURSE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_TITLE;
import static com.epmrdpt.services.LanguageSwitchingService.getLocaleLanguage;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxBySkills.ANDROID;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxBySkills.ATLASSIAN;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxBySkills.AUTOMATED_TESTING;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxByType.ENGLISH;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxByType.FACE_TO_FACE;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxByType.FREE_PRICING;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxByType.ONLINE;
import static com.epmrdpt.services.ReactTrainingManagementService.CheckboxByType.TRAINING_TYPE;

import com.epmrdpt.bo.Training;
import com.epmrdpt.bo.TrainingForCopy;
import com.epmrdpt.bo.TrainingForSearch;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import com.epmrdpt.screens.GroupDetailsScreen;
import com.epmrdpt.screens.ReactAutoreplyTabScreen;
import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.screens.ReactDetailTrainingCalendarScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.screens.ReactModalPageFromReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ReactTrainingManagementService {

  private final String TITLE_TAG = "Title";
  private final String DESCRIPTION_TAG = "Description";
  private final String KEYWORDS_TAG = "Keywords";

  private ReactDetailTrainingCalendarScreen reactDetailTrainingCalendarScreen = new ReactDetailTrainingCalendarScreen();
  private ReactDetailTrainingCalendarService reactDetailTrainingCalendarService =
      new ReactDetailTrainingCalendarService();
  private ReactTrainingManagementScreen reactTrainingManagementScreen = new ReactTrainingManagementScreen();
  private ReactAutoreplyTabScreen reactAutoreplyTabScreen = new ReactAutoreplyTabScreen();
  private ReactDetailTrainingScreen reactDetailTrainingScreen = new ReactDetailTrainingScreen();
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
  private ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen
      = new ReactSurveyAndTestingTabScreen();
  private ReactGroupScreen reactGroupScreen = new ReactGroupScreen();
  private TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
  private GroupDetailsScreen groupDetailsScreen = new GroupDetailsScreen();
  private ReactGroupDetailsScreen reactGroupDetailsScreen = new ReactGroupDetailsScreen();
  private ReactDescriptionTabScreen reactDescriptionTabScreen = new ReactDescriptionTabScreen();
  private ReactDetailTrainingService reactDetailTrainingService = new ReactDetailTrainingService();
  protected final WebDriver webDriver = WebDriverSingleton.getInstance().getDriver();

  public enum CheckboxByType {
    TRAINING_TYPE(0),
    FACE_TO_FACE(6),
    ONLINE(7),
    FREE_PRICING(9),
    ENGLISH(13),
    BASIC(17);

    private final int index;

    CheckboxByType(int index) {
      this.index = index;
    }
  }

  public enum CheckboxBySkills {
    ANDROID(1),
    ATLASSIAN(2),
    AUTOMATED_TESTING(3);

    private final int index;

    CheckboxBySkills(int index) {
      this.index = index;
    }
  }

  public ReactDetailTrainingScreen searchTrainingByNameAndRedirectOnIt(String trainingName) {
    return searchTrainingByName(trainingName)
        .clickTrainingNameByName(trainingName)
        .waitDataLoading();
  }

  public ReactTrainingManagementScreen searchTrainingByName(String trainingName) {
    return searchTrainingByNameByKeepingPagination(trainingName)
        .clickMaxElementsPerPageInDropDownIfDisplayedNoWait()
        .waitAllSpinnersAreNotDisplayed();
  }

  public ReactTrainingManagementScreen searchTrainingByNameByKeepingPagination(
      String trainingName) {
    return reactTrainingManagementScreen
        .waitTrainingScreenIsLoaded()
        .typeTrainingName(trainingName)
        .uncheckCheckBoxes()
        .clickCancelFilterByDateButtonIfNeeded()
        .waitSearchResultsLabelIsDisplayed()
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed();
  }

  public ReactTrainingManagementScreen searchTrainingByCountry(String countryName) {
    return reactTrainingManagementScreen
        .waitPreloadingPictureHidden()
        .clickTrainingCountryDropDown()
        .fillCountryDropDownInput(countryName)
        .clickTrainingCountryDropDownByCountryName(countryName)
        .uncheckCheckBoxes()
        .clickCancelFilterByDateButtonIfNeeded()
        .clickApplyButton()
        .waitPreloadingPictureHidden()
        .waitAllTrainingNameDisplayed();
  }

  public ReactGroupDetailsScreen searchReactGroupByNameAndRedirectOnIt(String groupName) {
    reactDetailTrainingScreen
        .clickGroupsTabs()
        .clickRefreshButton();
    return reactGroupScreen
        .waitDataLoading()
        .clickGroupByName(groupName)
        .waitAllSpinnersAreNotDisplayed();
  }

  public ReactGroupDetailsScreen searchReactClassByNameAndRedirectOnIt(String className) {
    reactDetailTrainingScreen
        .clickGroupsTabs()
        .clickRefreshButton();
    return reactGroupScreen
        .waitAddClassButtonVisibility()
        .clickGroupByName(className)
        .waitAllSpinnersAreNotDisplayed();
  }

  public void checkStatusEditCheckBoxAndSwitchOnIfUnchecked() {
    if (!reactAutoreplyTabScreen.isToolBarIsPresent()) {
      reactAutoreplyTabScreen.clickEditCheckBox();
    }
  }

  public ReactDetailTrainingScreen fillRequiredFieldsForNewTrainingAndCreateIt(
      TrainingForCopy training) {
    return reactGeneralInfoTabScreen
        .waitScreenLoading()
        .waitLanguageButtonForVisibility()
        .clickTrainingTypeDropDown()
        .selectTrainingTypeByName(training.getTrainingType())
        .typeTrainingName(training.getName())
        .clickTrainingFormatDropDown()
        .selectTrainingFormatByName(training.getFormat())
        .setTrainingCountryByName(training.getCountry())
        .setTrainingCityByName(training.getCity())
        .setFirstTrainingOwner()
        .setTrainingSkillByName(training.getSkill())
        .selectTrainingEnrollmentTypeByName(training.getEnrollmentType())
        .setTrainingProgramLanguageByName(training.getProgramLanguage())
        .setTrainingProgramLevelByName(training.getProgramLevel())
        .setEndlessCheckBox()
        .typePlannedStudentsCountField(training.getStudentsCount())
        .setTrainingPricingByName(training.getPricing())
        .waitScreenLoading()
        .clickCreateButton();
  }

  public void deleteRussianLocalizationIfPresent() {
    if (reactDetailTrainingScreen.isRussianLanguageFromDDLButtonIsDisplayed()) {
      reactDetailTrainingScreen
          .clickCrossOnRussianLocalizationButton()
          .waitWarningMessageVisibility()
          .waitForClickableAndClickConfirmationButton();
      new ReactModalPageFromReactDetailTrainingScreen().waitModalWindowClosed();
      reactDetailTrainingScreen.clickSaveChangesButton();
    }
  }

  public void deleteACLinkedToTheTrainingIfPresent() {
    if (reactSurveyAndTestingTabScreen.isColumnDisplayed(
        getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_ID_LABEL))) {
      reactSurveyAndTestingTabScreen
          .clickBusketButton()
          .clickConfirmationDeleteButton();
    }
  }

  public ReactDetailTrainingScreen setTrainingStatusByText(String trainingStatus) {
    if (!reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(trainingStatus)) {
      reactDetailTrainingScreen
          .waitScreenLoaded()
          .clickTrainingStatusButtonByText(trainingStatus)
          .waitChangeStatusPopUpVisibility()
          .clickConfirmationOfChangingStatusButton()
          .waitNotificationPopUpVisibility()
          .closeNotificationPopUp();
    }
    return reactDetailTrainingScreen;
  }

  public void checkUseEnglishResultsCheckBoxAndSwitchOffIfChecked() {
    if (reactSurveyAndTestingTabScreen.isEnglishLevelTitleDisplayed()) {
      reactSurveyAndTestingTabScreen.clickUseEnglishResultsCheckbox();
    }
  }

  public GroupDetailsScreen addTrainerToGroup(String trainerName) {
    return groupDetailsScreen
        .clickSelectTrainerInput()
        .typeSelectTrainerInput(trainerName)
        .clickResultFromSearchByName(trainerName)
        .clickAddTrainerButton();
  }

  public GroupDetailsScreen addStudentInGroup(String studentName) {
    return groupDetailsScreen
        .waitDataLoading()
        .clickSelectParticipantDropDown()
        .clickStudentNameInSelectParticipantDropDownByName(studentName)
        .clickAddStudentButton();
  }

  public ReactGroupDetailsScreen addStudentInReactGroup(String studentName) {
    return reactGroupDetailsScreen
        .waitDataLoading()
        .chooseStudentNameInSelectParticipantDropDownByName(studentName)
        .clickAddParticipantButton()
        .waitResultOfChangeMessageDisplayed()
        .clickStatusPopUpCrossButton();
  }

  public void deleteGroupByIndex(int groupIndex) {
    reactGroupScreen
        .clickDeleteButtonByIndex(groupIndex)
        .clickRemoveConfirmButtonButton()
        .waitNumberGroupNameToBeLess(groupIndex + 1);
  }

  public void deleteAllStudentsFromGroup() {
    for (int attemptNumber = 3; attemptNumber > 0; attemptNumber--) {
      groupDetailsScreen
          .clickCheckboxSelectAllStudents()
          .waitForCogwheelIconVisibility()
          .clickCogwheelIcon()
          .clickDeleteLabelInCogwheelIcon()
          .clickDeleteButtonInPopUp();
      try {
        groupDetailsScreen.waitForStudentsInvisibility();
        break;
      } catch (TimeoutException ignored) {
        if (groupDetailsScreen.isResultOfChangeMessagePresent()) {
          groupDetailsScreen.waitResultOfChangeMessageInvisibility();
        }
      }
    }
  }

  public void switchLangForCurrentLocale() {
    String langName = new TrainingCenterManagementService()
        .getLanguageTabName(getLocaleLanguage());
    reactDetailTrainingScreen.clickLanguageTab(langName);
  }

  public void deleteStudentFromGroup(String studentName) {
    groupDetailsScreen
        .clickDeleteIconByStudentName(studentName)
        .clickDeleteButtonInPopUp()
        .clickRefreshButton();
  }

  public void deleteStudentFromReactGroup(String studentName) {
    reactGroupDetailsScreen
        .clickDeleteIconByStudentName(studentName)
        .clickDeleteButtonInPopUp()
        .waitResultOfChangeMessageDisplayed()
        .clickStatusPopUpCrossButton();
  }

  public void changeGroupStatusOnFinished() {
    groupDetailsScreen.waitSpinnerLoadingInvisibility();
    if (!groupDetailsScreen.getCurrentStatus().
        contains(LocaleProperties.getValueOf(GROUP_SCREEN_FINISHED_CURRENT_STATUS))) {
      groupDetailsScreen.clickFinishedButton()
          .clickOkButtonFromModalPopup()
          .waitFinishedButtonVanish()
          .waitLearningButtonDisplayed();
    }
  }

  public void changeGroupStatusOnLearning() {
    if (!groupDetailsScreen.getCurrentStatus().
        contains(LocaleProperties.getValueOf(GROUP_SCREEN_LEARNING_CURRENT_STATUS))) {
      changeGroupStatusOnFormation();
      addOneStudentToGroupIfNoOnePresent()
          .clickLearningButton()
          .waitForPopupVisibility()
          .clickOkButtonFromModalPopup();
      groupDetailsScreen.waitDataLoading();
    }
  }

  public void changeGroupStatusOnFormation() {
    if (!groupDetailsScreen.getCurrentStatus().
        contains(LocaleProperties.getValueOf(GROUP_SCREEN_FORMATION_CURRENT_STATUS))) {
      groupDetailsScreen.clickFormationButton()
          .clickOkButtonFromModalPopup()
          .waitFormationButtonInvisibility();
    }
  }

  public GroupDetailsScreen addOneStudentToGroupIfNoOnePresent() {
    groupDetailsScreen.waitScreenLoading();
    if (groupDetailsScreen.isNoStudentsInGroupPresent()) {
      groupDetailsScreen
          .clickAddManyStudentsButton()
          .waitForPopupVisibility()
          .waitDataLoading()
          .clickFirstStudentNameCheckboxSelectParticipantsPopup()
          .clickAddButtonInPopUp()
          .waitDataLoading()
          .waitForStudentsVisibility();
    }
    return groupDetailsScreen;
  }

  public GroupDetailsScreen setGroupNameAndGraduation(String groupName, String graduation) {
    return groupDetailsScreen
        .waitGroupNameTextToBePresent()
        .typeNewGroupName(groupName)
        .typeNewGroupGraduation(graduation)
        .clickSaveChangesButton()
        .waitResultOfChangeMessageDisplayed();
  }

  public ReactGroupDetailsScreen setReactGroupNameAndDates(String groupName, LocalDate startDate,
      LocalDate endDate) {
    reactGroupDetailsScreen
        .waitGroupNameTextToBePresent()
        .typeNewGroupName(groupName)
        .clickStartDateInputField();
    setConcreteDate(startDate)
        .clickEndDateInputField();
    return setConcreteDate(endDate)
        .clickSaveChangesButton()
        .waitResultOfChangeMessageDisplayed();
  }

  public ReactGroupDetailsScreen setReactGroupName(String groupName) {
    return reactGroupDetailsScreen
        .waitGroupNameTextToBePresent()
        .typeNewGroupName(groupName)
        .clickSaveChangesButton()
        .waitResultOfChangeMessageDisplayed();
  }

  public ReactGroupDetailsScreen setConcreteDate(LocalDate date) {
    return openRequiredMonth(date)
        .clickDayOfMonthDateButton(date.getDayOfMonth())
        .waitDateCalendarCollapsed();
  }

  public TrainingBlockScreen selectMoreSearchCheckboxesByType() {
    trainingBlockScreen
        .clickByTypesCheckboxByIndex(TRAINING_TYPE.index)
        .clickByTypesCheckboxByIndex(FACE_TO_FACE.index)
        .clickByTypesCheckboxByIndex(FREE_PRICING.index)
        .clickByTypesCheckboxByIndex(ONLINE.index);
    return trainingBlockScreen;
  }

  public TrainingBlockScreen selectEnglishCheckboxByType() {
    trainingBlockScreen.clickByTypesCheckboxByIndex(ENGLISH.index);
    return trainingBlockScreen;
  }

  public void deleteTrainerFromTrainersSectionIfNeed(String TRAINER_NAME) {
    if (reactGroupDetailsScreen.isTrainerByNameDisplayed(TRAINER_NAME)) {
      reactGroupDetailsScreen
          .clickDeleteIconByTrainerName(TRAINER_NAME)
          .clickDeleteButtonInPopUp()
          .clickRefreshButton();
    }
  }

  public TrainingBlockScreen selectAutomatedTestingCheckboxBySkills() {
    trainingBlockScreen.clickBySkillsCheckboxByIndex(AUTOMATED_TESTING.index);
    return trainingBlockScreen;
  }

  public TrainingBlockScreen selectSomeNecessarySkills() {
    trainingBlockScreen
        .clickBySkillsCheckboxByIndex(ANDROID.index)
        .clickBySkillsCheckboxByIndex(ATLASSIAN.index)
        .clickBySkillsCheckboxByIndex(AUTOMATED_TESTING.index);
    return trainingBlockScreen;
  }

  public ReactDescriptionTabScreen selectTrainingProgram(String trainingProgram) {
    reactDescriptionTabScreen
        .clearTrainingProgram()
        .typeTrainingProgram(trainingProgram)
        .clickPaidConsultationInfoField();
    return reactDescriptionTabScreen;
  }

  public ReactGroupDetailsScreen openRequiredMonth(LocalDate startDateTraining) {
    int clicksQuantity = (int) ChronoUnit.MONTHS.between(startDateTraining, LocalDate.now());
    String dateValue = reactDetailTrainingCalendarScreen.getMonthAndYearDateText();
    for (int i = 0; i < Math.abs(clicksQuantity); i++) {
      if (clicksQuantity < 0) {
        reactDetailTrainingCalendarScreen.clickRightArrowDate();
      } else {
        reactDetailTrainingCalendarScreen.clickLeftArrowDate();
      }
      reactDetailTrainingCalendarScreen.waitGetMonthAndYearDateChanged(dateValue);
      dateValue = reactDetailTrainingCalendarScreen.getMonthAndYearDateText();
    }
    return new ReactGroupDetailsScreen();
  }

  public void fillInputsForSearchTraining(TrainingForSearch trainingForSearch) {
    reactTrainingManagementScreen
        .waitAllSpinnersAreNotDisplayed()
        .clickAdvancedSearchButtonIfNeeded()
        .clickCancelFilterByDateButtonIfNeeded()
        .clickTrainingCountryDropDown()
        .clickTrainingCountryDropDownByCountryName(trainingForSearch.getCountryName())
        .clickTrainingCityDropDownByCityName(trainingForSearch.getCityName())
        .clickTrainingTypeDropDownTypeName(trainingForSearch.getType())
        .clickTrainingDurationDropDownTypeName(trainingForSearch.getDuration())
        .clickTrainingFormatDropDownFormatName(trainingForSearch.getFormat())
        .clickCurrentStatusDropDownCurrentStatusName(trainingForSearch.getStatus())
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .waitAllTrainingNameDisplayed();
  }

  public void deleteAllParticipantsFromReactGroup() {
    reactGroupDetailsScreen
        .clickCheckBoxAllStudentsInParticipants()
        .clickSettingsInParticipantSectionButton()
        .clickDeleteAllParticipantsButton()
        .clickDeleteButtonInPopUp()
        .waitResultOfChangeMessageDisplayed()
        .clickRefreshButton();
  }

  public TrainingBlockScreen selectLevelLanguagePriceTypeCheckboxes(
      TrainingForSearch trainingForSearch) {
    trainingBlockScreen
        .clickByTypesCheckboxByName(trainingForSearch.getType())
        .clickByTypesCheckboxByName(trainingForSearch.getFormat())
        .clickByTypesCheckboxByName(trainingForSearch.getPrice())
        .clickByTypesCheckboxByName(trainingForSearch.getLanguage())
        .clickByTypesCheckboxByName(trainingForSearch.getLevel());
    return trainingBlockScreen;
  }

  public ReactGroupDetailsScreen searchTrainingByNameAndRedirectToGroup(String trainingName,
      String groupName) {
    searchTrainingByNameAndRedirectOnIt(trainingName);
    return searchReactGroupByNameAndRedirectOnIt(groupName);
  }

  public ReactGroupDetailsScreen searchTrainingByNameAndRedirectToClass(String trainingName,
      String className) {
    searchTrainingByNameAndRedirectOnIt(trainingName);
    return searchReactClassByNameAndRedirectOnIt(className);
  }

  public void changeTrainingStartDateToPresentAndClickPlaningTitle(String trainingName) {
    searchTrainingByNameAndRedirectOnIt(trainingName);
    reactDetailTrainingService.enterStartDate(LocalDate.now());
    reactDetailTrainingScreen.clickSaveChangesButton().clickPlanningTitle();
  }

  public Training getTraining() {
    Training training = new Training();
    training
        .withTrainingType(reactGeneralInfoTabScreen.getTrainingTypeValue())
        .withSkill(reactGeneralInfoTabScreen.getTrainingSkillValue())
        .withSkillDisplayToggle(reactGeneralInfoTabScreen.isToggleByNameChecked(
            REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT))
        .withAdditionsSkill(reactGeneralInfoTabScreen.getSelectedAdditionalSkills())
        .withAdditionsSkillDisplayToggle(
            reactGeneralInfoTabScreen.isToggleByNameChecked(
                REACT_GENERAL_INFO_TAB_SCREEN_ADDITIONAL_SKILLS_INPUT))
        .withFormat(reactGeneralInfoTabScreen.getTrainingFormatValue())
        .withEnrollmentType(reactGeneralInfoTabScreen.getTrainingEnrollmentTypeValue())
        .withPricing(reactGeneralInfoTabScreen.getTrainingPricingValue())
        .withPrice(reactGeneralInfoTabScreen.getTrainingPriceInputFieldValue())
        .withCurrency(reactGeneralInfoTabScreen.getTrainingCurrencyInputFieldValue())
        .withTax(reactGeneralInfoTabScreen.getTrainingValueAddedTaxInputFieldValue())
        .withProgramLevel(reactGeneralInfoTabScreen.getTrainingProgramLevelValue())
        .withProgramLanguage(reactGeneralInfoTabScreen.getSelectedProgramLanguages())
        .withName(reactGeneralInfoTabScreen.getTrainingNameText())
        .withDisplayedName(reactGeneralInfoTabScreen.getTextFromDisplayingNameInput())
        .withTargetAudience(reactGeneralInfoTabScreen.getTargetAudienceValue())
        .withSupervisors(reactGeneralInfoTabScreen.getSelectedSupervisors())
        .withLinkToExternalTraining(
            reactGeneralInfoTabScreen.getLinkToExternalTrainingValue());
    reactGeneralInfoTabScreen.clickDescriptionTabFromTrainingScreen();
    training
        .withMetaTagTitle(reactDescriptionTabScreen.getInputFieldValueByLabelName("Title"))
        .withMetaTagDescription(
            reactDescriptionTabScreen.getInputFieldValueByLabelName("Description"))
        .withMetaTagKeyword(reactDescriptionTabScreen.getInputFieldValueByLabelName("Keywords"))
        .withTrainingDetail(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_DETAILS_LABEL)))
        .withRequiredSkills(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_REQUIRED_SKILLS_LABEL)))
        .withWhatIfDoNotMeetRequirementsField(
            reactDescriptionTabScreen.getTextBlockContentByBlockName(
                getValueOf(REACT_DESCRIPTION_TAB_SCREEN_USEFUL_LINKS_LABEL)))
        .withChooseThisTrainingIfYouField(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_CHOOSE_THIS_LABEL)))
        .withTrainingProgram(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_TRAINING_PROGRAM_LABEL)))
        .withHowToJoinField(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_HOW_TO_GET_STARTED_LABEL)))
        .withPaidConsultationsField(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL)))
        .withRecommendedTrainingField(reactDescriptionTabScreen.getTextBlockContentByBlockName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL)))
        .withLinkToVideoField(reactDescriptionTabScreen.getVideoBlockContent())
        .withRecommendedTrainingDDLOptions(reactDescriptionTabScreen
            .getRecommendedTrainingsDdlOptions())
        .withExpectedFrequency(reactDescriptionTabScreen.getInputFieldValueByLabelName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_EXPECTED_FREQUENCY_LABEL)));
    reactGeneralInfoTabScreen.clickSurveyAndTestingTabFromTrainingScreen();
    training
        .withUseEnglishTestResultCheckBox(
            reactSurveyAndTestingTabScreen.isUseEnglishResultsCheckBoxChecked())
        .withMinimalEnglishLevel(reactSurveyAndTestingTabScreen.getInputFieldValueByLabelName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_TITLE)))
        .withELearnIntegration(reactSurveyAndTestingTabScreen.getInputFieldValueByLabelName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_COURSE_LABEL)));
    return training;
  }

  public ReactDescriptionTabScreen fillAllFieldsInTheUpperFieldsSectionByLocalization() {
    switch (System.getProperty("locale")) {
      case "en":
        reactDetailTrainingScreen.clickEnglishLanguageButton();
        break;
      case "ru":
        reactDetailTrainingScreen.clickRussianLanguageButton();
        break;
      case "ukr":
        reactDetailTrainingScreen.clickUkrainianLanguageButton();
        break;
    }
    fillAllFieldsInTheUpperFieldsSection();
    return new ReactDescriptionTabScreen();
  }

  public void fillAllFieldsInTheUpperFieldsSection() {
    reactDescriptionTabScreen.fillInputFieldByLabelNameWithRandomData(TITLE_TAG)
        .fillInputFieldByLabelNameWithRandomData(DESCRIPTION_TAG)
        .fillInputFieldByLabelNameWithRandomData(KEYWORDS_TAG)
        .fillInputFieldByLabelNameWithRandomData(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_EXPECTED_FREQUENCY_LABEL));
  }

  public ReactDetailTrainingScreen changeTrainingStartDateAndNotifyMembers(String trainingName,
      String commentText) {
    reactGeneralInfoTabScreen.clickOnStartDateInput();
    reactDetailTrainingService.enterStartDate(LocalDate.now());
    return reactDetailTrainingScreen.clickSaveChangesButton()
        .closeNotificationPopUp()
        .clickNotifyMembersButton()
        .waitScreenLoading()
        .typeComment(commentText)
        .clickStartDateCheckbox()
        .clickNewProgramCheckbox()
        .clickNotifyButton();
  }
}
