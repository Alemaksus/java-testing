package com.epmrdpt.smoke.homeheader;

import static org.testng.Assert.assertNotEquals;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14508_VerifyThatLocalizationBlockGlobeIconIsHighlightedOnHovering",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14508_VerifyThatLocalizationBlockGlobeIconIsHighlightedOnHovering {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen()
        .waitScreenLoaded();
  }

  @Test
  public void checkHighlightedLanguageAcronymsWhenHoverOver() {
    assertNotEquals(headerScreen.getLanguageAcronymsColor(),
        headerScreen.getHoveredLanguageAcronymsColor(),
        "'Language acronyms' color wasn't changed after mouse hovering");
  }
}
