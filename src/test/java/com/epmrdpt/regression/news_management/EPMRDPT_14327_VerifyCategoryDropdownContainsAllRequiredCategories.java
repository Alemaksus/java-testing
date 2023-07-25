package com.epmrdpt.regression.news_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.*;
import static java.lang.String.format;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14327_VerifyCategoryDropdownContainsAllRequiredCategories",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14327_VerifyCategoryDropdownContainsAllRequiredCategories {

  private User user;
  private NewsManagementScreen newsManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14327_VerifyCategoryDropdownContainsAllRequiredCategories(User user) {
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
  public void checkCategoryDropdownContent() {
    SoftAssert softAssert = new SoftAssert();
    newsManagementScreen.clickCategoryDropDown().waitCategoryDDLDisplayed();
    for (String category : getCategoriesList()) {
      softAssert.assertTrue(newsManagementScreen.isCategoryDDLItemByNameDisplayed(category),
          format("'%s' option is not present in 'Category' dropdown!", category));
    }
    softAssert.assertAll();
  }

  private List<String> getCategoriesList() {
    List<String> categories = new ArrayList<>();
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_NEWS));
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_HARD_SKILLS));
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_SOFT_SKILLS));
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_REAL_STORIES));
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_SELF_STUDY));
    categories.add(LocaleProperties.getValueOf(NEWS_MANAGEMENT_CATEGORY_ENGLISH));
    return categories;
  }
}
