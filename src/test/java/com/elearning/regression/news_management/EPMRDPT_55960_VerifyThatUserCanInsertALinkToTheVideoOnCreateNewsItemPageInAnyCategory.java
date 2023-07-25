package com.epmrdpt.regression.news_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_HARD_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_NEWS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_REAL_STORIES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_SELF_STUDY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_SOFT_SKILLS;

import com.epmrdpt.bo.NewsItem;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.CreateNewsItemService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.lang.reflect.Method;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_55960_VerifyThatTheUserCanInsertALinkToTheVideoIntoLinkToVideoFieldOnCreateNewsItemPageInAnyCategory",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_55960_VerifyThatUserCanInsertALinkToTheVideoOnCreateNewsItemPageInAnyCategory {

  private final String NEWS_ITEM_LINK_TO_VIDEO = "https://www.youtube.com/watch?v=0";
  private final String NEWS_ITEM_TITLE = "AutoTest News with link to video";
  private final String NEWS_ITEM_INTRODUCTION = NEWS_ITEM_TITLE.concat(" - Introduction");
  private final String NEWS_ITEM_DESCRIPTION = NEWS_ITEM_TITLE.concat(" - Description");

  private User user;
  private NewsManagementScreen newsManagementScreen;
  private NewsItem newsItem;
  private LocalDate todayDate;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_55960_VerifyThatUserCanInsertALinkToTheVideoOnCreateNewsItemPageInAnyCategory(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    newsManagementScreen = new NewsManagementScreen();
    todayDate = StringUtils.getTodayDate();
    newsItem = new NewsItem();
    newsItem.setDateOfPublication(todayDate);
    newsItem.setTitleInEnglish(NEWS_ITEM_TITLE);
    newsItem.setDescriptionInEnglish(NEWS_ITEM_DESCRIPTION);
    newsItem.setIntroductionInEnglish(NEWS_ITEM_INTRODUCTION);
    newsItem.setLinkToVideo(NEWS_ITEM_LINK_TO_VIDEO);
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test(dataProvider = "Provider data for news categories")
  public void checkUserCanCreateNewsItemWithLinkToVideo(String category) {
    newsItem.setCategory(getValueOf(category));
    CreateNewsItemService.createNewsItemWithLinkToVideo(newsItem)
        .waitSaveNewsItemVisibility()
        .clickCancelNewsItemButton()
        .waitScreenLoading()
        .clickRefreshButton();
    newsManagementScreen.waitScreenLoading();
    Assert.assertTrue(newsManagementScreen.isNewsItemWithSubjectPresent(NEWS_ITEM_TITLE),
        String.format("News item with %s category didn't created!", getValueOf(category)));
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteNewsItem(Method method) {
    if (method.getName().equals("checkUserCanCreateNewsItemWithLinkToVideo")) {
      newsManagementScreen
          .clickEllipsisActionMenuBySubjectName(NEWS_ITEM_TITLE)
          .clickDeleteAction()
          .clickOkOnConfirmationPopUp()
          .waitScreenLoading();
    }
  }

  @DataProvider(name = "Provider data for news categories")
  public Object[] provideNewsCategories() {
    return new Object[]{
        NEWS_MANAGEMENT_CATEGORY_NEWS,
        NEWS_MANAGEMENT_CATEGORY_REAL_STORIES,
        NEWS_MANAGEMENT_CATEGORY_ENGLISH,
        NEWS_MANAGEMENT_CATEGORY_HARD_SKILLS,
        NEWS_MANAGEMENT_CATEGORY_SOFT_SKILLS,
        NEWS_MANAGEMENT_CATEGORY_SELF_STUDY
    };
  }
}
