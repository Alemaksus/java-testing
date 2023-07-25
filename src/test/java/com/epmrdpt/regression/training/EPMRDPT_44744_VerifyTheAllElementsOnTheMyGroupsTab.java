package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_ADVANCED_SEARCH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_DATES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_SEARCH_BY_GROUP_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_TRAINING_NAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44744_VerifyTheAllElementsOnTheMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44744_VerifyTheAllElementsOnTheMyGroupsTab {

  private User user;
  private ReactTrainingScreen reactTrainingScreen;

  @Factory(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44744_VerifyTheAllElementsOnTheMyGroupsTab(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupMyGroupsTabOnReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .waitCalendarForVisibility()
        .waitScheduleForVisibility()
        .clickMyGroupsTab()
        .clickAdvancedSearchButton()
        .clickOnlyMyGroupsCheckBox()
        .clickApplyButton()
        .waitGroupsTableFieldForVisible();
  }

  @Test
  public void checkSearchByGroupNameHasCorrectText() {
    assertEquals(reactTrainingScreen.getGroupsSearchValue(),
        LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_SEARCH_BY_GROUP_NAME),
        "'Search by group name' in My Groups tab has incorrect text!");
  }

  @Test
  public void checkAdvancedSearchHasCorrectText() {
    assertEquals(reactTrainingScreen.getAdvancedSearchText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_ADVANCED_SEARCH),
        "'Advanced Search' in My Groups tab has incorrect text!");
  }

  @Test
  public void checkHeaderGroupsHasCorrectText() {
    assertEquals(reactTrainingScreen.getHeaderGroupsText(), headerGroupsList,
        "'Header Groups' in My Groups tab has incorrect text!");
  }

  @Test
  public void checkLocationIconDisplayed() {
    assertTrue(reactTrainingScreen.isLocationIconDisplayed(),
        "'Location Icon' in My Groups tab isn't displayed!");
  }

  @Test
  public void checkJournalIconDisplayed() {
    assertTrue(reactTrainingScreen.isJournalIconDisplayed(),
        "'Journal Icon' in My Groups tab isn't displayed!");
  }

  private List<String> headerGroupsList = new ArrayList<>(Arrays.asList(
      LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_TRAINING_NAME),
      LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME),
      LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_DATES),
      LocaleProperties.getValueOf(REACT_TRAINING_MY_GROUPS_TAB_CURRENT_STATUS)));
}
