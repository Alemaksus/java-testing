package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class AuditScreen extends AbstractScreen {

  private Element backButtonOnTheTop = Element
      .byXpath("//div[@class='audit-post-head']/button[contains(@class,'audit-back-button')]");

  @Override
  public boolean isScreenLoaded() {
    return backButtonOnTheTop.isDisplayed();
  }

  public AuditScreen waitScreenLoading() {
    backButtonOnTheTop.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen clickBackButtonOnTheTop() {
    backButtonOnTheTop.waitForClickableAndClick();
    return new ReactDetailTrainingScreen();
  }
}
