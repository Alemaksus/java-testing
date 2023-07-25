package com.epmrdpt.regression.news_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14326_VerifyThatNewsItemStaysInTheNewsListAfterClickingCancelButton",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14326_VerifyThatNewsItemStaysInTheNewsListAfterClickingCancelButton {

  private NewsManagementScreen newsManagementScreen;
  private String newsItemSubjectText = "";
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14326_VerifyThatNewsItemStaysInTheNewsListAfterClickingCancelButton(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    newsManagementScreen = new NewsManagementScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(user);
  }

  @Test(priority = 1)
  public void checkNewsManagementScreenIsOpened() {
    assertTrue(
        new HeaderScreen().clickNewsManagementLink().waitSearchResultsLoading().isScreenLoaded(),
        "News management page isn't opened!");
  }

  @Test(priority = 2)
  public void checkActionListOfNewsAppears() {
    int randomIndexOfNewsDisplayed = new Random()
        .nextInt(newsManagementScreen.waitSearchResultsLoading().getPublicationDatesCount());
    newsItemSubjectText = newsManagementScreen
        .getNewsItemSubjectTextByIndex(randomIndexOfNewsDisplayed);
    newsManagementScreen.clickEllipsisActionMenuByIndex(randomIndexOfNewsDisplayed);
    assertTrue(newsManagementScreen.isActionMenuPopUpDisplayed(),
        "Action menu pop up isn't displayed!");
  }

  @Test(priority = 3)
  public void checkConfirmationPopUpAppears() {
    assertTrue(newsManagementScreen.clickDeleteAction().isConfirmationPopUpDisplayed(),
        "Confirmation pop up isn't displayed!");
  }

  @Test(priority = 4)
  public void checkNewsItemIsNotDeleted() {
    assertTrue(newsManagementScreen.clickCancelButtonOnConfirmationPopUp()
        .isNewsItemWithSubjectPresent(newsItemSubjectText), "News item is deleted!");
  }
}
