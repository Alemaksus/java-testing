package com.epmrdpt.regression.news_management;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14321_VerifyThatAllRequiredFieldsInSearchSectionContainHintByDefault",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14321_VerifyThatAllRequiredFieldsInSearchSectionContainHintByDefault {

  private NewsManagementScreen newsManagementScreen;
  private User user;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14321_VerifyThatAllRequiredFieldsInSearchSectionContainHintByDefault(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    newsManagementScreen = new NewsManagementScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkSearchBoxAndFindButton() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getPlaceHolderTextOfSearchBySubject(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SEARCH_BY_SUBJECT),
        "Search by subject placeholder isn't displayed correctly in search box!");
    softAssert.assertEquals(newsManagementScreen.getTextOnSearchFindButton(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_FIND),
        "Find button's text isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCategoryLabelAndDefaultValue() {
    softAssert = new SoftAssert();
    softAssert.assertAll();
    softAssert.assertEquals(newsManagementScreen.getCategoryLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY),
        "Category label text isn't displayed correctly!");
    softAssert.assertEquals(newsManagementScreen.getCategoryDropDownSelectedValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_CATEGORY),
        "Select category isn't displayed as default value for select dropdown!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCurrentStatusLabelAndItsValues() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getCurrentStatusLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_CURRENT_STATUS),
        "Current status label isn't displayed correctly!");
    List<String> currentStatusExpectedValues = new ArrayList<String>() {{
      add(LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_STATUS_ALL));
      add(LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_STATUS_DRAFT));
      add(LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_STATUS_PUBLISHED));
    }};
    softAssert.assertEquals(
        newsManagementScreen.clickStatusDropDown().getCurrentStatusDropDownValueTexts(),
        currentStatusExpectedValues, String.format(
            "Current status drop down doesn't contains %s!",
            currentStatusExpectedValues.toString()));
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkAuthorLabelAndDefaultValue() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getAuthorLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_AUTHOR),
        "Author label text isn't displayed correctly!");
    softAssert.assertEquals(newsManagementScreen.getAuthorDropDownSelectedValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_AUTHOR),
        "Author drop down default selected value isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkHashTagLabelAndDefaultValue() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getHashTagLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_HASHTAGS),
        "Hashtags label text isn't displayed correctly!");
    softAssert.assertEquals(newsManagementScreen.getHashTagDropDownSelectedValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_HASH_TAGS),
        "Hashtags dropdown default selected value isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkStartDateLabelAndItsPattern() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getStartDateLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_START_DATE),
        "Start date label text isn't displayed correctly!");
    softAssert.assertEquals(newsManagementScreen.getStartDateInputPlaceholderValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_DATE_PATTERN),
        "Start date date default format isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkEndDateLabelAndItsPattern() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(newsManagementScreen.getEndDateLabelText(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_END_DATE),
        "End date label text isn't displayed correctly!");
    softAssert.assertEquals(newsManagementScreen.getEndDateInputPlaceholderValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_DATE_PATTERN),
        "End date date default format isn't displayed correctly!");
    softAssert.assertAll();
  }
}
