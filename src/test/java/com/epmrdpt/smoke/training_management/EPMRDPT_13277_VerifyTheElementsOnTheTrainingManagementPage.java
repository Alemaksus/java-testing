package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_APPLY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CREATOR_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_ELEMENTS_FOUND_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_EXACT_MATCH_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_FORMAT_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_FORMAT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_HIDE_ADVANCED_SEARCH_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_OPEN_ADVANCED_SEARCH_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_OWNER_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PRICING_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PRICING_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PROGRAM_LEVEL_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PROGRAM_LEVEL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_RESET_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SKILL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SUBSCRIBERS_LIST_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TYPE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_CREATE_NEW;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_PLANNING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_TO_EXCEL;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactFooterScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen.ColumnHeaders;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13277_VerifyTheElementsOnTheTrainingManagementPage",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13277_VerifyTheElementsOnTheTrainingManagementPage {

  private static final String ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN = "%s of pagination block isn't displayed!";

  private User user;
  private ReactHeaderScreen reactHeaderScreen;
  private ReactFooterScreen reactFooterScreen;
  private SoftAssert softAssert;
  private ReactTrainingManagementScreen reactTrainingManagementScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13277_VerifyTheElementsOnTheTrainingManagementPage(User user) {
    this.user = user;
  }

  @DataProvider(name = "provider of label names and default values if dropdown")
  public Object[][] providerOfFilterBlockLabelNamesAndDefaultDropdownValues() {
    return new Object[][]{
        {getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_CITY_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_EXACT_MATCH_LABEL),
            null},
        {getValueOf(REACT_TRAINING_MANAGEMENT_TYPE_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL),
            null},
        {getValueOf(REACT_TRAINING_MANAGEMENT_SKILL_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_SKILL_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_PRICING_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_PRICING_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_FORMAT_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_FORMAT_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_PROGRAM_LEVEL_LABEL),
            getValueOf(REACT_TRAINING_MANAGEMENT_PROGRAM_LEVEL_DROPDOWN_DEFAULT_VALUE)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_ELEMENTS_FOUND_LABEL),
            null}
    };
  }

  @DataProvider(name = "provider of button names")
  public Object[][] buttonNamesProvider() {
    return new Object[][]{
        {getValueOf(REACT_TRAINING_MANAGEMENT_APPLY_BUTTON)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_RESET_BUTTON)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_HIDE_ADVANCED_SEARCH_BUTTON)},
        {getValueOf(TRAINING_MANAGEMENT_CREATE_NEW)},
        {getValueOf(TRAINING_MANAGEMENT_TO_EXCEL)},
        {getValueOf(REACT_TRAINING_MANAGEMENT_SUBSCRIBERS_LIST_BUTTON)}
    };
  }


  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactHeaderScreen = new ReactHeaderScreen();
    reactTrainingManagementScreen = new ReactTrainingManagementScreen();
    reactFooterScreen = new ReactFooterScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    new HeaderScreen()
        .clickReactTrainingManagementLink()
        .waitFilterBlockDisplayed()
        .waitPreloadingPictureHidden();
    new ReactTrainingManagementService()
        .searchTrainingByNameByKeepingPagination("AutoTest");
  }

  @Test(priority = 1)
  public void checkThatHeaderDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactHeaderScreen.isHeaderContainerDisplayed(),
        "Header container isn't displayed!");
    softAssert.assertTrue(reactHeaderScreen.isEpamLogoDisplayed(),
        "Logo on header isn't displayed!");
    softAssert.assertTrue(reactHeaderScreen.isAllNavigationTabsDisplayed(),
        "Not all navigation tabs are displayed on header!");
    softAssert.assertAll();
  }

  @Test(dataProvider = "provider of button names", priority = 1)
  public void checkAllButtonsOnFilterBlockAreDisplayed(String buttonName) {
    assertTrue(reactTrainingManagementScreen.isButtonByNameDisplayed(buttonName),
        String.format("'%s' button isn't displayed!", buttonName));
  }

  @Test(priority = 1)
  public void checkThatPlanningTitleDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isPlanningTitleDisplayed(),
        "Planning title isn't displayed!");
    softAssert.assertEquals(reactTrainingManagementScreen.getPlanningTitleElementText(),
        LocaleProperties.getValueOf(TRAINING_MANAGEMENT_PLANNING),
        "Planning title Text is wrong!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkThatSearchByTrainingNameFieldDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isSearchingByTrainingFieldDisplayed(),
        "Search input field isn't displayed!");
    softAssert.assertTrue(reactTrainingManagementScreen.isSearchIconDisplayed(),
        "Search icon isn't displayed!");
    reactTrainingManagementScreen.typeTrainingName("training");
    softAssert.assertTrue(
        reactTrainingManagementScreen.isClearSearchInputButtonDisplayed(),
        String.format("'%s' button isn't displayed!", "clear search"));
    softAssert.assertAll();
  }

  @Test(dataProvider = "provider of label names and default values if dropdown", priority = 2)
  public void checkThatAllLabelsAndDropdownsOnFilterBlockDisplayed(String name,
      String defaultValue) {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isLabelOnFilterBlockByNameDisplayed(name),
        String.format("Label '%s' isn't displayed on filter block!", name));
    if (defaultValue != null) {
      softAssert.assertTrue(
          reactTrainingManagementScreen.isDropDownOnFilterBlockByNameDisplayed(name),
          String.format("Dropdown menu '%s' isn't displayed on filter block!", name));
      softAssert.assertEquals(reactTrainingManagementScreen.getValueFromDropdownFilterByName(name),
          defaultValue,
          String.format("Filter '%s' doesn't contain default value!", name));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatExactMatchSwitchIsDisplayed() {
    assertTrue(reactTrainingManagementScreen.isExactMatchSwitchDisplayed(),
        "'Exact match' switch isn't displayed!");
  }

  @Test(priority = 3)
  public void checkThatDateSearchFiltersIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isDateFromFilterDisplayed(),
        "Date filter 'From' isn't displayed!");
    softAssert.assertTrue(reactTrainingManagementScreen.isDateToFilterDisplayed(),
        "Date filter 'To' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatCheckBoxesIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isCreatorCheckboxDisplayed(),
        String.format("'%s' checkbox isn't displayed!",
            getValueOf(REACT_TRAINING_MANAGEMENT_CREATOR_CHECKBOX)));
    softAssert.assertTrue(reactTrainingManagementScreen.isOwnerCheckboxDisplayed(),
        String.format("'%s' checkbox isn't displayed!",
            getValueOf(REACT_TRAINING_MANAGEMENT_OWNER_CHECKBOX)));
  }

  @Test(priority = 3)
  public void checkThatTableOfTrainingsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isTrainingTableHeaderContainerDisplayed(),
        "The header container of training table isn't displayed!");
    softAssert.assertTrue(reactTrainingManagementScreen.isTrainingTableContainerDisplayed(),
        "The training table container isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatTableOfTrainingsContainsRequiredColumns() {
    softAssert = new SoftAssert();
    for (ColumnHeaders header : ColumnHeaders.values()) {
      softAssert.assertTrue(
          reactTrainingManagementScreen.isColumnHeaderDisplayed(header),
          String.format("'%s' column header isn't displayed!", header.getName()));
    }
    softAssert.assertTrue(reactTrainingManagementScreen.isSbrColumnHeaderDisplayed(),
        "'SBR' column header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkPaginationBlockIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTrainingManagementScreen.isPaginationContainerDisplayed(),
        String.format(ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN,
            "Pagination container"));
    softAssert.assertTrue(reactTrainingManagementScreen.isShowLabelOfPaginationBlockDisplayed(),
        String.format(ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN, "Label 'Show'"));
    softAssert.assertTrue(reactTrainingManagementScreen.isElementsPerPageDropDownDisplayed(),
        String.format(ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN,
            "Elements per page dropdown"));
    softAssert.assertTrue(reactTrainingManagementScreen.isTotalLabelOfPaginationBlockDisplayed(),
        String.format(ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN,
            "Label 'elements. Total'"));
    softAssert.assertTrue(reactTrainingManagementScreen.isPaginationButtonsDisplayed(),
        String.format(ELEMENT_OF_PAGINATION_BLOCK_NOT_DISPLAYED_MESSAGE_PATTERN,
            "Pagination buttons"));
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatFooterIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactFooterScreen.isFooterContainerDisplayed(),
        "Footer container isn't displayed!");
    softAssert.assertTrue(reactFooterScreen.isEpamLogoDisplayed(),
        "'Epam' logo isn't displayed on footer!");
    softAssert.assertTrue(reactFooterScreen.isAllNavigationLinksDisplayed(),
        "Some navigation links on footer isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatElementsFoundTitleIsDisplayed() {
    Assert.assertTrue(reactTrainingManagementScreen.isElementsFoundTitleDisplayed(),
        "'Elements found' title isn't displayed!");
  }

  @Test(priority = 4)
  public void checkAdvancedSearchButtonAfterClickOnIt() {
    reactTrainingManagementScreen.clickAdvancedSearchButton();
    Assert.assertEquals(reactTrainingManagementScreen.getAdvancedSearchButtonText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_OPEN_ADVANCED_SEARCH_BUTTON),
        "'Advanced search' button text after click isn't equal to the expected one!");
  }
}
