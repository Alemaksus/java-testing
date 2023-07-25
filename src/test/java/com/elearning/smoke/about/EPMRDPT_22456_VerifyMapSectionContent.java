package com.epmrdpt.smoke.about;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_ZOOM_IN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_ZOOM_OUT_BUTTON;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22456_VerifyMapSectionContent",
    groups = {"full", "general", "smoke", "deprecated"})
public class EPMRDPT_22456_VerifyMapSectionContent {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
  }

  @Test(priority = 2)
  public void checkMapSectionDisplayed() {
    assertTrue(aboutScreen.isMapDisplayed(), "Map section isn't displayed!");
  }

  @Test(priority = 2)
  public void checkMapButtonDisplayed() {
    assertTrue(aboutScreen.isMapButtonDisplayed(), "Map button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkSatelliteButtonDisplayed() {
    assertTrue(aboutScreen.isSatelliteButtonDisplayed(), "Satellite button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkToggleFullScreenButtonDisplayed() {
    assertTrue(aboutScreen.isToggleFullScreenButtonDisplayed(),
        "Toggle fullScreen button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkPegmanButtonDisplayed() {
    assertTrue(aboutScreen.isPegmanButtonDisplayed(), "Pegman button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkZoomInButtonDisplayed() {
    assertTrue(aboutScreen
            .isZoomInButtonDisplayed(LocaleProperties.getValueOf(ABOUT_MAP_SECTION_ZOOM_IN_BUTTON)),
        "Zoom in button isn't displayed!");
  }

  @Test(priority = 2)
  public void checkZoomOutButtonDisplayed() {
    assertTrue(aboutScreen
            .isZoomOutButtonDisplayed(LocaleProperties.getValueOf(ABOUT_MAP_SECTION_ZOOM_OUT_BUTTON)),
        "Zoom out button isn't displayed!");
  }
}
