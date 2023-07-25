package com.epmrdpt.regression.homefooter;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21918_VerifyThatLinkedInIconInTheFooterSectionIsHighlightedWhenHoverOver",
    groups = {"full", "general", "regression"})
public class EPMRDPT_21918_VerifyThatLinkedInIconInTheFooterSectionIsHighlightedWhenHoverOver {

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new TrainingBlockScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkHighlightedLinkedInLinkWhenHoverOver() {
    assertTrue(new FooterScreen().mouseOverLinkedInIcon().isLinkedInIconHighlighted(),
        "LinkedIn link doesn't change color after mouse over!");
  }
}
