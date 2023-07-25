package com.epmrdpt.regression.news_management;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14323_VerifySearchBySubjectFunctionalityWorksCorrectly",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14323_VerifySearchBySubjectFunctionalityWorksCorrectly {

  private static final String SUBJECT_NAME = "Autotest";
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14323_VerifySearchBySubjectFunctionalityWorksCorrectly(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test
  public void checkSearchBySubjectFieldFunctionalityWorkingProperly() {
    NewsManagementScreen newsManagementScreen = new NewsManagementScreen();
    SoftAssert softAssert = new SoftAssert();
    List<String> subjectColumnInSearchResultsBeforePerformingSearchBySubject = newsManagementScreen
        .waitSearchResultsLoading()
        .getSearchResultsBySubjectColumn();
    List<String> subjectColumnInSearchResultsOnPerformingSearch = newsManagementScreen
        .enterSearchBySubjectInput(SUBJECT_NAME)
        .clickFindButton()
        .waitSearchResultsLoading()
        .getSearchResultsBySubjectColumn();
    for (String subjectName : subjectColumnInSearchResultsOnPerformingSearch) {
      softAssert.assertTrue(subjectName.toLowerCase().contains(SUBJECT_NAME.toLowerCase()),
          "Search results are not properly filtered with respect to subject entered!");
    }
    softAssert.assertEquals(
        newsManagementScreen
            .clearSearchBySubjectInput()
            .clickFindButton()
            .waitSearchResultsLoading()
            .getSearchResultsBySubjectColumn(),
        subjectColumnInSearchResultsBeforePerformingSearchBySubject,
        "Search results did not reset after clearing the 'Search by subject' input!");
    softAssert.assertAll();
  }
}
