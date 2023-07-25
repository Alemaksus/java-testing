package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21900_VerifyTheBannerSectionOfHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21900_VerifyTheBannerSectionOfHomePage {

  private HeaderScreen headerScreen;
  private static final String IMAGE_PATH = "Content/themes/Redesign/banners/home.jpg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test
  public void checkTextOnBannerDisplayed() {
    assertEquals(headerScreen.getBannerText(), "Jumpstart & Develop\n" + "Your Career with Us",
        "There isn't text in the banner!");
  }

  @Test
  public void checkBannerBackgroundImageDisplayed() {
    assertTrue(headerScreen.getBannerBackground().contains(IMAGE_PATH),
        "Banner's background doesn't contain a link to the image!");
  }
}
