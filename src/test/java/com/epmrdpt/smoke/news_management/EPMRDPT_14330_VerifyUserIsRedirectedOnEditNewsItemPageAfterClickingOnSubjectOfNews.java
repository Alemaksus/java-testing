package com.epmrdpt.smoke.news_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.EditNewsItemScreen;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14330_VerifyIfUserIsRedirectedOnEditNewsItemPageAfterClickingOnSubjectOfNews",
    groups = {"full", "news_management", "smoke"})
public class EPMRDPT_14330_VerifyUserIsRedirectedOnEditNewsItemPageAfterClickingOnSubjectOfNews {

  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14330_VerifyUserIsRedirectedOnEditNewsItemPageAfterClickingOnSubjectOfNews(
      User user) {
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
  public void checkEditNewsItemPageLoading() {
    new NewsManagementScreen().waitSearchResultsLoading().clickRandomNewsItemSubject();
    assertTrue(new EditNewsItemScreen().isScreenLoaded(), "'Edit News Item' page isn't loaded!");
  }
}
