package com.epmrdpt.regression.news_management;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14324_VerifyListOfActionsByClickingOnEllipsis",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14324_VerifyListOfActionsByClickingOnEllipsis {

  private NewsManagementScreen newsManagementScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    newsManagementScreen = new NewsManagementScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.asNewsCreator())
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkActionsInEllipsisActionMenuAreDisplayed() {
    softAssert = new SoftAssert();
    newsManagementScreen.waitSearchResultsLoading().clickEllipsisActionMenu();
    softAssert.assertTrue(newsManagementScreen.isViewLinkDisplayed(),
        "View link is not displayed!");
    softAssert.assertTrue(newsManagementScreen.isEditLinkDisplayed(),
        "Edit link is not displayed!");
    softAssert.assertTrue(newsManagementScreen.isDeleteLinkDisplayed(),
        "Delete link is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkEllipsisActionMenuItemsHighlightedWhenHovered() {
    softAssert = new SoftAssert();
    softAssert.assertNotEquals(newsManagementScreen.getViewLinkTextColor(),
        newsManagementScreen.getHoveredViewLinkTextColor(),
        "View link in ellipsis action menu is not highlighted when hovered on it!");
    softAssert.assertNotEquals(newsManagementScreen.getEditLinkTextColor(),
        newsManagementScreen.getHoveredEditLinkTextColor(),
        "Edit link in ellipsis action menu is not highlighted when hovered on it!");
    softAssert.assertNotEquals(newsManagementScreen.getDeleteLinkTextColor(),
        newsManagementScreen.getHoveredDeleteLinkTextColor(),
        "Delete link in ellipsis action menu is not highlighted when hovered on it!");
    softAssert.assertAll();
  }
}
