package com.epmrdpt.regression.news_management;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.util.List;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14322_VerifyThatApplyAndResetButtonsWorkCorrectly",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14322_VerifyThatApplyAndResetButtonsWorkCorrectly {

  private static final String EXPECTED_DATE = "2020-07-12";
  private static final String EXPECTED_DATE_INPUT_FIELD_VALUE = "";
  private static String categoryTypeName = LocaleProperties.getValueOf(
      LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_REAL_STORIES);
  private static String statusType = LocaleProperties.getValueOf(
      LocalePropertyConst.NEWS_MANAGEMENT_STATUS_PUBLISHED);
  private static final String HASH_TAG_NAME = "#JAVA";
  private static final String SUBJECT_NAME = "AutoTest_Celebration";
  private static final String EXPECTED_PUBLICATION_DATE = "12.07.2020";

  private User user;
  private NewsManagementScreen newsManagementScreen;
  private SoftAssert softAssert;
  private String username;
  private final String NEWS_CREATOR_USERNAME= UserFactory.asNewsCreator().getFirstName();
  private final String SUPER_ADMIN_USERNAME="AutoTest SuperAdmin";


  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14322_VerifyThatApplyAndResetButtonsWorkCorrectly(User user) { this.user = user;}

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    newsManagementScreen = new NewsManagementScreen();
    username=user.getFirstName().equals(NEWS_CREATOR_USERNAME) ? NEWS_CREATOR_USERNAME : SUPER_ADMIN_USERNAME;
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkApplyButtonFunctionalityWorkingProperly() {
    softAssert = new SoftAssert();
    newsManagementScreen
        .enterSearchBySubjectInput(SUBJECT_NAME)
        .clickCategoryDropDown()
        .clickCategoryInDropDownListByName(categoryTypeName)
        .clickStatusDropDown()
        .clickStatusInDropDownListByName(statusType)
        .clickHashTagsDropDown()
        .clickHashTagInDropDownListByName(HASH_TAG_NAME)
        .clickAuthorDropDown()
        .enterAuthorNameInputText(username)
        .clickAuthorNameInDropDownList()
        .enterStartDateInputText(EXPECTED_DATE)
        .enterEndDateInputText(EXPECTED_DATE)
        .clickApplyButton()
        .waitSearchResultsLoading();
    List<String> searchResultsCategoryColumn = newsManagementScreen
        .getSearchResultsByCategoryColumn();
    List<String> searchResultsStatusColumn = newsManagementScreen.getSearchResultsByStatusColumn();
    List<String> searchResultsAuthorColumn = newsManagementScreen.getSearchResultsByAuthorColumn();
    List<String> searchResultsPublicationDateColumn = newsManagementScreen
        .getSearchResultsByPublicationDateColumn();
    for (int indexOfRow = 0; indexOfRow < searchResultsCategoryColumn.size(); indexOfRow++) {
      softAssert.assertEquals(searchResultsCategoryColumn.get(indexOfRow), categoryTypeName,
          "Search results are not properly filtered with respect to category chosen!");
      softAssert.assertEquals(searchResultsStatusColumn.get(indexOfRow), statusType,
          "Search results are not properly filtered with respect to status chosen!");
      softAssert.assertEquals(searchResultsAuthorColumn.get(indexOfRow),
          username,
          "Search results are not properly filtered with respect to author chosen!");
      softAssert.assertEquals(searchResultsPublicationDateColumn.get(indexOfRow),
          EXPECTED_PUBLICATION_DATE,
          "Search results are not properly filtered with respect to publication date chosen!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkResetButtonFunctionalityWorkingProperly() {
    softAssert = new SoftAssert();
    newsManagementScreen.clickResetButton();
    softAssert.assertTrue(newsManagementScreen.isCategoryDropDownHintDisplayed(),
        "Category drop down did not reset!");
    softAssert.assertTrue(newsManagementScreen.isStatusDropDownHintDisplayed(),
        "Status drop down did not rest!");
    softAssert.assertTrue(newsManagementScreen.isAuthorDropDownHintDisplayed(),
        "Author drop down did not reset!");
    softAssert.assertTrue(newsManagementScreen.isHashTagsDropDownHintDisplayed(),
        "Hash tags drop down did not reset");
    softAssert.assertEquals(newsManagementScreen.getStartDateAttributeValue(),
        EXPECTED_DATE_INPUT_FIELD_VALUE,
        "Start date field did not reset!");
    softAssert.assertEquals(newsManagementScreen.getEndDateAttributeValue(),
        EXPECTED_DATE_INPUT_FIELD_VALUE,
        "End date field did not reset!");
    softAssert.assertEquals(newsManagementScreen.getSearchBySubjectInputTextValue(),
        SUBJECT_NAME, "Search by subject input field was reset!");
    softAssert.assertAll();
  }
}
