package com.epmrdpt.smoke.faq;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21937_VerifyBannerSectionFAQScreen",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21937_VerifyBannerSectionFAQScreen {

  private FAQScreen faqScreen;
  private static final String BANNER_TEXT = "FAQ";
  private static final String BANNER_IMAGE_PATH = "Content/themes/Redesign/banners/faq.jpg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    faqScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickFAQNavigationLink();
  }

  @Test(priority = 1)
  public void checkFAQScreenLoading() {
    assertTrue(faqScreen.isScreenLoaded(), "FAQ screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkBannerSectionFAQScreenContainsText() {
    assertEquals(
        faqScreen.getBannerText(), BANNER_TEXT,
        "'Banner' section of FAQ page doesn't contain text!");
  }

  @Test(priority = 2)
  public void checkBannerSectionFAQScreenContainsImage() {
    assertTrue(
        faqScreen
            .getBannerBackground()
            .contains(BANNER_IMAGE_PATH),
        "'Banner' section of FAQ page doesn't contain image!");
  }
}
