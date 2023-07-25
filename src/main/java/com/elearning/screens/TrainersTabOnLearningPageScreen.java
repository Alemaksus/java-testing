package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class TrainersTabOnLearningPageScreen extends AbstractScreen {

  private Element trainersTab = Element.byCss("a.tabs__info-tab[href*='Trainers']");
  private Element trainersCard = Element.byCss("div.trainers-card");
  private Element groupName = Element.byCss("tabs+ div div.trainers-skill");
  private Element trainersProfileImage = Element.byCss("div.trainers-photo");
  private Element trainersName = Element.byCss("div.trainers-name");
  private Element trainersMail = Element.byCss("div.trainers-email--name");
  private Element trainersSkype = Element.byCss("div.trainers-skype--name");

  @Override
  public boolean isScreenLoaded() {
    return isTrainersCardDisplayed();
  }

  public boolean isTrainersCardDisplayed() {
    return trainersCard.isDisplayed();
  }

  public TrainersTabOnLearningPageScreen clickTrainersTab() {
    trainersTab.click();
    return this;
  }

  public boolean isGroupNameDisplayed() {
    return groupName.isDisplayed();
  }

  public boolean isTrainersProfileImageDisplayed() {
    return trainersProfileImage.isDisplayed();
  }

  public boolean isTrainersNameDisplayed() {
    return trainersName.isDisplayed();
  }

  public boolean isTrainersMailDisplayed() {
    return trainersMail.isDisplayed();
  }

  public boolean isTrainersSkypeDisplayed() {
    return trainersSkype.isDisplayed();
  }

  public boolean isTrainersTabDisplayed() {
    return trainersTab.isDisplayed();
  }
}
