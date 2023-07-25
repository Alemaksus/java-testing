package com.epmrdpt.smoke.about;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13221_VerifyTheHistorySectionOfAboutPageContest",
    groups = {"full", "general", "smoke", "deprecated"})
public class EPMRDPT_13221_VerifyTheHistorySectionOfAboutPageContest {

  private AboutScreen about;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    about = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(about.isScreenLoaded(), "Page 'About us' didn't load!");
  }

  @Test(priority = 2)
  public void checkThatAllYearsOfEventDisplayed() {
    assertTrue(about.isAllYearsDisplayed(), "Not all years of events display!");
  }

  @Test(priority = 2)
  public void checkThatAllEventDescriptionsDisplayed() {
    assertTrue(about.isAllEventDescriptionsDisplayed(), "Not all descriptions of events display!");
  }

  @Test(priority = 2)
  public void checkThatHistoryTitleDisplayed() {
    assertTrue(about.isHistoryTitleDisplayed(), "History title didn't display!");
  }

  @Test(priority = 2)
  public void checkThatHistoryTitleCorrect() {
    String actualTitle = about.getHistoryTitleText();
    String expectedTitle = LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_HISTORY_TITLE);
    assertTrue(actualTitle.equals(expectedTitle),
        format("History title didn't correct! Expected -%s, actual -%s", expectedTitle,
            actualTitle));
  }
}
