package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class GroupsCommonTabScreen extends AbstractScreen {

  private Element trainingTitle = Element.byClassName("text");
  private Element groupName = Element.byCss("h2.mainTitle");

  @Override
  public boolean isScreenLoaded() {
    return trainingTitle.isDisplayed();
  }

  public String getGroupName() {
    return groupName.getText();
  }
}
