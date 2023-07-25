package com.epmrdpt.regression.news_management;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.NewsItem;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.CreateNewsItemService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14325_VerifyThatNewsItemIsDeletedAfterClickingDeleteButton",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14325_VerifyThatNewsItemIsDeletedAfterClickingDeleteButton {

  private static final String NEWS_TITLE_FOR_SUPER_ADMIN = String
      .format("AutoTest_NewsItem_For_Deletion_For_Admin_%s", LocalDateTime.now());
  private static final String NEWS_TITLE_FOR_NEWS_CREATOR = String
      .format("AutoTest_NewsItem_For_Deletion_For_News_Creator_%s", LocalDateTime.now());
  private static final String NEWS_INTRODUCTION = "AutoTest NewsItem For Deletion Introduction";
  private static final String NEWS_DESCRIPTION = "AutoTest NewsItem For Deletion Description";

  private NewsManagementScreen newsManagementScreen;
  private NewsItem newsItem;
  private LocalDate todayDate;
  private User user;
  private String newsTitle;

  @Factory(dataProvider = "Provider of users with News Management tab")
  public EPMRDPT_14325_VerifyThatNewsItemIsDeletedAfterClickingDeleteButton(User user,
      String newsTitle) {
    this.user = user;
    this.newsTitle = newsTitle;
  }

  @DataProvider(name = "Provider of users with News Management tab")
  public static Object[][] provideUsers() {
    return new Object[][]{
        {UserFactory.asSuperAdmin(), NEWS_TITLE_FOR_SUPER_ADMIN},
        {UserFactory.asNewsCreator(), NEWS_TITLE_FOR_NEWS_CREATOR}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    todayDate = StringUtils.getTodayDate();
    newsManagementScreen = new NewsManagementScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickNewsManagementLink().waitScreenLoading();
    newsItem = new NewsItem();
    newsItem.setAllNewsItemData(
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_CATEGORY_NEWS),
        LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_STATUS_PUBLISHED),
        todayDate, newsTitle, NEWS_INTRODUCTION, NEWS_DESCRIPTION,
        newsTitle, NEWS_INTRODUCTION, NEWS_DESCRIPTION,
        newsTitle, NEWS_INTRODUCTION, NEWS_DESCRIPTION);
    new CreateNewsItemService().createNewsItemWithData(newsItem)
        .waitSaveNewsItemVisibility()
        .clickCancelNewsItemButton().waitScreenLoading()
        .clickRefreshButton();
  }

  @Test(priority = 1)
  public void checkNewsManagementScreenIsOpened() {
    assertTrue(newsManagementScreen.waitSearchResultsLoading().isScreenLoaded(),
        "News management page isn't opened!");
  }

  @Test(priority = 2)
  public void checkNewsItemHasBeenCreated() {
    newsManagementScreen.typeSearchBySubject(newsTitle).clickFindButton();
    assertTrue(newsManagementScreen.isNewsItemWithSubjectPresent(newsTitle),
        String.format(" %s news item isn't created!", newsTitle));
  }

  @Test(priority = 3)
  public void checkActionListOfNewsAppears() {
    assertTrue(newsManagementScreen.clickEllipsisActionMenuBySubjectName(newsTitle)
            .isActionMenuPopUpDisplayed(),
        "Action menu pop up isn't displayed!");
  }

  @Test(priority = 4)
  public void checkConfirmationPopUpAppears() {
    assertTrue(newsManagementScreen.clickDeleteAction().isConfirmationPopUpDisplayed(),
        "Confirmation pop up isn't displayed!");
  }

  @Test(priority = 5)
  public void checkNewsItemIsDeleted() {
    newsManagementScreen.clickOkOnConfirmationPopUp().clickRefreshButton();
    newsManagementScreen.waitSearchResultsLoading()
        .typeSearchBySubject(newsTitle).clickFindButton();
    assertFalse(newsManagementScreen.isNewsItemWithSubjectPresent(newsTitle),
        "News item isn't deleted!");
  }
}
