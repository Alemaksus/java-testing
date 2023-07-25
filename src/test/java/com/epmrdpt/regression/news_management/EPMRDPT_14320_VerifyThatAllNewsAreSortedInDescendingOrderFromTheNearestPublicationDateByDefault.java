package com.epmrdpt.regression.news_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14320_VerifyThatAllNewsAreSortedInDescendingOrderFromTheNearestPublicationDateByDefault",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14320_VerifyThatAllNewsAreSortedInDescendingOrderFromTheNearestPublicationDateByDefault {

  private User user;
  private NewsManagementScreen newsManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14320_VerifyThatAllNewsAreSortedInDescendingOrderFromTheNearestPublicationDateByDefault(
      User user) {
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
  public void checkNewsAreCreated() {
    assertTrue(newsManagementScreen.waitSearchResultsLoading().getPublicationDatesCount() > 0,
        "News with publication dates aren't created!");
  }

  @Test(priority = 2)
  public void checkNewsAreOrderedInDescendingOrderOfPublicationDates() {
    List<LocalDate> publicationDates = StringUtils
        .getDatesListInDateFromString(newsManagementScreen.getPublicationDates(),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    List<LocalDate> publicationDatesAfterSortInDescendingOrder = new ArrayList<>();
    publicationDatesAfterSortInDescendingOrder.addAll(publicationDates);
    Collections.sort(publicationDatesAfterSortInDescendingOrder, Collections.reverseOrder());
    assertEquals(publicationDates, publicationDatesAfterSortInDescendingOrder,
        "News aren't sorted in descending order based on the publication dates!");
  }
}
