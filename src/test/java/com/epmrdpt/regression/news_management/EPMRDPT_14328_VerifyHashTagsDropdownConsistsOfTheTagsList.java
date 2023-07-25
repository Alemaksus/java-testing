package com.epmrdpt.regression.news_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14328_VerifyHashTagsDropdownConsistsOfTheTagsList",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14328_VerifyHashTagsDropdownConsistsOfTheTagsList {

  private User user;
  private NewsManagementScreen newsManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14328_VerifyHashTagsDropdownConsistsOfTheTagsList(User user) {
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

  @Test
  public void checkHashTagsDropdownContent() {
    SoftAssert softAssert = new SoftAssert();
    newsManagementScreen.clickHashTagsDropDown().waitHashTagsDDLDisplayed();
    assertTrue(newsManagementScreen.getHashTagsCountInHashTagDropdown() > 0,
        "Hash-tags list is not present in hash-tags dropdown!");
    softAssert.assertAll();
  }
}
