package com.epmrdpt.smoke.faq;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13230_VerifyContentFAQScreen",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13230_VerifyContentFAQScreen {

  private HeaderScreen headerScreen;
  private FAQScreen faqScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    faqScreen = headerScreen.waitLinksToRedirectOnOtherSectionsDisplayed().clickFAQNavigationLink();
  }

  @Test(priority = 1)
  public void checkFAQScreenLoading() {
    assertTrue(faqScreen.isScreenLoaded(), "FAQ Screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsHeaderSection() {
    assertTrue(
        headerScreen.isHeaderContainerDisplayed(), "'FAQ' page doesn't contain 'Header' section!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsBanner() {
    assertTrue(faqScreen.isBannerFAQDisplayed(), "'FAQ' page doesn't contain 'Banner' section!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsQuestionsSection() {
    assertTrue(faqScreen.isQuestionsContainerDisplayed(),
        "'FAQ' page doesn't contain 'Questions' section!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsHomeNavigationLink() {
    assertTrue(faqScreen.isHomeNavigationLinkDisplayed(), "Home navigate link doesn't display!");
  }

  @Test(priority = 2)
  public void checkFaqScreenHomeNavigationLinkLocalized() {
    assertEquals(faqScreen.getHomeNavigationLinkText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HOME_NAVIGATION_LINK),
        "Home navigation Link doesn't have valid text!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsFeedbackIcon() {
    assertTrue(
        new FooterScreen().isFeedbackDisplayed(), "'FAQ' page doesn't contain Feedback button!");
  }

  @Test(priority = 2)
  public void checkFaqScreenContainsFooterSection() {
    assertTrue(
        new FooterScreen().isFooterContainerDisplayed(),
        "'FAQ' page doesn't contain 'Footer' section!");
  }
}
